package com.pedrorok.carryme.network.packets;

import com.pedrorok.carryme.platform.CarryMePlatform;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

import static com.pedrorok.carryme.CarryMeLogic.MOD_ID;

/**
 * @author Rok, Pedro Lucas nmm. 06/01/2026
 * @project carry-me
 */
public record ToggleCarryModePacket() implements CustomPacketPayload {

    public static final Type<ToggleCarryModePacket> TYPE = new Type<>(Identifier.fromNamespaceAndPath(MOD_ID, "toggle_carry_mode"));

    public static final StreamCodec<ByteBuf, ToggleCarryModePacket> STREAM_CODEC = StreamCodec.unit(new ToggleCarryModePacket());

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(ToggleCarryModePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                boolean current = CarryMePlatform.getInstance().wantsToBeCarried(player);
                CarryMePlatform.getInstance().setWantsToBeCarried(player, !current, true);
            }
        });
    }
}

