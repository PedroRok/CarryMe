package com.pedrorok.carryme.platform;

import com.pedrorok.carryme.CarryMeLogic;
import com.pedrorok.carryme.ModRegistry;
import com.pedrorok.carryme.commands.CarryMeCommand;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.gamerules.GameRule;
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

    @Override
    public GameRule<Boolean> getAllowCarryChoiceRule() {
        return ModRegistry.ALLOW_CARRY_CHOICE.get();
    }

    @Override
    public boolean wantsToBeCarried(Player player) {
        CompoundTag persistentData = player.getPersistentData();
        return !persistentData.contains(WANNA_BE_CARRIED_KEY) || persistentData.getBoolean(WANNA_BE_CARRIED_KEY).get();
    }

    @Override
    public void setCarryStatus(Player player, boolean canBeCarried) {
        CompoundTag persistentData = player.getPersistentData();
        persistentData.putBoolean(WANNA_BE_CARRIED_KEY, canBeCarried);
    }

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        CarryMeCommand.register(event.getDispatcher());
    }
}

