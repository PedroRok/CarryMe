package com.pedrorok.carryme.network;

import com.pedrorok.carryme.network.packets.ToggleCarryModePacket;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import static com.pedrorok.carryme.CarryMeLogic.MOD_ID;

/**
 * Network handler for NeoForge
 *
 * @author Rok, Pedro Lucas nmm. 06/01/2026
 * @project carry-me
 */
@EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class NetworkHandler {

    @SubscribeEvent
    public static void registerPayloads(final RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");
        registrar.playToServer(
                ToggleCarryModePacket.TYPE,
                ToggleCarryModePacket.STREAM_CODEC,
                ToggleCarryModePacket::handle
        );
    }
}

