package net.gecko.prettypigeon;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class PrettyPigeonCompat {

    public static Item loadItem(String nameSpace, String path){
        return Registries.ITEM.get(Identifier.of(nameSpace, path));
    }

    public static Boolean isLoaded(String id) {
        return (FabricLoader.getInstance().isModLoaded(id));
    }
}
