package net.gecko.prettypigeon.compat;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.client.registry.transfer.TransferHandlerRegistry;
import me.shedaniel.rei.api.client.registry.transfer.simple.SimpleTransferHandler;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.gecko.prettypigeon.block.ModBlocks;
import net.gecko.prettypigeon.recipe.ChamberRecipe;
import net.gecko.prettypigeon.recipe.ModRecipes;
import net.gecko.prettypigeon.screen.custom.ChamberScreen;
import net.gecko.prettypigeon.screen.custom.ChamberScreenHandler;



public class PrettyPigeonREIClient implements REIClientPlugin {
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new ChamberCategory());

        registry.addWorkstations(ChamberCategory.CHAMBER, EntryStacks.of(ModBlocks.RADIATION_CHAMBER));
    }

    @Override public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(ChamberRecipe.class,
                ModRecipes.CHAMBER_TYPE, ChamberDisplay::new);
    }

    @Override
    public void registerTransferHandlers(TransferHandlerRegistry registry) {
        registry.register(SimpleTransferHandler.create(
                ChamberScreenHandler.class,
                ChamberCategory.CHAMBER,
                new SimpleTransferHandler.IntRange(0,2)
        ));
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerClickArea(screen -> new Rectangle(((screen.width - 176) / 2) + 73,
                ((screen.height - 166) / 2) + 30, 20, 25), ChamberScreen.class,
                ChamberCategory.CHAMBER);
    }
}
