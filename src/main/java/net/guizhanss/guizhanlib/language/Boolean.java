package net.guizhanss.guizhanlib.language;

import lombok.experimental.UtilityClass;

import javax.annotation.Nonnull;

/**
 * 布尔值(Boolean)相关的中文
 *
 * @author ybw0014
 */
@UtilityClass
public class Boolean {
    /**
     * 根据提供的布尔值返回"是"或"否"
     * @param value 一个布尔值
     * @return 根据提供的布尔值返回 {@link String} "是"或"否"
     */
    public static @Nonnull String yesOrNo(boolean value) {
        return value ? "是" : "否";
    }

    /**
     * 根据提供的布尔值返回"已启用"或"已禁用"
     * @param value 一个布尔值
     * @return 根据提供的布尔值返回 {@link String} "已启用"或"已禁用"
     */
    public static @Nonnull String enabledOrDisabled(boolean value) {
        return value ? "已启用" : "已禁用";
    }
}