package net.gecko.prettypigeon.compat;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.gecko.prettypigeon.recipe.ChamberRecipe;
import net.minecraft.recipe.RecipeEntry;

import java.util.List;
import java.util.stream.Stream;

public class ChamberDisplay extends BasicDisplay {
    public ChamberDisplay(RecipeEntry<ChamberRecipe> recipe) {
        super(List.of(
                EntryIngredients.ofIngredient(recipe.value().getIngredients().get(0)),
                EntryIngredients.ofIngredient(recipe.value().getIngredients().get(1))
                ),

                List.of(EntryIngredient.of(EntryStacks.of(recipe.value().getResult(null)))));
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return ChamberCategory.CHAMBER;
    }
}
