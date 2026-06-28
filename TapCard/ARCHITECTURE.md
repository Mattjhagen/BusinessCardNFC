# TapCard Architecture

## Overview
TapCard is an Android application designed for modern digital business card sharing using NFC, QR codes, and cloud synchronization. It follows a Clean Architecture approach layered into UI (Presentation), Domain, and Data components.

## Core Technologies
- **UI:** Jetpack Compose (Material 3)
- **Dependency Injection:** Hilt / Dagger
- **Local Storage:** Room Database (SQLite)
- **Remote Storage:** Supabase (PostgreSQL, Storage, Auth)
- **Asynchronous Operations:** Kotlin Coroutines and Flows
- **NFC:** Android NFC API (`NfcAdapter`, `NdefMessage`)
- **QR Code:** ZXing

## Project Structure

```
com.tapcard.app/
│
├── data/               # Data layer implementation
│   ├── local/          # Room entities, DAOs, and database
│   ├── remote/         # DTOs and Supabase integrations
│   └── repository/     # Repository implementations
│
├── di/                 # Hilt Modules (AppModule, etc.)
│
├── domain/             # Business logic and interfaces
│   ├── model/          # Domain models (Profile, SyncStatus)
│   ├── repository/     # Repository interfaces
│   └── wallet/         # Wallet capabilities and config
│
├── ui/                 # Presentation layer (Jetpack Compose)
│   ├── components/     # Reusable UI widgets (BusinessCardPreview)
│   ├── screens/        # Full-screen composables (Dashboard, Editor)
│   ├── theme/          # Material 3 theme configurations
│   └── viewmodel/      # Architecture components (ProfileViewModel)
│
└── utils/              # Helper utilities
    ├── ImageCompressor # Bitmaps and File processing
    ├── NfcService      # NFC reading/writing capabilities
    └── QRExportService # QR code generation and sharing
```

## Data Flow
The app employs a "Local-First" architecture:
1. All changes are saved locally to Room (`ProfileDao`).
2. If authenticated, the `SyncProfileRepositoryImpl` attempts to synchronize these changes with Supabase immediately.
3. Errors are caught and surfaced via a `syncError` state flow, and the local data is flagged with `isPendingSync = true`.

## Authentication
Authentication is handled via Supabase Auth (GoTrue). The `SupabaseAuthRepositoryImpl` provides an interface to sign up, sign in, and manage the current user session securely.
