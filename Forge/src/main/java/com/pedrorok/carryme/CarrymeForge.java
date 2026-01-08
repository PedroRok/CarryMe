package com.pedrorok.carryme;

import com.pedrorok.carryme.platform.CarryMePlatform;
import com.pedrorok.carryme.platform.ForgePlatformImpl;
import net.minecraftforge.fml.common.Mod;

/**
 * Forge entry point for Carry Me mod
 *
 * @author Rok, Pedro Lucas nmm. 06/01/2026
 * @project carry-me
 */
@Mod(CarryMeLogic.MOD_ID)
public class CarrymeForge {

    public CarrymeForge() {
        // Initialize platform-specific implementation
        CarryMePlatform.setInstance(new ForgePlatformImpl());
    }
}

