package com.pedrorok.carryme.mixin;

import com.pedrorok.carryme.platform.CarryMePlatform;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tschipp.carryon.common.carry.PickupHandler;

import java.util.function.Function;

/**
 * @author Rok, Pedro Lucas nmm. 06/01/2026
 * @project carry-me
 */
@Mixin(value = PickupHandler.class, remap = false)
public class CarryOnHelperMixin {

    @Inject(
            method = "tryPickupEntity",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private static void onTryPickUpEntity(ServerPlayer player, Entity entity, Function<Entity, Boolean> pickupCallback, CallbackInfoReturnable<Boolean> cir) {
        if (!(entity instanceof Player targetPlayer)) return;
        if (CarryMePlatform.getInstance().wantsToBeCarried(targetPlayer)) return;
        cir.setReturnValue(false);
    }
}

