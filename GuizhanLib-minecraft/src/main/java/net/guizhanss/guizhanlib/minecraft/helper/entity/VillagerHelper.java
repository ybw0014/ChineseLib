package net.guizhanss.guizhanlib.minecraft.helper.entity;

import com.google.common.base.Preconditions;
import lombok.experimental.UtilityClass;
import net.guizhanss.guizhanlib.minecraft.LanguageHelper;
import net.guizhanss.guizhanlib.utils.StringUtil;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;

import javax.annotation.Nonnull;

/**
 * 村民({@link Villager})
 *
 * @author ybw0014
 */
@UtilityClass
@SuppressWarnings("ConstantConditions")
public final class VillagerHelper {
    /**
     * 返回村民职业({@link Profession})的中文名
     *
     * @param profession
     *     {@link Profession} 村民职业
     *
     * @return 村民职业的中文名称, 如果获取失败则返回键名
     */
    @Nonnull
    public static String getProfessionName(@Nonnull Profession profession) {
        return LanguageHelper.getLangOrKey(getProfessionKey(profession));
    }

    /**
     * 获取村民职业({@link Profession})的键名
     *
     * @param profession
     *     {@link Profession} 村民职业
     *
     * @return 村民职业的键名
     */
    @Nonnull
    public static String getProfessionKey(@Nonnull Profession profession) {
        Preconditions.checkArgument(profession != null, "村民职业不能为空");

        return "entity.minecraft.villager." + profession.toString().toLowerCase();
    }

    /**
     * 返回村民职业({@link Profession})的中文名
     *
     * @param profession
     *     {@link String} 村民职业
     *
     * @return 村民职业的中文名称, 如果获取失败则返回可读形式
     */
    @Nonnull
    public static String getProfessionName(@Nonnull String profession) {
        Preconditions.checkArgument(profession != null, "村民职业不能为空");

        try {
            Profession villagerProfession = Profession.valueOf(profession);
            return getProfessionName(villagerProfession);
        } catch (IllegalArgumentException ex) {
            return StringUtil.humanize(profession);
        }
    }
}
