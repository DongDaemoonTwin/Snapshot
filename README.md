# Snapshot

Android-first prototype for a live weather wallpaper and home screen widget.

## What this prototype includes

- Native Android/Kotlin project scaffold
- `WallpaperService` live wallpaper renderer
- Home screen widget that renders a static weather-scene snapshot
- Open-Meteo weather fetcher using Seoul as the default location
- Scene state layer for weather type, rain/snow/cloud intensity, sunrise and sunset
- Canvas renderer for sky gradient, sun/moon arc, clouds, rain, snow, fog and a window-frame composition

## Run in Codespaces / local

```bash
# In a machine with Android SDK + Gradle available
./gradlew assembleDebug
```

If the Gradle wrapper is not generated yet, open the project in Android Studio once or run a system Gradle install with:

```bash
gradle wrapper
./gradlew assembleDebug
```

Debug APK path:

```text
app/build/outputs/apk/debug/app-debug.apk
```

## First test flow

1. Install the debug APK on an Android device.
2. Open Snapshot.
3. Tap **Fetch Seoul weather** or **Use device location**.
4. Tap **Open live wallpaper picker** and set the wallpaper.
5. Add the Snapshot widget from the Android home screen.

See `docs/prototype-plan.md` for the next implementation steps.
