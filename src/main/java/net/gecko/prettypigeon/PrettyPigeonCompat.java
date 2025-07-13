package net.gecko.prettypigeon;

import net.fabricmc.loader.api.FabricLoader;

public class PrettyPigeonCompat {

    public static Boolean isAetherLoaded() {
        return (FabricLoader.getInstance().isModLoaded("aether"));
    }

    public static Boolean isBumblezoneLoaded() {
        return (FabricLoader.getInstance().isModLoaded("the_bumblezone"));
    }


}
