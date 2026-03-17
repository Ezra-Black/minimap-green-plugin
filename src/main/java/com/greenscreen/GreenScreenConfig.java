package com.greenscreen;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Keybind;

import java.awt.Color;

/**
 * Configuration for the Green Screen plugin.
 *
 * Exposes where the greenscreen should be applied, which color to use and
 * how it is toggled on and off.
 */
@ConfigGroup("greenscreen")
public interface GreenScreenConfig extends Config
{
	/**
	 * Where the greenscreen should be applied.
	 *
	 * FULL_GAME: only over the game world.
	 * MINIMAP_ONLY: only over the minimap to embed a camera feed there.
	 * BOTH: over the game world and minimap at the same time.
	 */
	@ConfigItem(
		keyName = "mode",
		name = "Greenscreen mode",
		description = "Full game: green only on game world. Minimap only: green only on minimap. Both: green on game world and minimap.",
		position = 0
	)
	default GreenscreenMode mode()
	{
		return GreenscreenMode.FULL_GAME;
	}

	/**
	 * The chroma key color to use for streaming software (OBS/Streamlabs).
	 */
	@ConfigItem(
		keyName = "color",
		name = "Color",
		description = "The color of the greenscreen",
		position = 1
	)
	default Color greenscreenColor()
	{
		return new Color(41, 244, 24);
	}

	/**
	 * Keybind used to toggle the greenscreen on and off at runtime.
	 */
	@ConfigItem(
			keyName = "toggleKey",
			name= "Toggle Key",
			description = "Key to press to toggle greenscreen",
			position = 2
	)
	default Keybind hotkey()
	{
		return Keybind.NOT_SET;
	}

	/**
	 * Whether the greenscreen should be enabled immediately when the plugin starts.
	 */
	@ConfigItem(
			keyName = "defaultState",
			name = "Should Default On",
			description = "What state should the greenscreen default to",
			position = 3
	)
	default boolean defaultState()
	{
		return true;
	}
}
