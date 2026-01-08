package com.pedrorok.carryme.network;

import com.pedrorok.carryme.network.packets.ToggleCarryModePacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.SimpleChannel;

import static com.pedrorok.carryme.CarryMeLogic.MOD_ID;

/**
 * Network handler for Forge
 *
 * @author Rok, Pedro Lucas nmm. 06/01/2026
 * @project carry-me
 */
@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NetworkHandler {

    private static final int PROTOCOL_VERSION = 1;
    private static SimpleChannel INSTANCE;

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            INSTANCE = ChannelBuilder
                    .named(ResourceLocation.fromNamespaceAndPath(MOD_ID, "main"))
                    .networkProtocolVersion(PROTOCOL_VERSION)
                    .simpleChannel();

            INSTANCE.messageBuilder(ToggleCarryModePacket.class)
                    .encoder(ToggleCarryModePacket::encode)
                    .decoder(ToggleCarryModePacket::decode)
                    .consumerMainThread(ToggleCarryModePacket::handle)
                    .add();
        });
    }

    public static void sendToServer(Object message) {
        INSTANCE.send(message, PacketDistributor.SERVER.noArg());
    }
}

