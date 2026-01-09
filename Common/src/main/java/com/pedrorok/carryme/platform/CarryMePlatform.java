package com.pedrorok.carryme.platform;

import com.pedrorok.carryme.CarryMeLogic;
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

    default void setWantsToBeCarried(Player player, boolean wantsToBeCarried, boolean isSelfChange) {
        if (!CarryMeLogic.canChangeCarryPreference(player, isSelfChange, player.level().getServer().getWorldData().getGameRules().get(getAllowCarryChoiceRule()))) {
            return;
        }

        var current = wantsToBeCarried(player);
        setCarryStatus(player, wantsToBeCarried);

        CarryMeLogic.sendStatusMessage(player, wantsToBeCarried, current);
    }

    boolean wantsToBeCarried(Player player);

    void setCarryStatus(Player player, boolean canBeCarried);
}

