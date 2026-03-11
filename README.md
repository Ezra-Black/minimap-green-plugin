# Minimap Greenscreen

A **RuneLite** plugin for **Old School RuneScape** that turns the minimap into a chroma-key green circle so you can replace it with your webcam (or any other source) in OBS, Streamlabs, or other streaming software.

---

## What it does

- Draws a **green circle** over the minimap area so streaming software can key it out.
- Works in **fixed and resizable** game layouts.
- **Visual overlay only** — no automation, no input simulation, no interaction with the game beyond drawing the overlay. Safe with Jagex’s third-party client guidelines.

Use it to show your face (or another scene) inside the minimap circle while you play.

---

## How to get it

### From the Plugin Hub (recommended)

1. Open **RuneLite** → **Configuration** → **Plugin Hub**.
2. Search for **Minimap Greenscreen**.
3. Enable the plugin.

If you don’t see it in the Plugin Hub yet, use the side-load steps below.

### Side-load (developer / unreleased builds)

1. [Build the plugin](#building-from-source) (or download a built JAR).
2. Start RuneLite with **developer mode** (e.g. `run-developer.ps1` in this repo, or `-Drunelite.developer-mode=true`).
3. **Plugins** → **Developer Tools** → **Side-load plugin** → select the JAR.
4. Enable **Minimap Greenscreen** in the plugin list.

---

## How to use it (OBS / Streamlabs)

1. **Capture:** Add your game capture (RuneLite) as a source.
2. **Chroma key:** Add a **Chroma Key** (or “Color Key”) filter to that source. Set the color to **Green** (e.g. `#00FF00`). Adjust similarity/smoothness as needed.
3. **Webcam:** Add your webcam (or other source) **above** the game capture in the source list so it appears inside the circle where the minimap was.

Your webcam will now show inside the minimap circle in your stream.

---

## Requirements

- **RuneLite** client  
- **Old School RuneScape** (fixed or resizable layout)  
- For building/developing: **Java 11+**

---

## Contributing

Contributions are welcome. Here’s how you can help.

### Report a bug or suggest a feature

- Open an **[Issue](https://github.com/Ezra-Black/minimap-green-plugin/issues)** on GitHub.
- For bugs: include RuneLite version, game layout (fixed/resizable), and steps to reproduce.
- For ideas: describe the use case and how you’d expect it to work.

### Contribute code

1. **Fork** this repo and clone it locally.
2. **Build and run:**
   - **Windows:** `.\run-developer.ps1` or set `JAVA_HOME` and run `.\gradlew.bat run`.
   - **macOS/Linux:** `./gradlew run`
3. Make your changes, keep the overlay visual-only (no automation/input simulation).
4. **Build:** `./gradlew build` (or `.\gradlew.bat build` on Windows). Ensure it passes.
5. Open a **Pull Request** with a short description of the change.

### Building from source

```bash
# Build
./gradlew build          # or: .\gradlew.bat build  (Windows)

# Output JAR
# build/libs/minimap-green-plugin-1.0-SNAPSHOT.jar
```

---

## Links

- **Plugin Hub:** [RuneLite Plugin Hub](https://github.com/runelite/plugin-hub)
- **Submission notes:** [PLUGIN_HUB.md](PLUGIN_HUB.md) (for maintainers / Plugin Hub PR process)

---

*Minimap Greenscreen is a community plugin and is not officially supported by RuneLite or Jagex.*
