package net.gecko.prettypigeon.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record ChamberRecipeInput(ItemStack catalyst, ItemStack powder) implements RecipeInput {
    @Override
    public ItemStack getStackInSlot(int slot) {
        ItemStack stack;
        switch(slot) {
            case 0 -> stack = this.catalyst;
            case 1 -> stack = this.powder;
            default -> throw new IllegalArgumentException("Recipe does not contain slot " + slot);
        }
        return stack;
    }

    @Override
    public int getSize() {
        return 2;
    }
}
