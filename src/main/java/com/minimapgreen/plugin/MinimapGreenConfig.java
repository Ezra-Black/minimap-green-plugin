package com.minimapgreen.plugin;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Keybind;

import java.awt.Color;

@ConfigGroup("minimapgreenscreen")
public interface MinimapGreenConfig extends Config
{
	@ConfigItem(
		keyName = "chromaColor",
		name = "Chroma key color",
		description = "Color used for the minimap overlay (e.g. green for OBS/Streamlabs chroma key). Change to match your streaming software.",
		position = 0
	)
	default Color chromaColor()
	{
		return new Color(0, 255, 0);
	}

	@ConfigItem(
		keyName = "toggleKeybind",
		name = "Toggle overlay",
		description = "Keybind to show or hide the minimap overlay.",
		position = 1
	)
	default Keybind toggleKeybind()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
		keyName = "defaultOn",
		name = "Default on",
		description = "When enabled, the overlay is shown by default when you start RuneLite. When disabled, it starts hidden until you press the toggle keybind.",
		position = 2
	)
	default boolean defaultOn()
	{
		return true;
	}
}
