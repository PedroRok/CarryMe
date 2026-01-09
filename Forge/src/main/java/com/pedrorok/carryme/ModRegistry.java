package com.pedrorok.carryme;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.serialization.Codec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import net.minecraft.world.level.gamerules.GameRuleType;
import net.minecraft.world.level.gamerules.GameRuleTypeVisitor;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/*
 * @author Lucasmellof, Lucas de Mello Freitas created on 08/01/2026
 */
public class ModRegistry {
    private static final DeferredRegister<GameRule<?>> GAME_RULES = DeferredRegister.create(BuiltInRegistries.GAME_RULE.key(), CarryMeLogic.MOD_ID);

    public static final RegistryObject<GameRule<Boolean>> ALLOW_CARRY_CHOICE = GAME_RULES.register("allow_carry_choice",
            () -> new GameRule<>(
                    GameRuleCategory.PLAYER,
                    GameRuleType.BOOL,
                    BoolArgumentType.bool(),
                    GameRuleTypeVisitor::visitBoolean,
                    Codec.BOOL,
                    it -> it ? 1 : 0,
                    true,
                    FeatureFlagSet.of()
            ));


    public static void init(BusGroup bus) {
        GAME_RULES.register(bus);
    }
}
