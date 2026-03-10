package com.minimapgreen.plugin;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class MinimapGreenPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(MinimapGreenPlugin.class);
		RuneLite.main(args);
	}
}
