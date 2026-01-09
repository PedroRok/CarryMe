package com.pedrorok.carryme;

import com.pedrorok.carryme.platform.CarryMePlatform;
import com.pedrorok.carryme.platform.ForgePlatformImpl;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * @author Rok, Pedro Lucas nmm. 06/01/2026
 * @project carry-me
 */
@Mod(CarryMeLogic.MOD_ID)
public class CarrymeForge {

    public CarrymeForge(FMLJavaModLoadingContext context) {
        ModRegistry.init(context.getModBusGroup());
        CarryMePlatform.setInstance(new ForgePlatformImpl());
    }
}

