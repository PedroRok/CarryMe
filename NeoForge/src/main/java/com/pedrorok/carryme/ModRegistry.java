package com.pedrorok.carryme;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.serialization.Codec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.gamerules.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/*
 * @author Lucasmellof, Lucas de Mello Freitas created on 08/01/2026
 */
public class ModRegistry {
	private static final DeferredRegister<GameRule<?>> GAME_RULES = DeferredRegister.create(BuiltInRegistries.GAME_RULE, CarryMeLogic.MOD_ID);

	public static final DeferredHolder<GameRule<?>, GameRule<Boolean>> ALLOW_CARRY_CHOICE = GAME_RULES.register("allow_carry_choice",
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


	public static void init(IEventBus bus) {
		GAME_RULES.register(bus);
	}
}
