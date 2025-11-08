package com.styenvy.egweapons;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = EGWeapons.MODID)
public class DataGenerator {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var gen = event.getGenerator();
        PackOutput packOutput = gen.getPackOutput();
        CompletableFuture<HolderLookup.Provider> registries = event.getLookupProvider();
        ExistingFileHelper efh = event.getExistingFileHelper();

        // Server-side providers
        gen.addProvider(event.includeServer(), new ModRecipes(packOutput, registries));

        // Client-side providers
        gen.addProvider(event.includeClient(), new ModLanguageProvider(packOutput));
        gen.addProvider(event.includeClient(), new ModItemModels(packOutput, efh));
    }

    private static class ModLanguageProvider extends LanguageProvider {
        public ModLanguageProvider(PackOutput output) {
            super(output, EGWeapons.MODID, "en_us");
        }

        @Override
        protected void addTranslations() {
            add(ModItems.RED_LIGHTSABER.get(), "Red Nethersaber");
            add(ModItems.BLUE_LIGHTSABER.get(), "Blue Nethersaber");
            add(ModItems.GREEN_LIGHTSABER.get(), "Green Nethersaber");
            add(ModItems.PURPLE_LIGHTSABER.get(), "Purple Nethersaber");
        }
    }

    private static class ModItemModels extends ItemModelProvider {
        public ModItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
            super(output, EGWeapons.MODID, existingFileHelper);
        }

        @Override
        protected void registerModels() {
            handheld(ModItems.RED_LIGHTSABER.get());
            handheld(ModItems.BLUE_LIGHTSABER.get());
            handheld(ModItems.GREEN_LIGHTSABER.get());
            handheld(ModItems.PURPLE_LIGHTSABER.get());
        }

        private void handheld(Item item) {
            String path = BuiltInRegistries.ITEM.getKey(item).getPath();
            getBuilder(path)
                    .parent(getExistingFile(mcLoc("item/handheld")))
                    .texture("layer0", modLoc("item/" + path));
        }
    }
}
