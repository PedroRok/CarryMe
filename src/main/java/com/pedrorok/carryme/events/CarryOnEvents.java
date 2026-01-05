package com.pedrorok.carryme.events;

import com.pedrorok.carryme.Carryme;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import tschipp.carryon.events.EntityPickupEvent;

import static com.pedrorok.carryme.Carryme.MOD_ID;

/**
 * @author Rok, Pedro Lucas nmm. 05/01/2026
 * @project carry-me
 */
@EventBusSubscriber(modid = MOD_ID)
public class CarryOnEvents {

    @SubscribeEvent
    public static void onCarryEntity(EntityPickupEvent event) {
        if (!(event.target instanceof Player player)) return;
        if (Carryme.wantsToBeCarried(player)) return;
        event.setCanceled(true);
    }
}
