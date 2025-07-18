package net.gecko.prettypigeon.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.gecko.prettypigeon.PrettyPigeon;
import net.gecko.prettypigeon.screen.custom.ChamberScreenHandler;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers {
    public static final ScreenHandlerType<ChamberScreenHandler> CHAMBER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(PrettyPigeon.MOD_ID, "chamber_screen_handler"),
                    new ExtendedScreenHandlerType<>(ChamberScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void registerScreenHandlers() {
        PrettyPigeon.LOGGER.info("Registering Screen Handlers for " + PrettyPigeon.MOD_ID);
    }
}
