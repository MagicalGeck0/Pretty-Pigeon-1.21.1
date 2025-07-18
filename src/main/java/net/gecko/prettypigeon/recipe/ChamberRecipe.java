package net.gecko.prettypigeon.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public record ChamberRecipe(Ingredient inputCatalyst, Ingredient inputPowder, ItemStack outputItem) implements Recipe<ChamberRecipeInput> {
    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.add(this.inputCatalyst);
        list.add(this.inputPowder);

        return list;
    }


    @Override
    public boolean matches(ChamberRecipeInput input, World world) {
        if (world.isClient()) {
            return false;
        }

        return inputCatalyst.test(input.getStackInSlot(0)) && inputPowder.test(input.getStackInSlot(1));
    }

    @Override
    public ItemStack craft(ChamberRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return outputItem.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return outputItem;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.CHAMBER_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.CHAMBER_TYPE;
    }

    public static class Serializer implements RecipeSerializer<ChamberRecipe> {
        public static final MapCodec<ChamberRecipe> CODEC = RecordCodecBuilder.mapCodec(inst ->inst.group(
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("catalyst").forGetter(ChamberRecipe::inputCatalyst),
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("powder").forGetter(ChamberRecipe::inputPowder),
                ItemStack.CODEC.fieldOf("result").forGetter(ChamberRecipe::outputItem)
        ).apply(inst, ChamberRecipe::new));
        public static final PacketCodec<RegistryByteBuf, ChamberRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, ChamberRecipe::inputCatalyst,
                        Ingredient.PACKET_CODEC, ChamberRecipe::inputPowder,
                        ItemStack.PACKET_CODEC, ChamberRecipe::outputItem,
                        ChamberRecipe::new);

        @Override
        public MapCodec<ChamberRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, ChamberRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}

