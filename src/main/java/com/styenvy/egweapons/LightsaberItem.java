package com.styenvy.egweapons;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LightsaberItem extends SwordItem {

    public enum LightsaberColor {
        RED("Red"),
        BLUE("Blue"),
        GREEN("Green"),
        PURPLE("Purple");

        private final String name;

        LightsaberColor(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public LightsaberItem(LightsaberColor color) {
        super(LightsaberTier.INSTANCE, createProperties(color));
    }

    private static Item.Properties createProperties(LightsaberColor color) {
        return new Item.Properties()
                .durability(8192)
                .rarity(Rarity.EPIC)
                .component(DataComponents.ITEM_NAME, Component.literal(color.getName() + " Lightsaber"))
                .component(DataComponents.ATTRIBUTE_MODIFIERS, createAttributeModifiers());
    }

    private static ItemAttributeModifiers createAttributeModifiers() {
        return ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(BASE_ATTACK_DAMAGE_ID, 19.0, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_SPEED,
                        new AttributeModifier(BASE_ATTACK_SPEED_ID, -2.0, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND)
                .build();
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("§7An elegant weapon from a more civilized age"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    public boolean isEnchantable(@NotNull ItemStack stack) {
        return true;
    }

    @Override
    public int getEnchantmentValue() {
        return 50;
    }

    @Override
    public boolean isValidRepairItem(@NotNull ItemStack toRepair, ItemStack repair) {
        return repair.is(Items.NETHER_STAR);
    }
}