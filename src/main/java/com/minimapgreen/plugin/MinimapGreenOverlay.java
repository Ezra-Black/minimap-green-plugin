package com.minimapgreen.plugin;

import net.runelite.api.Client;
import net.runelite.api.widgets.ComponentID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

import javax.inject.Inject;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class MinimapGreenOverlay extends Overlay
{
	private static final int[] MINIMAP_COMPONENT_IDS = {
		ComponentID.FIXED_VIEWPORT_MINIMAP_DRAW_AREA,
		ComponentID.RESIZABLE_VIEWPORT_BOTTOM_LINE_MINIMAP_DRAW_AREA,
		ComponentID.RESIZABLE_VIEWPORT_MINIMAP_DRAW_AREA
	};

	/** Component IDs for orbs/buttons around the minimap that must not be covered. */
	private static final int[] MINIMAP_ORB_COMPONENT_IDS = {
		ComponentID.MINIMAP_HEALTH_ORB,
		ComponentID.MINIMAP_PRAYER_ORB,
		ComponentID.MINIMAP_RUN_ORB,
		ComponentID.MINIMAP_SPEC_ORB,
		ComponentID.MINIMAP_QUICK_PRAYER_ORB,
		ComponentID.MINIMAP_TOGGLE_RUN_ORB,
		ComponentID.MINIMAP_XP_ORB,
		ComponentID.MINIMAP_WORLDMAP_ORB,
		ComponentID.MINIMAP_WIKI_BANNER_PARENT
	};

	private final Client client;
	private final MinimapGreenConfig config;
	private boolean hidden;

	@Inject
	public MinimapGreenOverlay(Client client, MinimapGreenConfig config)
	{
		this.client = client;
		this.config = config;
		this.hidden = !config.defaultOn();
		setLayer(OverlayLayer.ABOVE_WIDGETS);
		setPosition(OverlayPosition.DYNAMIC);
	}

	@Override
	public Dimension render(Graphics2D g)
	{
		if (hidden)
		{
			return null;
		}

		Widget minimap = getMinimapWidget();
		if (minimap == null || minimap.isHidden())
		{
			return null;
		}

		Rectangle bounds = minimap.getBounds();
		int centerX = bounds.x + bounds.width / 2;
		int centerY = bounds.y + bounds.height / 2;
		// Use full radius so the overlay reaches the edge of the minimap (no visible ring).
		int radius = Math.min(bounds.width, bounds.height) / 2;

		Area fill = new Area(new Ellipse2D.Double(
			centerX - radius,
			centerY - radius,
			radius * 2,
			radius * 2
		));

		// Subtract orb/button regions so they remain visible and are not covered by the overlay.
		for (int componentId : MINIMAP_ORB_COMPONENT_IDS)
		{
			Widget w = client.getWidget(componentId);
			if (w != null && !w.isHidden())
			{
				Rectangle orbBounds = w.getBounds();
				if (orbBounds != null && !orbBounds.isEmpty())
				{
					fill.subtract(new Area(orbBounds));
				}
			}
		}

		g.setColor(config.chromaColor());
		g.fill(fill);

		return null;
	}

	void toggle()
	{
		hidden = !hidden;
	}

	private Widget getMinimapWidget()
	{
		for (int componentId : MINIMAP_COMPONENT_IDS)
		{
			Widget w = client.getWidget(componentId);
			if (w != null && !w.isHidden())
			{
				return w;
			}
		}
		return null;
	}
}
