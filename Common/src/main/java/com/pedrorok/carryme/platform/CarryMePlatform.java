package com.pedrorok.carryme.platform;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.gamerules.GameRule;

/**
 * @author Rok, Pedro Lucas nmm. 06/01/2026
 * @project carry-me
 */
public interface CarryMePlatform {

    static CarryMePlatform getInstance() {
        return CarryMePlatformHolder.INSTANCE;
    }

    static void setInstance(CarryMePlatform instance) {
        CarryMePlatformHolder.INSTANCE = instance;
    }

    GameRule<Boolean> getAllowCarryChoiceRule();

    void setWantsToBeCarried(Player player, boolean wantsToBeCarried, boolean isSelfChange);

    boolean wantsToBeCarried(Player player);


    default void loadPreferenceFromNBT(java.util.UUID playerId, boolean wantsToBeCarried) {

    }
}

