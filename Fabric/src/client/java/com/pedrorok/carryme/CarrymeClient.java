package com.pedrorok.carryme;

import com.pedrorok.carryme.events.ClientEvents;
import net.fabricmc.api.ClientModInitializer;

/**
 * Fabric client entry point for Carry Me mod
 *
 * @author Rok, Pedro Lucas nmm. 06/01/2026
 * @project carry-me
 */
public class CarrymeClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientEvents.register();
    }
}

