package com.pedrorok.carryme;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

/**
 * @author Rok, Pedro Lucas nmm. 05/01/2026
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