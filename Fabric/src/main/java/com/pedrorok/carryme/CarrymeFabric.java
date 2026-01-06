package com.pedrorok.carryme;

import com.pedrorok.carryme.commands.CarryMeCommand;
import com.pedrorok.carryme.network.NetworkHandler;
import com.pedrorok.carryme.platform.CarryMePlatform;
import com.pedrorok.carryme.platform.FabricPlatformImpl;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

/**
 * Fabric entry point for Carry Me mod
 *
 * @author Rok, Pedro Lucas nmm. 06/01/2026
 * @project carry-me
 */
public class CarrymeFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        // Initialize platform-specific implementation
        CarryMePlatform.setInstance(new FabricPlatformImpl());

        NetworkHandler.registerPackets();

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
                CarryMeCommand.register(dispatcher)
        );
    }
}

