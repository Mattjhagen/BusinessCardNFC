# Known Limitations

As of Version 1.0, TapCard has the following known limitations:

### 1. Platform Support
- Currently only supported on Android 8.0 (API 26) and above.
- No Apple Wallet integration (only Google Wallet structure exists, gated by a feature flag).
- No Web Landing Page (QR/NFC directly shares data, rather than a hosted profile page).

### 2. Synchronization
- **Conflict Resolution:** The app uses a simple "last write wins" strategy for synchronization. If the profile is edited on multiple devices simultaneously while offline, the last device to sync will overwrite previous changes.
- **Background Sync:** The app does not currently perform background sync using Android WorkManager. Synchronization only occurs when the app is open and an action is triggered.

### 3. Image Uploads
- Maximum image resolution is downscaled to 1024x1024 to preserve storage and bandwidth.
- Image uploads do not support background resumption if the app is killed during the upload process.

### 4. NFC Capabilities
- "Share via NFC" relies on NDEF message writing. The receiving device must be unlocked and have NFC enabled.
- Direct peer-to-peer sharing (Android Beam) is no longer supported in modern Android versions, meaning a rewritable NFC tag must be used.
