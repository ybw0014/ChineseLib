import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    `java-library`
    `maven-publish`
    signing
    id("io.freefair.lombok") version "8.7.1"
    id("com.gradleup.shadow") version "8.3.3"
    id("org.sonarqube") version "4.4.1.3373"
}

group = "net.guizhanss"

allprojects {
    repositories {
        mavenCentral()
        maven(url = "https://jitpack.io/")
        maven(url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        maven(url = "https://papermc.io/repo/repository/maven-public")
    }
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")
    apply(plugin = "signing")
    apply(plugin = "io.freefair.lombok")
    apply(plugin = "com.gradleup.shadow")
    apply(plugin = "org.sonarqube")

    dependencies {
        fun compileOnlyAndTestImplementation(dependencyNotation: Any) {
            compileOnly(dependencyNotation)
            testImplementation(dependencyNotation)
        }

        api("com.google.code.findbugs:jsr305:3.0.2")
        compileOnlyAndTestImplementation("io.papermc.paper:paper-api:1.20.6-R0.1-SNAPSHOT")
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.3")
        testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.3")
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
        withJavadocJar()
        withSourcesJar()
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<ShadowJar> {
        archiveAppendix = ""
    }

    sonar {
        properties {
            property("sonar.projectKey", "ybw0014_GuizhanLib")
            property("sonar.organization", "ybw0014")
            property("sonar.host.url", "https://sonarcloud.io")
        }
    }

    publishing {
        publications {
            create<MavenPublication>("maven") {
                project.shadow.component(this)

                artifact(tasks.named("javadocJar").get())
                artifact(tasks.named("sourcesJar").get())

                groupId = rootProject.group as String
                artifactId = project.name
                version = rootProject.version as String

                pom {
                    name.set("guizhanlib")
                    description.set("A library for Slimefun addon development.")
                    url.set("https://github.com/ybw0014/guizhanlib")

                    licenses {
                        license {
                            name.set("GPL-3.0 license")
                            url.set("https://github.com/ybw0014/guizhanlib/blob/master/LICENSE")
                            distribution.set("repo")
                        }
                    }

                    developers {
                        developer {
                            name.set("ybw0014")
                            url.set("https://ybw0014.dev/")
                        }
                    }

                    scm {
                        connection.set("scm:git:git://github.com/ybw0014/guizhanlib.git")
                        developerConnection.set("scm:git:ssh://github.com:ybw0014/guizhanlib.git")
                        url.set("https://github.com/ybw0014/guizhanlib/tree/master")
                    }
                }
            }
        }

        repositories {
            maven {
                name = "CentralRelease"
                url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                credentials {
                    username = System.getenv("OSSRH_USERNAME") ?: ""
                    password = System.getenv("OSSRH_PASSWORD") ?: ""
                }
            }
            maven {
                name = "CentralSnapshot"
                url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
                credentials {
                    username = System.getenv("OSSRH_USERNAME") ?: ""
                    password = System.getenv("OSSRH_PASSWORD") ?: ""
                }
            }
        }
    }

    signing {
        useGpgCmd()
        sign(configurations.runtimeElements.get())
    }
}
