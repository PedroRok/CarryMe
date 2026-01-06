package com.pedrorok.carryme.platform;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;

/**
 * Platform-specific abstraction for Carry Me mod
 * This interface is implemented differently for Fabric and NeoForge
 *
 * @author Rok, Pedro Lucas nmm. 06/01/2026
 * @project carry-me
 */
public interface CarryMePlatform {

    /**
     * Get the platform-specific instance
     */
    static CarryMePlatform getInstance() {
        return CarryMePlatformHolder.INSTANCE;
    }

    /**
     * Set the platform-specific instance (called during mod initialization)
     */
    static void setInstance(CarryMePlatform instance) {
        CarryMePlatformHolder.INSTANCE = instance;
    }

    /**
     * Get the game rule for allowing carry choice
     */
    GameRules.Key<GameRules.BooleanValue> getAllowCarryChoiceRule();

    /**
     * Set whether a player wants to be carried
     */
    void setWantsToBeCarried(Player player, boolean wantsToBeCarried, boolean isSelfChange);

    /**
     * Check if a player wants to be carried
     */
    boolean wantsToBeCarried(Player player);

    /**
     * Load player preference from NBT (Fabric only)
     */
    default void loadPreferenceFromNBT(java.util.UUID playerId, boolean wantsToBeCarried) {
        // Optional: Fabric implementation may use this
    }
}

