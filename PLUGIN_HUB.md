# Submitting to the RuneLite Plugin Hub

**Quick PR (already prepared):** The plugin-hub branch and manifest file are ready in `c:\Users\devez\plugin-hub`. See that folder’s **OPEN_PR_INSTRUCTIONS.md** to push the branch and open the PR (fork → push → create PR with the given title/description).

---

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

4. **Optional: add a LICENSE** — Plugin Hub recommends BSD 2-Clause. In your repo: *Add file → Create new file →* filename `LICENSE`, then *Choose a license template* → *BSD 2-Clause "Simplified" License*.

5. **Check the layout** — the repo should look like:
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

The Plugin Hub CI will build from your repo; the build **must** succeed.

---

## 3. Fork the Plugin Hub and create a branch

1. Open **https://github.com/runelite/plugin-hub** and click **Fork** (e.g. `https://github.com/YourGitHubUsername/plugin-hub`).

2. Clone your fork and create a branch from upstream `master`:
   ```bash
   git clone https://github.com/YourGitHubUsername/plugin-hub
   cd plugin-hub
   git remote add upstream https://github.com/runelite/plugin-hub.git
   git fetch upstream
   git checkout -B minimap-greenscreen upstream/master
   ```

---

## 4. Add the plugin manifest file

The Plugin Hub uses **one file per plugin** in `plugins/`, not a single JSON file.

1. **Create a new file** in the `plugins/` directory. The **filename** is the plugin’s identifier (e.g. `minimap-greenscreen`). Use lowercase and hyphens.

2. **Content** of the file (two required lines; add `warning=` only if your plugin makes outbound HTTP to a third party):
   ```
   repository=https://github.com/YourGitHubUsername/minimap-green-plugin.git
   commit=<40-character-full-commit-hash>
   ```

3. **Get the commit hash** from your plugin repo:
   - On GitHub: open your plugin repo → **Commits** → click the latest commit → copy the **full 40-character hash** (top right).
   - Or locally: `git rev-parse HEAD`

4. **Example** — if your username is `jane` and latest commit is `a1b2c3d4e5f6...`:
   - Create file: `plugins/minimap-greenscreen`
   - Contents:
     ```
     repository=https://github.com/jane/minimap-green-plugin.git
     commit=a1b2c3d4e5f6789012345678901234567890abcd
     ```

5. **Commit and push** your branch:
   ```bash
   git add plugins/minimap-greenscreen
   git commit -m "Add Minimap Greenscreen plugin"
   git push -u origin minimap-greenscreen
   ```

---

## 5. Open the Pull Request

1. On GitHub, open your fork and use **Compare & pull request** (or **Contribute → Open pull request**).
2. **Base:** `runelite/plugin-hub` → `master`. **Compare:** your fork → `minimap-greenscreen`.
3. **Title:** e.g. **Add Minimap Greenscreen plugin**
4. **Description** — include:
   - What the plugin does: visual-only overlay that draws chroma-key green over the minimap for streaming (OBS/Streamlabs).
   - That it does **not** automate gameplay, simulate input, or interact with the game beyond drawing an overlay.
5. **Create pull request**.

---

## 6. CI and review

- **Build workflow:** Check the PR for `.github/workflows/build.yml / build (pull_request)`. It must be ✔️. If ❌, open *Details* and fix the reported errors (often build or `commit=` hash).
- **Plugin Hub Checks:** If it reports “Changes are needed”, address those in your plugin repo, then update the **commit=** hash in your plugin-hub file and push a new commit to the same PR.
- **Human review:** Maintainers will review for safety, Jagex rules, and [Rejected/Rolled Back](https://github.com/runelite/runelite/wiki/Rejected-or-Rolled-Back-Features) features. Visual/streaming overlays are generally acceptable.
- After merge, the plugin appears in **RuneLite → Plugin Hub**; users can search for **Minimap Greenscreen** and install it.

---

## Notes

- **Plugin Hub rejects** plugins that automate gameplay, simulate input, or violate [Jagex’s third-party client guidelines](https://secure.runescape.com/m=news/third-party-client-guidelines?oldschool=1). This plugin only draws a green overlay and is within those rules.
- Keep `runelite-plugin.properties` and `build.gradle` as-is; the Hub uses them to build and list your plugin.
- To **update** the plugin later: change the `commit=` in `plugins/minimap-greenscreen` to the new commit hash and open a new PR (or push to the same branch and update the existing PR).
