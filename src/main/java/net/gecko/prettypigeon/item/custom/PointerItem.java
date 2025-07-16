package net.gecko.prettypigeon.item.custom;

import com.mojang.datafixers.TypeRewriteRule;
import net.gecko.prettypigeon.entity.custom.PigeonVariant;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import javax.swing.*;
import java.util.List;

public class PointerItem extends Item {

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (stack.get(DataComponentTypes.CUSTOM_DATA) != null)
            if (Screen.hasControlDown()) {
                tooltip.add(Text.literal(PigeonVariant.byid(stack.get(DataComponentTypes.CUSTOM_DATA).copyNbt().getInt("variant")).toString()));
            } else {
                tooltip.add(Text.translatable("tooltip.pretpig.pointer_tip"));
            }


        super.appendTooltip(stack, context, tooltip, type);
    }

    public PointerItem(Settings settings) {
        super(settings);
    }
}
