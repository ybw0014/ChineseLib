package net.guizhanss.guizhanlib.minecraft.helper.potion;

import com.google.common.base.Preconditions;
import io.github.thebusybiscuit.slimefun4.api.MinecraftVersion;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import lombok.experimental.UtilityClass;
import net.guizhanss.guizhanlib.minecraft.LanguageHelper;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nonnull;

/**
 * 药水效果({@link PotionEffectType})
 *
 * @author ybw0014
 */
@UtilityClass
@SuppressWarnings("unused")
public final class PotionEffectTypeHelper {
    /**
     * 返回药水效果({@link PotionEffectType})的中文名
     *
     * @param type
     *     {@link PotionEffectType} 药水效果
     *
     * @return 药水效果的中文名, 如果获取失败则返回键名
     */
    @Nonnull
    public static String getName(@Nonnull PotionEffectType type) {
        return LanguageHelper.getLangOrKey(getKey(type));
    }

    /**
     * 获取药水效果({@link PotionEffectType}的键名
     *
     * @param type
     *     {@link PotionEffectType} 药水效果
     *
     * @return 药水效果的键名
     */
    @Nonnull
    public static String getKey(@Nonnull PotionEffectType type) {
        Preconditions.checkArgument(type != null, "药水效果不能为空");

        String key;

        /*
          处理语言文件与内部代码不一致的问题。
          在1.18后，PotionEffectType 增加了 NamespacedKey 可以直接获取语言key。
         */
        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_18)) {
            key = type.getKey().getKey();
        } else {
            if (type == PotionEffectType.CONFUSION) {
                key = "nausea";
            } else if (type == PotionEffectType.DAMAGE_RESISTANCE) {
                key = "resistance";
            } else if (type == PotionEffectType.FAST_DIGGING) {
                key = "haste";
            } else if (type == PotionEffectType.HARM) {
                key = "instant_damage";
            } else if (type == PotionEffectType.HEAL) {
                key = "instant_health";
            } else if (type == PotionEffectType.INCREASE_DAMAGE) {
                key = "strength";
            } else if (type == PotionEffectType.JUMP) {
                key = "jump_boost";
            } else if (type == PotionEffectType.SLOW) {
                key = "slowness";
            } else if (type == PotionEffectType.SLOW_DIGGING) {
                key = "mining_fatigue";
            } else {
                key = type.getName().toLowerCase();
            }
        }

        return "effect.minecraft." + key;
    }
}
