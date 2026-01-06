package com.pedrorok.carryme;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;

/**
 * Platform-independent logic for the Carry Me mod
 *
 * @author Rok, Pedro Lucas nmm. 06/01/2026
 * @project carry-me
 */
public class CarryMeLogic {

    public static final String MOD_ID = "carryme";

    /**
     * Sends status message to player about their carry mode preference
     */
    public static void sendStatusMessage(Player player, boolean enabled, boolean current) {
        if (enabled == current) return;
        if (enabled) {
            player.displayClientMessage(Component.translatable("carryme.message.carry_enabled").withColor(0x54fc54), true);
            player.playNotifySound(SoundEvents.VILLAGER_YES, SoundSource.PLAYERS, 0.5f, 1.5f);
            return;
        }
        player.displayClientMessage(Component.translatable("carryme.message.carry_disabled").withColor(0xfcfc54), true);
        player.playNotifySound(SoundEvents.VILLAGER_NO, SoundSource.PLAYERS, 0.5f, 1.5f);
    }

    /**
     * Checks if a player can change their carry preference based on game rules
     */
    public static boolean canChangeCarryPreference(Player player, GameRules.Key<GameRules.BooleanValue> allowCarryChoiceRule, boolean isSelfChange) {
        boolean canChoose = player.level().getGameRules().getBoolean(allowCarryChoiceRule);

        if (!canChoose && isSelfChange && !player.hasPermissions(2)) {
            player.sendSystemMessage(Component.translatable("carryme.message.cant_change_carry_mode").withColor(0xfc5454));
            player.playNotifySound(SoundEvents.VILLAGER_NO, SoundSource.PLAYERS, 0.5f, 1.5f);
            return false;
        }

        return true;
    }
}

