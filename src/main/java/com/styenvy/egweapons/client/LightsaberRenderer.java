package com.styenvy.egweapons.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.styenvy.egweapons.LightsaberItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.Optional;

/**
 * Base pass: normal item render.
 * Overlay pass: emissive texture via RenderType.eyes(...), full-bright.
 */
public class LightsaberRenderer extends BlockEntityWithoutLevelRenderer {

    private static final int FULL_BRIGHT = 0xF000F0; // 15728880
    private final ItemRenderer itemRenderer;

    public LightsaberRenderer() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                Minecraft.getInstance().getEntityModels());
        this.itemRenderer = Minecraft.getInstance().getItemRenderer();
    }

    @Override
    public void renderByItem(@NotNull ItemStack stack,
                             @NotNull ItemDisplayContext displayContext,
                             @NotNull PoseStack poseStack,
                             @NotNull MultiBufferSource buffer,
                             int packedLight,
                             int packedOverlay) {

        if (!(stack.getItem() instanceof LightsaberItem)) {
            return;
        }

        // --- Pass 1: normal/base render (uses your JSON model & base texture) ---
        BakedModel model = itemRenderer.getModel(stack, null, null, 0);
        itemRenderer.render(stack, displayContext, false, poseStack, buffer, packedLight, packedOverlay, model);

        // --- Pass 2: emissive overlay (full-bright) using a mask texture ---
        ResourceLocation emissive = resolveEmissiveTexture(stack);
        if (resourceExists(emissive)) {
            RenderType emissiveRT = RenderType.eyes(emissive);
            MultiBufferSource fixed = new FixedTypeBuffer(buffer, emissiveRT);
            // Re-render the same geometry but routed through the emissive RenderType at full-bright
            itemRenderer.render(stack, displayContext, false, poseStack, fixed, FULL_BRIGHT, packedOverlay, model);
        }
    }

    /**
     * Derive emissive texture from the item registry id.
     * Example: egweapons:red_lightsaber -> egweapons:item/red_saberlight_e
     * Adjust to your naming if needed.
     */
    private static ResourceLocation resolveEmissiveTexture(ItemStack stack) {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(stack.getItem());

        String ns = id.getNamespace();
        String path = id.getPath(); // e.g. "red_lightsaber"
        String color = path.contains("_") ? path.substring(0, path.indexOf('_')) : path;
        color = color.toLowerCase(Locale.ROOT);

        return ResourceLocation.fromNamespaceAndPath(ns, "item/" + color + "_lightsaber_e");
    }

    private static boolean resourceExists(ResourceLocation rl) {
        Optional<?> res = Minecraft.getInstance().getResourceManager().getResource(rl);
        return res.isPresent();
    }

    /** A buffer wrapper that forces all draws through a fixed RenderType (our emissive overlay). */
    private static final class FixedTypeBuffer implements MultiBufferSource {
        private final MultiBufferSource delegate;
        private final RenderType fixedType;
        private VertexConsumer cached;

        FixedTypeBuffer(MultiBufferSource delegate, RenderType fixedType) {
            this.delegate = delegate;
            this.fixedType = fixedType;
        }

        @Override
        public @NotNull VertexConsumer getBuffer(@NotNull RenderType ignored) {
            if (cached == null) cached = delegate.getBuffer(fixedType);
            return cached;
        }
    }
}
