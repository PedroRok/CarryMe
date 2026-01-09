package com.pedrorok.carryme.events;

import com.pedrorok.carryme.KeyBindings;
import com.pedrorok.carryme.network.NetworkHandler;
import com.pedrorok.carryme.network.packets.ToggleCarryModePacket;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.pedrorok.carryme.CarryMeLogic.MOD_ID;

/**
 * @author Rok, Pedro Lucas nmm. 06/01/2026
 * @project carry-me
 */
@Mod.EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvents {

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(KeyBindings.TOGGLE_CARRY_MODE);
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class GameEvents {
        @SubscribeEvent
        public static void onClientTick(TickEvent.LevelTickEvent.Post event) {
            while (KeyBindings.TOGGLE_CARRY_MODE.consumeClick()) {
                NetworkHandler.sendToServer(new ToggleCarryModePacket());
            }
        }
    }
}

