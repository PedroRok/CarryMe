package com.pedrorok.carryme;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.settings.KeyConflictContext;

/**
 * Client-side key bindings for NeoForge
 *
 * @author Rok, Pedro Lucas nmm. 06/01/2026
 * @project carry-me
 */
public class KeyBindings {

    public static final String CATEGORY = "key.categories.carryme";

    public static final KeyMapping TOGGLE_CARRY_MODE = new KeyMapping(
            "key.carryme.toggle",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            InputConstants.UNKNOWN.getValue(),
            CATEGORY
    );
}


