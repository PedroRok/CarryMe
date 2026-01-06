package com.pedrorok.carryme;

import com.pedrorok.carryme.platform.CarryMePlatform;
import com.pedrorok.carryme.platform.NeoForgePlatformImpl;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

/**
 * NeoForge entry point for Carry Me mod
 *
 * @author Rok, Pedro Lucas nmm. 06/01/2026
 * @project carry-me
 */
@Mod(CarryMeLogic.MOD_ID)
public class CarrymeNeoForge {

    public CarrymeNeoForge(IEventBus modEventBus, ModContainer modContainer) {
        // Initialize platform-specific implementation
        CarryMePlatform.setInstance(new NeoForgePlatformImpl());
    }
}

