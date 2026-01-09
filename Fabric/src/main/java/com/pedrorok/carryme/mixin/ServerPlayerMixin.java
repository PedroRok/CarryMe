package com.pedrorok.carryme.mixin;

import com.pedrorok.carryme.platform.CarryMePlatform;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
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
    private void onReadAdditionalSaveData(ValueInput valueInput, CallbackInfo ci) {
        ServerPlayer player = (ServerPlayer) (Object) this;
        CarryMePlatform.getInstance().loadPreferenceFromNBT(player.getUUID(), valueInput.getBooleanOr("wantsToBeCarried", true));
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void onAddAdditionalSaveData(ValueOutput valueOutput, CallbackInfo ci) {
        ServerPlayer player = (ServerPlayer) (Object) this;

        boolean wantsToBeCarried = CarryMePlatform.getInstance().wantsToBeCarried(player);

        valueOutput.putBoolean("wantsToBeCarried", wantsToBeCarried);
    }
}

