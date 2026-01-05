package com.pedrorok.carryme.events;

import com.pedrorok.carryme.KeyBindings;
import com.pedrorok.carryme.network.packets.ToggleCarryModePacket;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import static com.pedrorok.carryme.Carryme.MOD_ID;

/**
 * @author Rok, Pedro Lucas nmm. 05/01/2026
 * @project carry-me
 */
@EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(KeyBindings.TOGGLE_CARRY_MODE);
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        while (KeyBindings.TOGGLE_CARRY_MODE.consumeClick()) {
            PacketDistributor.sendToServer(new ToggleCarryModePacket());
        }
    }
}