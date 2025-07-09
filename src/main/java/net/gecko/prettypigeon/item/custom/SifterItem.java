package net.gecko.prettypigeon.item.custom;

import net.gecko.prettypigeon.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class SifterItem extends Item {
    private  static final Map<Block, Block> SIFTER_MAP =
            Map.of(
                    Blocks.ROOTED_DIRT, Blocks.COARSE_DIRT,
                    Blocks.COARSE_DIRT, Blocks.DIRT
            );
    private static final Map<BlockPos, Integer> clickCountMap = new HashMap<>();

    public SifterItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        Block clickedBlock = world.getBlockState(context.getBlockPos()).getBlock();

        if(SIFTER_MAP.containsKey(clickedBlock)) {
            if(!world.isClient()){
                int count = clickCountMap.getOrDefault(pos, 0) + 1;
                if (count >= 5) {
                    clickCountMap.remove(pos);
                    world.setBlockState(context.getBlockPos(), SIFTER_MAP.get(clickedBlock).getDefaultState());
                    context.getPlayer().giveItemStack(new ItemStack(ModItems.WORM));
                    context.getStack().damage(1, ((ServerWorld) world), ((ServerPlayerEntity) context.getPlayer()),
                            item -> context.getPlayer().sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));

                    world.playSound(null, context.getBlockPos(), SoundEvents.BLOCK_ROOTED_DIRT_STEP, SoundCategory.BLOCKS);

                }else {
                    clickCountMap.put(pos, count);
                }
                return ActionResult.SUCCESS;
            }
            return ActionResult.PASS;
        }

        return ActionResult.PASS;
    }
}
