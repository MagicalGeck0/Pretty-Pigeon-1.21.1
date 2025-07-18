package net.gecko.prettypigeon.block.entity;

import net.gecko.prettypigeon.PrettyPigeon;
import net.gecko.prettypigeon.block.ModBlocks;
import net.gecko.prettypigeon.block.entity.custom.ChamberBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Random;

public class ModBlockEntities {
    public static final BlockEntityType<ChamberBlockEntity> CHAMBER_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(PrettyPigeon.MOD_ID, "chamber_be"),
                    BlockEntityType.Builder.create(ChamberBlockEntity::new, ModBlocks.RADIATION_CHAMBER).build(null));


    public static void registerBlockEntities() {
        PrettyPigeon.LOGGER.info("Registering Block Entities for " + PrettyPigeon.MOD_ID);
    }
}
