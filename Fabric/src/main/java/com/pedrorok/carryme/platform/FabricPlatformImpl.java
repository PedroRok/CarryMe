package com.pedrorok.carryme.platform;

import com.pedrorok.carryme.CarryMeLogic;
import com.pedrorok.carryme.accessor.ICarryPlayer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import net.minecraft.world.level.gamerules.GameRules;

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

    public static final GameRule<Boolean> ALLOW_CARRY_CHOICE = GameRuleBuilder.forBoolean(true)
            .category(GameRuleCategory.PLAYER)
            .buildAndRegister(Identifier.fromNamespaceAndPath(CarryMeLogic.MOD_ID, "allow_carry_choice"));

    @Override
    public GameRule<Boolean> getAllowCarryChoiceRule() {
        return ALLOW_CARRY_CHOICE;
    }

    @Override
    public void setCarryStatus(Player player, boolean canBeCarried) {
        if (!(player instanceof ICarryPlayer carryPlayer)) return;
        carryPlayer.carry_me$setCanBeCarried(canBeCarried);
    }

    @Override
    public boolean wantsToBeCarried(Player player) {
        if (!(player instanceof ICarryPlayer carryPlayer)) return true;
        return carryPlayer.carry_me$canBeCarried();
    }
}

