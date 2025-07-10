package net.gecko.prettypigeon.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.gecko.prettypigeon.PrettyPigeon;
import net.gecko.prettypigeon.entity.ModEntities;
import net.gecko.prettypigeon.item.custom.SifterItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item PIGEON_SPAWN_EGG = registerItem("pigeon_spawn_egg", new SpawnEggItem(ModEntities.PIGEON, 0x5dce87, 0xbe6682, new Item.Settings()));
    public static final Item RAD_BLEND = registerItem("rad_blend", new Item(new Item.Settings()));
    public static final Item WORM = registerItem("worm", new Item(new Item.Settings().food(ModFoodComponents.WORM)));
    public static final Item COOKED_WORM = registerItem("cooked_worm", new Item(new Item.Settings().food(ModFoodComponents.COOKED_WORM)));
    public static final Item RAD_STAR = registerItem("rad_star", new Item(new Item.Settings()));
    public static final Item GOLDEN_WORM = registerItem("golden_worm", new Item(new Item.Settings().food(ModFoodComponents.GOLDEN_WORM)));

    public static final Item SIFTER = registerItem("sifter", new SifterItem(new Item.Settings().maxDamage(32)));

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
            entries.add(RAD_STAR);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(SIFTER);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(WORM);
            entries.add(COOKED_WORM);
            entries.add(GOLDEN_WORM);
        });

    }
}
