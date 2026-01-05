package com.pedrorok.carryme;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import tschipp.carryon.common.carry.CarryOnDataManager;

/**
 * @author Rok, Pedro Lucas nmm. 05/01/2026
 * @project carry-me
 */
@Mod(Carryme.MOD_ID)
public class Carryme {

    public static final String MOD_ID = "carryme";

    private static final String WANNA_BE_CARRIED_KEY = MOD_ID + ":wantsToBeCarried";

    public static final GameRules.Key<GameRules.BooleanValue> ALLOW_CARRY_CHOICE =
            GameRules.register("allowCarryChoice", GameRules.Category.PLAYER, GameRules.BooleanValue.create(true));


    public Carryme(IEventBus modEventBus, ModContainer modContainer) {
    }

    public static void setWantsToBeCarried(Player player, boolean wantsToBeCarried, boolean isSelfChange) {
        boolean canChoose = player.level().getGameRules().getBoolean(Carryme.ALLOW_CARRY_CHOICE);

        if (!canChoose && isSelfChange && !player.hasPermissions(2)) {
            player.sendSystemMessage(Component.translatable("carryme.message.cant_change_carry_mode").withColor(0xfc5454));
            player.playNotifySound(SoundEvents.VILLAGER_NO, SoundSource.PLAYERS,0.5f, 1.5f);
            return;
        }

        CompoundTag persistentData = player.getPersistentData();
        boolean current = persistentData.getBoolean(WANNA_BE_CARRIED_KEY);
        persistentData.putBoolean(WANNA_BE_CARRIED_KEY, wantsToBeCarried);
        sendStatusMessage(player, wantsToBeCarried, current);
    }

    private static void sendStatusMessage(Player player, boolean enabled, boolean current) {
        if (enabled == current) return;
        if (enabled) {
            player.displayClientMessage(Component.translatable("carryme.message.carry_enabled").withColor(0x54fc54), true);
            player.playNotifySound(SoundEvents.VILLAGER_YES, SoundSource.PLAYERS,0.5f, 1.5f);
            return;
        }
        player.displayClientMessage(Component.translatable("carryme.message.carry_disabled").withColor(0xfcfc54), true);
        player.playNotifySound(SoundEvents.VILLAGER_NO, SoundSource.PLAYERS,0.5f, 1.5f);
    }

    public static boolean wantsToBeCarried(Player player) {
        CompoundTag persistentData = player.getPersistentData();
        return persistentData.getBoolean(WANNA_BE_CARRIED_KEY);
    }
}
