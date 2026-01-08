package com.pedrorok.carryme.events;

import com.pedrorok.carryme.platform.CarryMePlatform;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import tschipp.carryon.events.EntityPickupEvent;

import static com.pedrorok.carryme.CarryMeLogic.MOD_ID;

/**
 * @author Rok, Pedro Lucas nmm. 06/01/2026
 * @project carry-me
 */
@Mod.EventBusSubscriber(modid = MOD_ID)
public class CarryOnEvents {

    @SubscribeEvent
    public static void onCarryEntity(EntityPickupEvent event) {
        if (!(event.target instanceof Player player)) return;
        if (CarryMePlatform.getInstance().wantsToBeCarried(player)) return;
        event.setCanceled(true);
    }
}

