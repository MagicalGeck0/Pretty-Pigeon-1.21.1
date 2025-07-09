package net.gecko.prettypigeon.world.gen;


import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.gecko.prettypigeon.entity.ModEntities;
import net.gecko.prettypigeon.entity.custom.PigeonEntity;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

public class ModEntitySpawns {
    public static void addSpawns() {
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(),
                SpawnGroup.CREATURE, ModEntities.PIGEON, 60, 2, 7);

        SpawnRestriction.register(ModEntities.PIGEON, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
    }
}
