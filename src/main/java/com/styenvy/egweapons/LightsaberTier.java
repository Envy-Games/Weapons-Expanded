package com.styenvy.egweapons;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public class LightsaberTier implements Tier {

    public static final LightsaberTier INSTANCE = new LightsaberTier();

    private LightsaberTier() {}

    @Override
    public int getUses() {
        return 8192; // Very high durability
    }

    @Override
    public float getSpeed() {
        return 12.0F; // Fast mining speed
    }

    @Override
    public float getAttackDamageBonus() {
        return 19.0F; // Base damage (20 total with base 1 damage)
    }

    @Override
    public @NotNull TagKey<Block> getIncorrectBlocksForDrops() {
        return BlockTags.INCORRECT_FOR_NETHERITE_TOOL;
    }

    @Override
    public int getEnchantmentValue() {
        return 50; // Very high enchantability for best enchantments
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return Ingredient.of(Items.NETHER_STAR);
    }
}