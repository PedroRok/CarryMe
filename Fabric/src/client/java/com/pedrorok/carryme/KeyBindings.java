package com.pedrorok.carryme;

import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

/**
 * @author Rok, Pedro Lucas nmm. 06/01/2026
 * @project carry-me
 */
public class KeyBindings {
    public static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(Identifier.fromNamespaceAndPath(CarryMeLogic.MOD_ID, "key.carry.category"));

    public static final KeyMapping TOGGLE_CARRY_MODE = new KeyMapping(
            "key.carryme.toggle",
            GLFW.GLFW_KEY_UNKNOWN,
            CATEGORY
    );
}

