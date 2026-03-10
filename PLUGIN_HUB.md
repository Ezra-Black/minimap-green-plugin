# Submitting to the RuneLite Plugin Hub

Follow these steps to get **Minimap Greenscreen** on the official Plugin Hub so users can install it via **RuneLite → Plugin Hub**.

---

## 1. Put the plugin on GitHub

1. **Create a new public repository** on GitHub (e.g. `minimap-green-plugin` or `runelite-minimap-greenscreen`).

2. **Set your GitHub username** in `runelite-plugin.properties`:
   ```properties
   author=YourGitHubUsername
   ```
   Replace `YourGitHubUsername` with your actual GitHub username.

3. **Push this project** to the repo.

   If the project already has a git repo (e.g. from the example-plugin clone), point it at your new repo and push:
   ```bash
   cd C:\Users\devez\minimap-green-plugin
   git remote set-url origin https://github.com/YourGitHubUsername/minimap-green-plugin.git
   git add -A
   git status
   git commit -m "Minimap Greenscreen plugin for Plugin Hub"   # if there are changes
   git branch -M main
   git push -u origin main
   ```

   If starting from scratch:
   ```bash
   cd C:\Users\devez\minimap-green-plugin
   git init
   git add .
   git commit -m "Initial commit: Minimap Greenscreen plugin"
   git branch -M main
   git remote add origin https://github.com/YourGitHubUsername/minimap-green-plugin.git
   git push -u origin main
   ```
   Replace `YourGitHubUsername` and the repo name with your actual GitHub username and repository name.

4. **Check the layout** — the repo should look like:
   ```
   your-repo/
   ├── src/main/java/com/minimapgreen/plugin/
   │   ├── MinimapGreenPlugin.java
   │   └── MinimapGreenOverlay.java
   ├── runelite-plugin.properties
   ├── build.gradle
   ├── settings.gradle
   └── README.md
   ```

---

## 2. Confirm it builds

From the project root:

```bash
./gradlew build
```

or on Windows:

```bash
gradlew.bat build
```

The Plugin Hub will build from this repo; the build must succeed.

---

## 3. Fork the Plugin Hub

1. Open: **https://github.com/runelite/plugin-hub**
2. Click **Fork** and create your fork (e.g. `https://github.com/YourGitHubUsername/plugin-hub`).

---

## 4. Add the plugin to the manifest

1. **Clone your fork** (if you don’t have it yet):
   ```bash
   git clone https://github.com/YourGitHubUsername/plugin-hub
   cd plugin-hub
   ```

2. **Edit** `plugins/plugins.json`.

3. **Add one entry** for Minimap Greenscreen (order doesn’t matter; keep JSON valid):
   ```json
   {
     "name": "Minimap Greenscreen",
     "repository": "https://github.com/YourGitHubUsername/minimap-green-plugin"
   }
   ```
   Use your real GitHub username and the actual repo URL.

4. **Commit and push** to your fork:
   ```bash
   git add plugins/plugins.json
   git commit -m "Add Minimap Greenscreen plugin"
   git push origin main
   ```

---

## 5. Open a Pull Request

1. On GitHub, open your fork of `plugin-hub`.
2. Use **Compare & pull request** (or **Contribute → Open pull request**).
3. Target: **runelite/plugin-hub**, branch **master** (or whatever the default branch is).
4. Title example: **Add Minimap Greenscreen plugin**
5. In the description, mention:
   - It’s a visual-only overlay for streaming (minimap chroma key).
   - No automation or input simulation.
6. Submit the PR.

---

## 6. Review and release

- RuneLite maintainers will review (code safety, no malicious behavior, build, API usage).
- Visual overlays and streaming helpers are generally acceptable; automation/input abuse is not.
- After the PR is merged, the plugin will be available in **RuneLite → Plugin Hub**; users can search **Minimap Greenscreen** and install it there.

---

## Notes

- **Plugin Hub rejects** plugins that automate gameplay, interact with inputs/macros, or violate game rules. This plugin only draws a green overlay and is within those rules.
- Keep `runelite-plugin.properties` and `build.gradle` in the repo as they are; the Hub uses them to build and list your plugin.
