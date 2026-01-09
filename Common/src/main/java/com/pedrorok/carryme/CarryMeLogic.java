package com.pedrorok.carryme;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.permissions.Permissions;
import net.minecraft.sounds.SoundEvents;import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;

/**
 * @author Rok, Pedro Lucas nmm. 06/01/2026
 * @project carry-me
 */
public class CarryMeLogic {

    public static final String MOD_ID = "carryme";

    public static Identifier of(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    public static void sendStatusMessage(Player player, boolean enabled, boolean current) {
        if (enabled == current) return;
        if (enabled) {
            player.displayClientMessage(Component.translatable("carryme.message.carry_enabled").withColor(0x54fc54), true);
            player.playSound(SoundEvents.VILLAGER_YES,0.5f, 1.5f);
            return;
        }
        player.displayClientMessage(Component.translatable("carryme.message.carry_disabled").withColor(0xfcfc54), true);
        player.playSound(SoundEvents.VILLAGER_NO, 0.5f, 1.5f);
    }

    public static boolean canChangeCarryPreference(Player player, boolean isSelfChange, boolean canChoose) {
        if (!canChoose && isSelfChange && !(player.permissions().hasPermission(Permissions.COMMANDS_MODERATOR))) {
            player.displayClientMessage(Component.translatable("carryme.message.cant_change_carry_mode").withColor(0xfc5454), false);
            player.playSound(SoundEvents.VILLAGER_NO, 0.5f, 1.5f);
            return false;
        }

        return true;
    }


}

