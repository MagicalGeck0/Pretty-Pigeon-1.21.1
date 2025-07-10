package net.gecko.prettypigeon.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent WORM = new FoodComponent.Builder().nutrition(1).saturationModifier(0.1f).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 200), 0.5f).build();
    public static final FoodComponent COOKED_WORM = new FoodComponent.Builder().nutrition(2).saturationModifier(0.2f).snack().build();
}
