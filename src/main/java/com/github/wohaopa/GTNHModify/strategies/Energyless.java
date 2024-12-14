package com.github.wohaopa.GTNHModify.strategies;

import gregtech.api.util.GTRecipe;

public class Energyless extends Strategy {

    protected Energyless() {}

    @Override
    public void handler_GT_Recipe(GTRecipe gtRecipe) {
        if (gtRecipe.mEUt > 0) gtRecipe.mEUt = 0;
    }
}
