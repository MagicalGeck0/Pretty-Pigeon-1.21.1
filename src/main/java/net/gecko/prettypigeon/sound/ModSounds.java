package net.gecko.prettypigeon.sound;

import net.gecko.prettypigeon.PrettyPigeon;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class ModSounds {

    public static final SoundEvent ENTITY_PIGEON_AMBIENT = registerSoundEvent("entity_pigeon_ambient");

    private static SoundEvent registerSoundEvent(String name){
        Identifier id = Identifier.of(PrettyPigeon.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        PrettyPigeon.LOGGER.info("Registering Mod Sounds for "+ PrettyPigeon.MOD_ID);
    }
}
