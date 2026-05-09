# Tip Calculator

A clean, ad-free Android tip calculator with real-time results and bill splitting.

## Features

- **Instant calculation** — updates as you type, no button press needed
- **Tip presets** — 10%, 15%, 18%, 20%, 25% one-tap chips + custom %
- **Bill splitting** — up to 99 people, highlights per-person amount
- **Round up** — round tip up to nearest dollar
- **Zero ads** — completely free, no tracking, no internet needed

## Tech Stack

- Kotlin + Jetpack Compose + Material 3
- ViewModel + StateFlow (clean state management)
- No permissions required — fully offline

## Build

```bash
./gradlew assembleDebug      # debug APK
./gradlew bundleRelease      # signed AAB for Play Store
```

## Play Store Checklist

- [ ] Add app icon (replace mipmap folders)
- [ ] Take 2-8 screenshots
- [ ] Create feature graphic (1024x500px)
- [ ] Host privacy policy and add link
- [ ] Build signed AAB and upload to Play Console
- [ ] Fill Data Safety form

## Privacy

This app collects no data. All calculations are on-device and instant.
