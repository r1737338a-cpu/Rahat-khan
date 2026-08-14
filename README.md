# Kitti — Native Android AI Voice Assistant

Kitti is a native Kotlin + Jetpack Compose Android assistant starter project designed to work on lower-end Android phones.

## Important

This project intentionally uses **no API key** and no paid AI service. The included intent engine is local/rule-based and uses Android's built-in SpeechRecognizer and Text-to-Speech.

That means it is buildable without entering an AI configuration key, but it is **not unlimited AGI**. A truly open-ended cloud AI brain requires a model/service and therefore a key or a self-hosted model.

## Current included capabilities

- Bengali/English/Arabic/Hindi speech recognition through Android SpeechRecognizer
- Voice replies through Android Text-to-Speech
- Launch YouTube, WhatsApp, Instagram, Facebook, Google Maps
- Open Camera/Gallery
- Open Settings, Wi-Fi, Bluetooth, Battery settings
- Volume adjustment
- Open alarm/timer screens
- Google web search
- Safe WhatsApp message preparation (user reviews before sending)
- Phone dialer flow (user confirms call)
- Incoming-call / SMS announcement receivers
- Permission requests using official Android permission APIs
- Local command history with clear button
- Jetpack Compose UI
- No secret/background permission bypass
- minSdk 26, target/compile SDK 35

## What cannot honestly be promised

Android, Google, WhatsApp, YouTube and Play Store policies can prevent silent actions, background mic access, direct message sending, or arbitrary control of other apps. Kitti must respect those limits.

For example:
- Directly sending WhatsApp messages without user interaction is not guaranteed.
- Always-on hotword listening in the background is restricted and usually requires a foreground service and additional battery/OS handling.
- Reading SMS/call data requires sensitive permissions and may be restricted by Play Store policy if published.
- Changing many protected system settings requires system/role privileges that a normal app cannot obtain.

## Build

Open this folder in Android Studio, let Gradle sync, then:
Build > Build APK(s)

The APK will normally appear under:
app/build/outputs/apk/debug/

For a release APK, use Android Studio's signed APK flow. A signing key is required for a distributable release APK, but **no Kitti AI API key is required**.

## GitHub

Upload the entire Kitti folder to a GitHub repository. Do not upload passwords, API keys, keystores, or local machine files.
