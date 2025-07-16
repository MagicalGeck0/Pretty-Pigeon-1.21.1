package net.gecko.prettypigeon;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.gecko.prettypigeon.entity.ModEntities;
import net.gecko.prettypigeon.entity.custom.PigeonEntity;
import net.gecko.prettypigeon.item.ModItems;
import net.gecko.prettypigeon.sound.ModSounds;
import net.gecko.prettypigeon.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static net.gecko.prettypigeon.entity.ModEntities.PIGEON;

// commit test
public class PrettyPigeon implements ModInitializer {
	public static final String MOD_ID = "pretpig";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModEntities.registerModEntities();
		FabricDefaultAttributeRegistry.register(PIGEON, PigeonEntity.createMobAttributes());

		ModSounds.registerSounds();
		ModItems.registerModItems();
		ModWorldGeneration.generateModWorldGen();
	}
}