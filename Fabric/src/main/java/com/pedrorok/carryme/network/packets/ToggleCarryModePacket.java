package com.pedrorok.carryme.network.packets;

import com.pedrorok.carryme.platform.CarryMePlatform;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.pedrorok.carryme.CarryMeLogic.MOD_ID;

/**
 * @author Rok, Pedro Lucas nmm. 06/01/2026
 * @project carry-me
 */
public record ToggleCarryModePacket() implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<ToggleCarryModePacket> ID =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(MOD_ID, "toggle_carry_mode"));

    public static final StreamCodec<FriendlyByteBuf, ToggleCarryModePacket> CODEC =
            StreamCodec.unit(new ToggleCarryModePacket());

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return ID;
    }

    public static void registerReceiver() {
        ServerPlayNetworking.registerGlobalReceiver(ID, (packet, context) -> {
            context.player().server.execute(() -> {
                boolean current = CarryMePlatform.getInstance().wantsToBeCarried(context.player());
                CarryMePlatform.getInstance().setWantsToBeCarried(context.player(), !current, true);
            });
        });
    }
}

