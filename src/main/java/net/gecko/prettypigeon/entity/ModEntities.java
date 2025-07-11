package net.gecko.prettypigeon.entity;

import net.gecko.prettypigeon.PrettyPigeon;
import net.gecko.prettypigeon.entity.custom.PigeonEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final EntityType<PigeonEntity> PIGEON = Registry.register(Registries.ENTITY_TYPE, Identifier.of(PrettyPigeon.MOD_ID, "pigeon"),EntityType.Builder.create(PigeonEntity::new, SpawnGroup.CREATURE).dimensions(0.5f,0.5f).build());

    public static void registerModEntities(){
        PrettyPigeon.LOGGER.info("registering mod entities for " + PrettyPigeon.MOD_ID);
    }
}
