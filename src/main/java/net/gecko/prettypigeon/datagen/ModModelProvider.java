package net.gecko.prettypigeon.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.gecko.prettypigeon.block.ModBlocks;
import net.gecko.prettypigeon.item.ModItems;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RADIATION_CHAMBER);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.PIGEON_SPAWN_EGG , new Model(Optional.of(Identifier.of("item/template_spawn_egg")),Optional.empty()));
        itemModelGenerator.register(ModItems.RAD_BLEND ,Models.GENERATED);
        itemModelGenerator.register(ModItems.WORM ,Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_WORM ,Models.GENERATED);
        itemModelGenerator.register(ModItems.SIFTER ,Models.GENERATED);
        itemModelGenerator.register(ModItems.RAD_STAR ,Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLDEN_WORM ,Models.GENERATED);

    }
}
