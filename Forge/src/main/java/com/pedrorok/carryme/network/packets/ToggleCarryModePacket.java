package com.pedrorok.carryme.network.packets;

import com.pedrorok.carryme.platform.CarryMePlatform;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.network.CustomPayloadEvent;

/**
 * @author Rok, Pedro Lucas nmm. 06/01/2026
 * @project carry-me
 */
public class ToggleCarryModePacket {

    public ToggleCarryModePacket() {
    }

    public static void encode(ToggleCarryModePacket packet, FriendlyByteBuf buffer) {
        // No data to encode
    }

    public static ToggleCarryModePacket decode(FriendlyByteBuf buffer) {
        return new ToggleCarryModePacket();
    }

    public static void handle(ToggleCarryModePacket packet, CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            if (context.getSender() instanceof ServerPlayer player) {
                boolean current = CarryMePlatform.getInstance().wantsToBeCarried(player);
                CarryMePlatform.getInstance().setWantsToBeCarried(player, !current, true);
            }
        });
        context.setPacketHandled(true);
    }
}

