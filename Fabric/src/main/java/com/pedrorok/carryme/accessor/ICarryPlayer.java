package com.pedrorok.carryme.accessor;

import net.minecraft.nbt.CompoundTag;

/*
 * @author Lucasmellof, Lucas de Mello Freitas created on 08/01/2026
 */
public interface ICarryPlayer {
	boolean carry_me$canBeCarried();

	void carry_me$setCanBeCarried(boolean canBeCarried);
}
