package com.pedrorok.carryme.platform;

import com.pedrorok.carryme.CarryMeLogic;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Fabric-specific platform implementation
 *
 * @author Rok, Pedro Lucas nmm. 06/01/2026
 * @project carry-me
 */
public class FabricPlatformImpl implements CarryMePlatform {

    private static final Map<UUID, Boolean> carryPreferences = new HashMap<>();

    public static final GameRules.Key<GameRules.BooleanValue> ALLOW_CARRY_CHOICE =
            GameRuleRegistry.register("allowCarryChoice", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));

    public FabricPlatformImpl() {
        // Register player copy event to preserve preferences across respawns
        ServerPlayerEvents.COPY_FROM.register((oldPlayer, newPlayer, alive) -> {
            UUID playerId = newPlayer.getUUID();
            if (carryPreferences.containsKey(oldPlayer.getUUID())) {
                carryPreferences.put(playerId, carryPreferences.get(oldPlayer.getUUID()));
            }
        });
    }

    @Override
    public GameRules.Key<GameRules.BooleanValue> getAllowCarryChoiceRule() {
        return ALLOW_CARRY_CHOICE;
    }

    @Override
    public void setWantsToBeCarried(Player player, boolean wantsToBeCarried, boolean isSelfChange) {
        if (!CarryMeLogic.canChangeCarryPreference(player, ALLOW_CARRY_CHOICE, isSelfChange)) {
            return;
        }

        UUID playerId = player.getUUID();
        boolean current = carryPreferences.getOrDefault(playerId, true);

        carryPreferences.put(playerId, wantsToBeCarried);

        CarryMeLogic.sendStatusMessage(player, wantsToBeCarried, current);
    }

    @Override
    public boolean wantsToBeCarried(Player player) {
        UUID playerId = player.getUUID();
        return carryPreferences.getOrDefault(playerId, true);
    }

    @Override
    public void loadPreferenceFromNBT(UUID playerId, boolean wantsToBeCarried) {
        carryPreferences.put(playerId, wantsToBeCarried);
    }
}

