package com.willfp.eco.internal.items;

import com.willfp.eco.util.StringUtils;
import lombok.AccessLevel;
import lombok.Getter;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public abstract class AbstractItemStackBuilder<T extends ItemMeta> {
    /**
     * The ItemMeta used while building.
     */
    @Getter(AccessLevel.PROTECTED)
    private final T meta;

    /**
     * The ItemStack.
     */
    @Getter(AccessLevel.PROTECTED)
    private final ItemStack base;

    /**
     * Create a new ItemStackBuilder.
     *
     * @param material The material.
     */
    protected AbstractItemStackBuilder(@NotNull final Material material) {
        this(new ItemStack(material));
    }

    /**
     * Create a new ItemStackBuilder to modify an existing item.
     *
     * @param base The ItemStack to start with.
     */
    protected AbstractItemStackBuilder(@NotNull final ItemStack base) {
        this.base = base;
        this.meta = (T) base.getItemMeta();
        assert meta != null;
    }

    /**
     * Set the ItemStack amount.
     *
     * @param amount The amount.
     * @return The builder.
     */
    public AbstractItemStackBuilder<T> setAmount(final int amount) {
        Validate.isTrue(amount >= 1 && amount <= base.getMaxStackSize());
        base.setAmount(amount);
        return this;
    }

    /**
     * Set the ItemStack amount.
     *
     * @param amount The amount.
     * @return The builder.
     */
    public AbstractItemStackBuilder<T> setAmount(@NotNull final Supplier<Integer> amount) {
        return setAmount(amount.get());
    }

    /**
     * Add an enchantment to the item.
     *
     * @param enchantment The enchantment.
     * @param level       The level.
     * @return The builder.
     */
    public AbstractItemStackBuilder<T> addEnchantment(@NotNull final Enchantment enchantment,
                                                      final int level) {
        meta.addEnchant(enchantment, level, true);
        return this;
    }

    /**
     * Add an enchantment to the item.
     *
     * @param enchantment The enchantment.
     * @param level       The level.
     * @return The builder.
     */
    public AbstractItemStackBuilder<T> addEnchantment(@NotNull final Supplier<Enchantment> enchantment,
                                                      @NotNull final Supplier<Integer> level) {
        return addEnchantment(enchantment.get(), level.get());
    }

    /**
     * Set the item display name.
     *
     * @param name The name.
     * @return The builder.
     */
    public AbstractItemStackBuilder<T> setDisplayName(@NotNull final String name) {
        meta.setDisplayName(StringUtils.translate(name));
        return this;
    }

    /**
     * Set the item display name.
     *
     * @param name The name.
     * @return The builder.
     */
    public AbstractItemStackBuilder<T> setDisplayName(@NotNull final Supplier<String> name) {
        String result = name.get();

        return result == null ? this : setDisplayName(name.get());
    }

    /**
     * Add lore line.
     *
     * @param line The line.
     * @return The builder.
     */
    public AbstractItemStackBuilder<T> addLoreLine(@NotNull final String line) {
        List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
        assert lore != null;
        lore.add(StringUtils.translate(line));
        meta.setLore(lore);

        return this;
    }

    /**
     * Add lore line.
     *
     * @param line The line.
     * @return The builder.
     */
    public AbstractItemStackBuilder<T> addLoreLine(@NotNull final Supplier<String> line) {
        String result = line.get();

        return result == null ? this : addLoreLine(line.get());
    }

    /**
     * Add lore lines.
     *
     * @param lines The lines.
     * @return The builder.
     */
    public AbstractItemStackBuilder<T> addLoreLines(@NotNull final List<String> lines) {
        List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
        assert lore != null;
        for (String line : lines) {
            lore.add(StringUtils.translate(line));
        }
        meta.setLore(lore);

        return this;
    }

    /**
     * Add lore lines.
     *
     * @param lines The lines.
     * @return The builder.
     */
    public AbstractItemStackBuilder<T> addLoreLines(@NotNull final Supplier<List<String>> lines) {
        List<String> result = lines.get();

        return result == null ? this : addLoreLines(lines.get());
    }

    /**
     * Add ItemFlags.
     *
     * @param itemFlags The flags.
     * @return The builder.
     */
    public AbstractItemStackBuilder<T> addItemFlag(@NotNull final ItemFlag... itemFlags) {
        meta.addItemFlags(itemFlags);

        return this;
    }

    /**
     * Add ItemFlags.
     *
     * @param itemFlags The flags.
     * @return The builder.
     */
    public AbstractItemStackBuilder<T> addItemFlag(@NotNull final Supplier<ItemFlag[]> itemFlags) {
        ItemFlag[] result = itemFlags.get();

        return result == null ? this : addItemFlag(result);
    }

    /**
     * Write meta key.
     *
     * @param key   The key.
     * @param type  The type.
     * @param value The value.
     * @param <A>   The type.
     * @param <B>   The type.
     * @return The builder.
     */
    public <A, B> AbstractItemStackBuilder<T> writeMetaKey(@NotNull final NamespacedKey key,
                                                           @NotNull final PersistentDataType<A, B> type,
                                                           @NotNull final B value) {
        meta.getPersistentDataContainer().set(key, type, value);

        return this;
    }

    /**
     * Write meta key.
     *
     * @param key   The key.
     * @param type  The type.
     * @param value The value.
     * @param <A>   The type.
     * @param <B>   The type.
     * @return The builder.
     */
    public <A, B> AbstractItemStackBuilder<T> writeMetaKey(@NotNull final Supplier<NamespacedKey> key,
                                                           @NotNull final Supplier<PersistentDataType<A, B>> type,
                                                           @NotNull final Supplier<B> value) {
        return writeMetaKey(key.get(), type.get(), value.get());
    }

    /**
     * Set unbreakable.
     *
     * @param unbreakable If the item should be unbreakable.
     * @return The builder.
     */
    public AbstractItemStackBuilder<T> setUnbreakable(final boolean unbreakable) {
        meta.setUnbreakable(unbreakable);

        return this;
    }

    /**
     * Set unbreakable.
     *
     * @param unbreakable If the item should be unbreakable.
     * @return The builder.
     */
    public AbstractItemStackBuilder<T> setUnbreakable(@NotNull final Supplier<Boolean> unbreakable) {
        Boolean result = unbreakable.get();

        return result == null ? this : setUnbreakable(unbreakable);
    }

    /**
     * Set custom model data.
     *
     * @param data The data.
     * @return The builder.
     */
    public AbstractItemStackBuilder<T> setCustomModelData(@Nullable final Integer data) {
        meta.setCustomModelData(data);

        return this;
    }

    /**
     * Set custom model data.
     *
     * @param data The data.
     * @return The builder.
     */
    public AbstractItemStackBuilder<T> setCustomModelData(@NotNull final Supplier<Integer> data) {
        Integer result = data.get();

        return result == null ? this : setCustomModelData(result);
    }

    /**
     * Build the item.
     *
     * @return The item.
     */
    public ItemStack build() {
        base.setItemMeta(meta);

        return base;
    }
}
