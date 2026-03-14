package com.minimapgreen.plugin;

import com.google.inject.Provides;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.input.KeyManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.util.HotkeyListener;

import javax.inject.Inject;

@PluginDescriptor(
	name = "Minimap Greenscreen",
	description = "Turns minimap interior into chroma key green for streaming (e.g. Streamlabs/OBS)"
)
public class MinimapGreenPlugin extends Plugin
{
	@Inject
	private OverlayManager overlayManager;

	@Inject
	private KeyManager keyManager;

	@Inject
	private MinimapGreenOverlay overlay;

	@Inject
	private MinimapGreenConfig config;

	@Provides
	MinimapGreenConfig getConfig(ConfigManager configManager)
	{
		return configManager.getConfig(MinimapGreenConfig.class);
	}

	private final HotkeyListener hotkeyListener = new HotkeyListener(() -> config.toggleKeybind())
	{
		@Override
		public void hotkeyPressed()
		{
			overlay.toggle();
		}
	};

	@Override
	protected void startUp()
	{
		overlayManager.add(overlay);
		keyManager.registerKeyListener(hotkeyListener);
	}

	@Override
	protected void shutDown()
	{
		overlayManager.remove(overlay);
		keyManager.unregisterKeyListener(hotkeyListener);
	}
}
