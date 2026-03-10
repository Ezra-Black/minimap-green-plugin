# Minimap Greenscreen

RuneLite plugin for **Old School RuneScape** that draws a chroma-key green circle over the minimap so you can replace it with a webcam (or other source) in OBS/Streamlabs.

## Features

- Works in fixed and resizable game layouts
- Pure visual overlay — no gameplay automation
- Standard chroma key green (`#00FF00`) for OBS/Streamlabs

## Build

```bash
./gradlew build
```

On Windows:

```bash
gradlew.bat build
```

Plugin JAR: `build/libs/minimap-green-plugin-1.0-SNAPSHOT.jar`

## Run locally (developer)

```bash
./gradlew run
```

Starts RuneLite with the plugin loaded and developer mode enabled.

## Install (side-load)

1. Build the JAR (see above).
2. Start RuneLite with developer mode (e.g. use the `run-developer.ps1` script in your RuneLite install folder, or pass `-Drunelite.developer-mode=true` when launching).
3. **Plugins → Developer Tools → Side-load plugin** and select the JAR.
4. Enable **Minimap Greenscreen** in the plugin list.

## Streamlabs / OBS

1. **Source order:** RuneLite capture → Webcam (webcam above so it shows inside the circle).
2. On the **RuneLite** source: **Filter → Chroma Key**, color **Green**.
3. Your webcam will appear inside the minimap circle.

## Requirements

- Java 11+
- RuneLite client
- Old School RuneScape (fixed or resizable layout)

## Plugin Hub

This plugin is structured for the [RuneLite Plugin Hub](https://github.com/runelite/plugin-hub). See **[PLUGIN_HUB.md](PLUGIN_HUB.md)** for submission steps.
