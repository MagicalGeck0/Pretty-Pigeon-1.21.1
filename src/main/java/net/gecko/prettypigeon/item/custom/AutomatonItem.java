package net.gecko.prettypigeon.item.custom;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.gecko.prettypigeon.entity.ModEntities;
import net.gecko.prettypigeon.entity.custom.PigeonEntity;
import net.gecko.prettypigeon.entity.custom.PigeonVariant;
import net.gecko.prettypigeon.item.ModItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.List;

public class AutomatonItem extends Item {

    public AutomatonItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {

        BlockPos top = context.getBlockPos().add(0, 1, 0);
        PigeonEntity pigeon = ModEntities.PIGEON.create(context.getWorld());
        ItemStack automaton = context.getStack();
        if (context.getSide()!= Direction.UP){return ActionResult.PASS;}

        PigeonVariant variant = PigeonVariant.IRON;
        if (automaton.isOf(ModItems.COPPER_AUTOMATON)){variant = PigeonVariant.COPPER;}
        else if (automaton.isOf(ModItems.GOLD_AUTOMATON)){variant = PigeonVariant.GOLD;}
        else if (automaton.isOf(ModItems.NETHERITE_AUTOMATON)){variant = PigeonVariant.NETHERITE;}

        if (!context.getWorld().isClient){
            pigeon.setPos(top.getX() + 0.5, top.getY() + 0.01, top.getZ() + 0.5);
            pigeon.setOwner(context.getPlayer());
            if (automaton.get(DataComponentTypes.CUSTOM_NAME)!=null){
                pigeon.setCustomName(automaton.getName());
            }
            pigeon.setVariant(variant);
            pigeon.setSitting(true);
            context.getWorld().spawnEntity(pigeon);

        }
        automaton.decrementUnlessCreative(1, context.getPlayer());
        return ActionResult.SUCCESS;
    }
}
