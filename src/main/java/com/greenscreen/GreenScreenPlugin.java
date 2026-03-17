package com.greenscreen;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.input.KeyManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.util.HotkeyListener;

import javax.inject.Inject;

import net.runelite.client.config.Keybind;

/**
 * Entry point for the Green Screen plugin.
 * <p>
 * Registers the full-game and minimap overlays and wires up the hotkey-based
 * toggle so users can turn the greenscreen on and off while playing.
 */
@Slf4j
@PluginDescriptor(
	name = "Green Screen"
)
public class GreenScreenPlugin extends Plugin
{
	// RuneLite game client, exposed in case future features need it.
	@Inject
	private Client client;

	// User-configurable options (mode, color, hotkey, default state).
	@Inject
	private GreenScreenConfig config;

	// Overlay that applies the greenscreen over the 3D game world.
	@Inject
	private GreenScreenOverlay overlay;

	// Overlay that applies the greenscreen over the minimap.
	@Inject
	private GreenScreenMinimapOverlay minimapOverlay;

	// Manages adding/removing overlays from the RuneLite overlay system.
	@Inject
	private OverlayManager overlayManager;

	// Handles plugin keybinds, including the greenscreen toggle.
	@Inject
	private KeyManager keyManager;

	// Whether the greenscreen is currently being rendered. Controlled by the hotkey.
	private boolean renderGreenscreen = true;

	public boolean isRenderGreenscreen()
	{
		return renderGreenscreen;
	}

	// Hotkey listener that toggles the greenscreen visibility when pressed.
	private final HotkeyListener hotkeyListener = new HotkeyListener(() -> config.hotkey())
	{
		@Override
		public void hotkeyPressed()
		{
			renderGreenscreen = !renderGreenscreen;
		}
	};

	@Override
	protected void startUp() throws Exception
	{
		// Register both overlays and initialize the toggle state from config.
		overlayManager.add(overlay);
		overlayManager.add(minimapOverlay);

		// If a default has been explicitly set, respect it; otherwise fall back to "on".
		renderGreenscreen = config.defaultState();

		// If no toggle key is configured, keep the greenscreen always on while the plugin is enabled.
		if (config.hotkey() == Keybind.NOT_SET)
		{
			renderGreenscreen = true;
		}

		keyManager.registerKeyListener(hotkeyListener);
	}

	@Override
	protected void shutDown() throws Exception
	{
		// Remove overlays and stop listening for the toggle hotkey.
		overlayManager.remove(overlay);
		overlayManager.remove(minimapOverlay);
		keyManager.unregisterKeyListener(hotkeyListener);
	}

	@Provides
	GreenScreenConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(GreenScreenConfig.class);
	}
}
