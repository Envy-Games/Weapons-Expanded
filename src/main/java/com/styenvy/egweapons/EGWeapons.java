package com.styenvy.egweapons;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;


@Mod(EGWeapons.MODID)
public class EGWeapons {
    public static final String MODID = "egweapons";

    public EGWeapons(IEventBus modEventBus, ModContainer modContainer) {
        ModItems.ITEMS.register(modEventBus);
        modEventBus.addListener(this::buildCreativeTabContents);
    }

    private void buildCreativeTabContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            // Output.accept expects an ItemStack or ItemLike; call .get() on DeferredHolder
            event.accept(ModItems.RED_LIGHTSABER.get());
            event.accept(ModItems.BLUE_LIGHTSABER.get());
            event.accept(ModItems.GREEN_LIGHTSABER.get());
            event.accept(ModItems.PURPLE_LIGHTSABER.get());
            event.accept(ModItems.EMERALD_SWORD.get());
        }
    }
}