package net.gecko.prettypigeon.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.gecko.prettypigeon.PrettyPigeon;
import net.gecko.prettypigeon.block.custom.ChamberBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block RADIATION_CHAMBER = registerBlock("radiation_chamber",
            new ChamberBlock(AbstractBlock.Settings.create()));


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(PrettyPigeon.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(PrettyPigeon.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        PrettyPigeon.LOGGER.info("Registering Mod Blocks for " + PrettyPigeon.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.add(RADIATION_CHAMBER);
        });
    }
}
