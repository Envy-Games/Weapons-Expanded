package com.styenvy.egweapons.client;

import com.styenvy.egweapons.EGWeapons;
import com.styenvy.egweapons.ModItems;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.jetbrains.annotations.NotNull;

@EventBusSubscriber(modid = EGWeapons.MODID, value = Dist.CLIENT)
public class ClientEvents {

    private static LightsaberRenderer lightsaberRenderer;

    @SubscribeEvent
    public static void onRegisterClientExtensions(RegisterClientExtensionsEvent event) {
        IClientItemExtensions extensions = new IClientItemExtensions() {
            @Override
            public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (lightsaberRenderer == null) {
                    lightsaberRenderer = new LightsaberRenderer();
                }
                return lightsaberRenderer;
            }
        };

        event.registerItem(extensions,
                ModItems.RED_LIGHTSABER.get(),
                ModItems.BLUE_LIGHTSABER.get(),
                ModItems.GREEN_LIGHTSABER.get(),
                ModItems.PURPLE_LIGHTSABER.get()
        );
    }
}