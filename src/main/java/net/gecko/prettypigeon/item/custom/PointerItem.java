package net.gecko.prettypigeon.item.custom;

import net.gecko.prettypigeon.entity.custom.PigeonVariant;
import net.gecko.prettypigeon.item.ModItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PointerItem extends Item {

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (stack.get(DataComponentTypes.CUSTOM_DATA) != null) {
            tooltip.add(Text.literal(PigeonVariant.byid(stack.get(DataComponentTypes.CUSTOM_DATA).copyNbt().getInt("variant")).toString().toLowerCase()));
        } else {
            tooltip.add(Text.literal("§kvariant"));
        }


        super.appendTooltip(stack, context, tooltip, type);
    }

    public PointerItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack off = user.getOffHandStack();
        ItemStack pointer = user.getStackInHand(hand);
        NbtCompound data = new NbtCompound();
        if (pointer.get(DataComponentTypes.CUSTOM_DATA) == null && !off.isEmpty())  {
            if (off.isOf(ModItems.WARPED_ESSENCE)) {
                data.putInt("variant", PigeonVariant.WARPED.getId());
            } else if (off.isOf(ModItems.CRIMSON_ESSENCE)) {
                data.putInt("variant", PigeonVariant.CRIMSON.getId());
            } else if (off.isOf(ModItems.ENDER_ESSENCE)) {
                data.putInt("variant", PigeonVariant.DRAGON.getId());
            } else if (off.isOf(ModItems.TOAD_ESSENCE)) {
                data.putInt("variant", PigeonVariant.RIBBIT.getId());
            } else if (off.isOf(ModItems.BUMBLE_ESSENCE)) {
                data.putInt("variant", PigeonVariant.BUMBLE.getId());
            } else {
                return TypedActionResult.pass(pointer);
            }
            NbtComponent component = NbtComponent.of(data);
            pointer.set(DataComponentTypes.CUSTOM_DATA, component);
            off.decrementUnlessCreative(1,user);
            world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK, SoundCategory.NEUTRAL);
            return TypedActionResult.success(pointer);
        } else if (pointer.get(DataComponentTypes.CUSTOM_DATA) != null && user.isSneaking()) {
            pointer.set(DataComponentTypes.CUSTOM_DATA, null);
            world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_DISPENSER_DISPENSE, SoundCategory.NEUTRAL);
            return TypedActionResult.success(pointer);
        }
        return super.use(world, user, hand);
    }
}
