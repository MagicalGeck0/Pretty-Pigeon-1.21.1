package net.gecko.prettypigeon.world.gen;


import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.gecko.prettypigeon.entity.ModEntities;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

public class ModEntitySpawns {
    public static void addSpawns() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.FOREST, BiomeKeys.FLOWER_FOREST, BiomeKeys.BIRCH_FOREST, BiomeKeys.WINDSWEPT_FOREST, BiomeKeys.PLAINS, BiomeKeys.DARK_FOREST),
                SpawnGroup.CREATURE, ModEntities.PIGEON, 60, 2, 7);
        SpawnRestriction.register(ModEntities.PIGEON, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
    }
}
