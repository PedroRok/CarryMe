package com.pedrorok.carryme.network.packets;

import com.pedrorok.carryme.Carryme;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static com.pedrorok.carryme.Carryme.MOD_ID;

public record ToggleCarryModePacket() implements CustomPacketPayload {

    public static final Type<ToggleCarryModePacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(MOD_ID, "toggle_carry_mode"));

    public static final StreamCodec<ByteBuf, ToggleCarryModePacket> STREAM_CODEC = StreamCodec.unit(new ToggleCarryModePacket());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(ToggleCarryModePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                boolean current = Carryme.wantsToBeCarried(player);
                Carryme.setWantsToBeCarried(player, !current, true);
            }
        });
    }
}