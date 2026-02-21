package net.gecko.prettypigeon.block.custom;

import net.gecko.prettypigeon.entity.ModEntities;
import net.gecko.prettypigeon.entity.custom.PigeonEntity;
import net.gecko.prettypigeon.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.joml.Vector3f;

import java.util.List;

public class AltarBlock extends Block {
    public AltarBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        tooltip.add(Text.literal("drop echo blend on top of the echo chamber then use a pigeon's last feather to revive him"));
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        NbtComponent nbt = stack.get(DataComponentTypes.CUSTOM_DATA);
        BlockPos top = pos.add(0, 1, 0);
        List<ItemEntity> items = world.getEntitiesByClass(ItemEntity.class,new Box(top),itemEntity -> itemEntity.getStack().isOf(ModItems.ECHO_BLEND));

        if (stack.isOf(ModItems.PIGEON_FEATHER)
                && nbt != null
                && !items.isEmpty()
        ) {
            PigeonEntity pigeon = ModEntities.PIGEON.create(world);
            if (!world.isClient) {
                pigeon.setPos(top.getX() + 0.5, top.getY() + 0.5, top.getZ() + 0.5);
                NbtCompound data = nbt.copyNbt();
                pigeon.readCustomDataFromNbt(data);
                pigeon.setOwner(world.getPlayerByUuid(data.getUuid("owner")));
                if (data.contains("name")) {
                    pigeon.setCustomName(Text.literal(data.getString("name")));
                }
                if (!(items.getFirst().getStack().getCount() > 1)) {items.getFirst().kill();}
                else {items.getFirst().getStack().decrement(1);}
                world.spawnEntity(pigeon);
            }
            stack.decrementUnlessCreative(1, player);
            DustParticleEffect colorSmoke = new DustParticleEffect(new Vector3f(0, 100, 150), 1.0f);
            for (int i = 0; i < 50; i++) {
                world.addParticle(colorSmoke,
                        top.getX()+ 0.5 + (Math.random() - 0.5),
                        top.getY()+ 0.5 + (Math.random() - 0.5),
                        top.getZ()+ 0.5 + (Math.random() - 0.5),
                        (Math.random() - 0.5),
                        (Math.random() - 0.5),
                        (Math.random() - 0.5));
            }
            return ItemActionResult.SUCCESS;
        }
        return ItemActionResult.FAIL;
    }
}
