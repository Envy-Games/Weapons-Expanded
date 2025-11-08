package com.styenvy.egweapons;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            net.minecraft.core.registries.BuiltInRegistries.ITEM, EGWeapons.MODID);

    public static final DeferredHolder<Item, LightsaberItem> RED_LIGHTSABER = ITEMS.register("red_lightsaber",
            () -> new LightsaberItem(LightsaberItem.LightsaberColor.RED));

    public static final DeferredHolder<Item, LightsaberItem> BLUE_LIGHTSABER = ITEMS.register("blue_lightsaber",
            () -> new LightsaberItem(LightsaberItem.LightsaberColor.BLUE));

    public static final DeferredHolder<Item, LightsaberItem> GREEN_LIGHTSABER = ITEMS.register("green_lightsaber",
            () -> new LightsaberItem(LightsaberItem.LightsaberColor.GREEN));

    public static final DeferredHolder<Item, LightsaberItem> PURPLE_LIGHTSABER = ITEMS.register("purple_lightsaber",
            () -> new LightsaberItem(LightsaberItem.LightsaberColor.PURPLE));

    public static final DeferredHolder<Item, SwordItem> EMERALD_SWORD = ITEMS.register("emerald_sword",
            () -> new SwordItem(Tiers.IRON, new Item.Properties()));
}