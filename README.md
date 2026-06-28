# TapCard Platform

TapCard is a digital identity platform built for Android, featuring NFC capabilities, a Supabase backend, and a web profile platform.

## Setup Instructions

### Firebase Setup (Required for Crashlytics & Analytics)
1. Go to the Firebase Console and create a new project (or use an existing one).
2. Add an Android app with the package name `com.tapcard.app`.
3. Download the `google-services.json` file.
4. Place the `google-services.json` file in the `TapCard/app/` directory. (DO NOT commit this file, it is already in `.gitignore`).
5. For reference, see `TapCard/app/google-services.template.json`.

### Supabase Setup
1. Create a Supabase project.
2. Run the SQL schema from `backend/supabase_schema.sql`.
3. Copy the `.env.example` to `.env` and fill in your keys.

