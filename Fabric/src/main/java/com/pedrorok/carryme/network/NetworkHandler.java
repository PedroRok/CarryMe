package com.pedrorok.carryme.network;

import com.pedrorok.carryme.network.packets.ToggleCarryModePacket;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

/**
 * @author Rok, Pedro Lucas nmm. 06/01/2026
 * @project carry-me
 */
public class NetworkHandler {

    public static void registerPackets() {
        PayloadTypeRegistry.playC2S().register(ToggleCarryModePacket.ID, ToggleCarryModePacket.CODEC);
        ToggleCarryModePacket.registerReceiver();
    }
}

