package com.pedrorok.carryme.platform;

import com.pedrorok.carryme.CarryMeLogic;
import com.pedrorok.carryme.commands.CarryMeCommand;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

/**
 * NeoForge-specific platform implementation
 *
 * @author Rok, Pedro Lucas nmm. 06/01/2026
 * @project carry-me
 */
@EventBusSubscriber(modid = CarryMeLogic.MOD_ID)
public class NeoForgePlatformImpl implements CarryMePlatform {

    private static final String WANNA_BE_CARRIED_KEY = CarryMeLogic.MOD_ID + ":wantsToBeCarried";

    public static final GameRules.Key<GameRules.BooleanValue> ALLOW_CARRY_CHOICE =
            GameRules.register("allowCarryChoice", GameRules.Category.PLAYER, GameRules.BooleanValue.create(true));

    @Override
    public GameRules.Key<GameRules.BooleanValue> getAllowCarryChoiceRule() {
        return ALLOW_CARRY_CHOICE;
    }

    @Override
    public void setWantsToBeCarried(Player player, boolean wantsToBeCarried, boolean isSelfChange) {
        if (!CarryMeLogic.canChangeCarryPreference(player, ALLOW_CARRY_CHOICE, isSelfChange)) {
            return;
        }

        CompoundTag persistentData = player.getPersistentData();
        boolean current = !persistentData.contains(WANNA_BE_CARRIED_KEY) || persistentData.getBoolean(WANNA_BE_CARRIED_KEY);
        persistentData.putBoolean(WANNA_BE_CARRIED_KEY, wantsToBeCarried);
        CarryMeLogic.sendStatusMessage(player, wantsToBeCarried, current);
    }

    @Override
    public boolean wantsToBeCarried(Player player) {
        CompoundTag persistentData = player.getPersistentData();
        return !persistentData.contains(WANNA_BE_CARRIED_KEY) || persistentData.getBoolean(WANNA_BE_CARRIED_KEY);
    }

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        CarryMeCommand.register(event.getDispatcher());
    }
}

