package com.pedrorok.carryme.mixin;

import com.pedrorok.carryme.platform.CarryMePlatform;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Rok, Pedro Lucas nmm. 06/01/2026
 * @project carry-me
 */
@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void onReadAdditionalSaveData(CompoundTag nbt, CallbackInfo ci) {
        ServerPlayer player = (ServerPlayer) (Object) this;

        if (nbt.contains("CarryMeData")) {
            CompoundTag carryMeData = nbt.getCompound("CarryMeData");
            boolean wantsToBeCarried = carryMeData.getBoolean("wantsToBeCarried");

            CarryMePlatform.getInstance().loadPreferenceFromNBT(player.getUUID(), wantsToBeCarried);
        } else {
            CarryMePlatform.getInstance().loadPreferenceFromNBT(player.getUUID(), true);
        }
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void onAddAdditionalSaveData(CompoundTag nbt, CallbackInfo ci) {
        ServerPlayer player = (ServerPlayer) (Object) this;

        boolean wantsToBeCarried = CarryMePlatform.getInstance().wantsToBeCarried(player);

        CompoundTag carryMeData = new CompoundTag();
        carryMeData.putBoolean("wantsToBeCarried", wantsToBeCarried);
        nbt.put("CarryMeData", carryMeData);
    }
}

