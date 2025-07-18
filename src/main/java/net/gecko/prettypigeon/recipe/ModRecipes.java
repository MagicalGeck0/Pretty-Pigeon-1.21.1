package net.gecko.prettypigeon.recipe;

import net.gecko.prettypigeon.PrettyPigeon;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static final RecipeSerializer<ChamberRecipe> CHAMBER_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(PrettyPigeon.MOD_ID, "radiation_chamber"),
                new ChamberRecipe.Serializer());
    public static final RecipeType<ChamberRecipe> CHAMBER_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(PrettyPigeon.MOD_ID, "radiation_chamber"), new RecipeType<ChamberRecipe>() {
                @Override
                public String toString() {
                    return "radiation_chamber";
                }});

    public static void registerRecipes() {
        PrettyPigeon.LOGGER.info("Registering Custom Recipes for " + PrettyPigeon.MOD_ID);
    }
}
