package com.pedrorok.carryme.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.pedrorok.carryme.platform.CarryMePlatform;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.TeamArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.scores.PlayerTeam;

import java.util.Collection;

/**
 * Common command logic for both Fabric and NeoForge
 *
 * @author Rok, Pedro Lucas nmm. 06/01/2026
 * @project carry-me
 */
public class CarryMeCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("carryme")
                .executes(CarryMeCommand::toggleSelf)
                .then(Commands.argument("enabled", BoolArgumentType.bool())
                        .executes(CarryMeCommand::setSelf))
                .then(Commands.argument("target", EntityArgument.players())
                        .requires(source -> source.hasPermission(2))
                        .executes(CarryMeCommand::toggleTargets)
                        .then(Commands.argument("enabled", BoolArgumentType.bool())
                                .executes(CarryMeCommand::setTargets)))
                .then(Commands.argument("team", TeamArgument.team())
                        .requires(source -> source.hasPermission(2))
                        .executes(CarryMeCommand::toggleTeam)
                        .then(Commands.argument("enabled", BoolArgumentType.bool())
                                .executes(CarryMeCommand::setTeam)))
        );
    }

    private static int toggleSelf(CommandContext<CommandSourceStack> context) {
        Player player = context.getSource().getPlayer();
        if (player == null) return 0;

        boolean current = CarryMePlatform.getInstance().wantsToBeCarried(player);
        CarryMePlatform.getInstance().setWantsToBeCarried(player, !current, true);
        return 1;
    }

    private static int setSelf(CommandContext<CommandSourceStack> context) {
        Player player = context.getSource().getPlayer();
        if (player == null) return 0;
        boolean enabled = BoolArgumentType.getBool(context, "enabled");
        CarryMePlatform.getInstance().setWantsToBeCarried(player, enabled, true);
        return 1;
    }

    private static int toggleTargets(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        Collection<? extends Entity> targets = EntityArgument.getEntities(context, "target");
        int count = 0;

        for (Entity entity : targets) {
            if (entity instanceof Player player) {
                boolean current = CarryMePlatform.getInstance().wantsToBeCarried(player);
                CarryMePlatform.getInstance().setWantsToBeCarried(player, !current, false);
                count++;
            }
        }

        final int finalCount = count;
        context.getSource().sendSuccess(() -> Component.literal(
                "Toggled carry mode for " + finalCount + " player(s)"), true);
        return count;
    }

    private static int setTargets(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        Collection<? extends Entity> targets = EntityArgument.getEntities(context, "target");
        boolean enabled = BoolArgumentType.getBool(context, "enabled");
        int count = 0;

        for (Entity entity : targets) {
            if (entity instanceof Player player) {
                CarryMePlatform.getInstance().setWantsToBeCarried(player, enabled, false);
                count++;
            }
        }

        final int finalCount = count;
        context.getSource().sendSuccess(() -> Component.literal(
                "Set carry mode to " + (enabled ? "§aenabled" : "§cdisabled") +
                        "§r for " + finalCount + " player(s)"), true);
        return count;
    }

    private static int toggleTeam(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        PlayerTeam team = TeamArgument.getTeam(context, "team");
        int count = 0;

        for (String memberName : team.getPlayers()) {
            Player player = context.getSource().getServer().getPlayerList().getPlayerByName(memberName);
            if (player != null) {
                boolean current = CarryMePlatform.getInstance().wantsToBeCarried(player);
                CarryMePlatform.getInstance().setWantsToBeCarried(player, !current, false);
                count++;
            }
        }

        final int finalCount = count;
        context.getSource().sendSuccess(() -> Component.literal(
                "Toggled carry mode for " + finalCount + " team member(s)"), true);
        return count;
    }

    private static int setTeam(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        PlayerTeam team = TeamArgument.getTeam(context, "team");
        boolean enabled = BoolArgumentType.getBool(context, "enabled");
        int count = 0;

        for (String memberName : team.getPlayers()) {
            Player player = context.getSource().getServer().getPlayerList().getPlayerByName(memberName);
            if (player != null) {
                CarryMePlatform.getInstance().setWantsToBeCarried(player, enabled, false);
                count++;
            }
        }

        final int finalCount = count;
        context.getSource().sendSuccess(() -> Component.literal(
                "Set carry mode to " + (enabled ? "§aenabled" : "§cdisabled") +
                        "§r for " + finalCount + " team member(s)"), true);
        return count;
    }
}

