package com.minimapgreen.plugin;

import net.runelite.api.Client;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

import javax.inject.Inject;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class MinimapGreenOverlay extends Overlay
{
	private static final WidgetInfo[] MINIMAP_WIDGETS = {
		WidgetInfo.FIXED_VIEWPORT_MINIMAP_DRAW_AREA,
		WidgetInfo.RESIZABLE_MINIMAP_STONES_DRAW_AREA,
		WidgetInfo.RESIZABLE_MINIMAP_DRAW_AREA
	};

	private static final Color CHROMA_KEY_GREEN = new Color(0, 255, 0);

	private final Client client;

	@Inject
	public MinimapGreenOverlay(Client client)
	{
		this.client = client;
		setLayer(OverlayLayer.ABOVE_WIDGETS);
		setPosition(OverlayPosition.DYNAMIC);
	}

	@Override
	public Dimension render(Graphics2D g)
	{
		Widget minimap = getMinimapWidget();
		if (minimap == null || minimap.isHidden())
		{
			return null;
		}

		Rectangle bounds = minimap.getBounds();
		int centerX = bounds.x + bounds.width / 2;
		int centerY = bounds.y + bounds.height / 2;
		int radius = Math.min(bounds.width, bounds.height) / 2 - 6;

		Ellipse2D circle = new Ellipse2D.Double(
			centerX - radius,
			centerY - radius,
			radius * 2,
			radius * 2
		);

		g.setColor(CHROMA_KEY_GREEN);
		g.fill(circle);

		return null;
	}

	private Widget getMinimapWidget()
	{
		for (WidgetInfo info : MINIMAP_WIDGETS)
		{
			Widget w = client.getWidget(info);
			if (w != null && !w.isHidden())
			{
				return w;
			}
		}
		return null;
	}
}
