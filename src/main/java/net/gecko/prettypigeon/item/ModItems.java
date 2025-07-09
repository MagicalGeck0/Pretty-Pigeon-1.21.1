package net.gecko.prettypigeon.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.gecko.prettypigeon.PrettyPigeon;
import net.gecko.prettypigeon.entity.ModEntities;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item PIGEON_SPAWN_EGG = registerItem("pigeon_spawn_egg", new SpawnEggItem(ModEntities.PIGEON, 0x5dce87, 0xbe6682, new Item.Settings()));
    public static final Item RAD_BLEND = registerItem("rad_blend", new Item(new Item.Settings()));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(PrettyPigeon.MOD_ID, name), item);
    }

    public static void registerModItems() {
        PrettyPigeon.LOGGER.info("Registering Mod Items for " + PrettyPigeon.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> {
            entries.add(PIGEON_SPAWN_EGG);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(RAD_BLEND);
        });

    }
}
