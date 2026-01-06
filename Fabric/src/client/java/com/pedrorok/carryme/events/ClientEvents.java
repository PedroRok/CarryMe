package com.pedrorok.carryme.events;

import com.pedrorok.carryme.KeyBindings;
import com.pedrorok.carryme.network.packets.ToggleCarryModePacket;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

/**
 * @author Rok, Pedro Lucas nmm. 06/01/2026
 * @project carry-me
 */
public class ClientEvents {

    public static void register() {
        KeyBindingHelper.registerKeyBinding(KeyBindings.TOGGLE_CARRY_MODE);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (KeyBindings.TOGGLE_CARRY_MODE.consumeClick()) {
                ClientPlayNetworking.send(new ToggleCarryModePacket());
            }
        });
    }
}

