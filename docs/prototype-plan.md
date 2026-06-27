# Prototype Plan

## Product direction

This is not a full weather app. The product direction is:

> A home-screen customization app where your wallpaper and widgets quietly follow the real sky.

The app screen is only for permissions, settings, preview and setup. The actual product surface is:

- Android live wallpaper
- Android home screen widget

## MVP scope

### Live wallpaper

- Time-based sky gradient
- Sun position from sunrise/sunset
- Moon/stars at night
- Cloud cover visualization
- Rain particles
- Snow particles
- Fog overlay
- Minimal window-frame composition

### Widget

- Static scene snapshot rendered to a bitmap
- City, temperature and weather label
- 30-minute refresh cadence

### Weather data

- Current weather from Open-Meteo
- Default city: Seoul
- Optional device last-known location
- Store latest scene in SharedPreferences

## Asset strategy

This prototype deliberately avoids heavy PNG assets. The first visual layer is mostly Canvas-based.

Later asset pack:

```text
assets/
  foreground/window_frame.png
  foreground/glass_reflection.png
  clouds/cloud_soft_01.png
  clouds/cloud_soft_02.png
  clouds/cloud_dark_01.png
  night/moon.png
  night/stars.png
  effects/fog_overlay.png
  effects/glass_raindrops.png
```

## Next technical steps

1. Replace default Canvas window frame with real PNG foreground assets.
2. Add WorkManager for periodic background weather refresh.
3. Add user-selectable saved cities.
4. Add theme packs: Window, Ocean, City Night.
5. Add battery settings: 15fps / 30fps / paused on low battery.
6. Add proper location permission onboarding.
7. Add Play Store screenshots and icon polish.
