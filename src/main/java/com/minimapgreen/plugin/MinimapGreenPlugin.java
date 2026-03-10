package com.minimapgreen.plugin;

import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

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
	private MinimapGreenOverlay overlay;

	@Override
	protected void startUp()
	{
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown()
	{
		overlayManager.remove(overlay);
	}
}
