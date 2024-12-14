package com.github.wohaopa.GTNHModify.handler;

import java.lang.reflect.Field;
import java.util.HashMap;

import com.github.wohaopa.GTNHModify.strategies.Strategy;

import gregtech.api.recipe.RecipeMap;
import gregtech.api.util.GTRecipe;
import tectech.TecTech;
import tectech.recipe.EyeOfHarmonyRecipe;
import tectech.recipe.EyeOfHarmonyRecipeStorage;
import tectech.recipe.TecTechRecipeMaps;

@IHandler("init")
public class GregTechHandler {

    static HashMap<String, EyeOfHarmonyRecipe> recipeHashMap;

    protected static void init() {
        RecipeMap.ALL_RECIPE_MAPS.forEach(
            (s, recipeMap) -> recipeMap.getAllRecipes()
                .forEach(recipe -> Strategy.strategy.handler_GT_Recipe(recipe)));
        GTRecipe.RecipeAssemblyLine.sAssemblylineRecipes
            .forEach(gtRecipeAssemblyLine -> Strategy.strategy.handler_GT_Recipe_AssemblyLine(gtRecipeAssemblyLine));
        TecTechRecipeMaps.eyeOfHarmonyRecipes.getAllRecipes()
            .forEach(recipe -> Strategy.strategy.handler_GT_Recipe(recipe));

        try {
            Class<EyeOfHarmonyRecipeStorage> clazz = EyeOfHarmonyRecipeStorage.class;
            Field field = clazz.getDeclaredField("recipeHashMap");
            field.setAccessible(true);
            recipeHashMap = (HashMap<String, EyeOfHarmonyRecipe>) field.get(TecTech.eyeOfHarmonyRecipeStorage);
            recipeHashMap
                .forEach((s, eyeOfHarmonyRecipe) -> Strategy.strategy.handler_EyeOfHarmonyRecipe(eyeOfHarmonyRecipe));
        } catch (NoSuchFieldException | IllegalAccessException e) {}
    }

    public static int handle(Object owner, int number) {
        return Strategy.strategy.handler_GT_ProcessingTime(owner, number);
    }

}
