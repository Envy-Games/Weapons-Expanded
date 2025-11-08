package com.styenvy.egweapons;

import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.enchanting.EnchantmentLevelSetEvent;

@EventBusSubscriber(modid = EGWeapons.MODID)
public class EnchantmentHandler {

    @SubscribeEvent
    public static void onEnchantmentLevelSet(EnchantmentLevelSetEvent event) {
        ItemStack stack = event.getItem();

        // Apply only to our lightsabers
        if (stack.is(ModItems.RED_LIGHTSABER.get())
                || stack.is(ModItems.BLUE_LIGHTSABER.get())
                || stack.is(ModItems.GREEN_LIGHTSABER.get())
                || stack.is(ModItems.PURPLE_LIGHTSABER.get())) {

            // Use the proper getters in 1.21.x
            int currentLevel = event.getEnchantLevel();       // 0..30
            int originalLevel = event.getOriginalLevel();     // 0..30

            if (originalLevel > 0) {
                // Nudge upwards but clamp to the allowed range (0..30)
                int boosted = Math.max(30, currentLevel + 10);
                if (boosted > 30) boosted = 30;
                event.setEnchantLevel(boosted);
            }

            // NOTE: getEnchantmentCost()/setEnchantmentCost() do not exist on this event in 1.21.x.
            // If you want to affect costs, that requires a different approach (custom UI/mechanics).
        }
    }
}