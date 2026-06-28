# Release Checklist

Follow this checklist before submitting TapCard to the Google Play Store.

## 1. Security & Configuration
- [ ] Verify `SUPABASE_SERVICE_ROLE` is completely removed from the Android project and `build.gradle.kts`.
- [ ] Confirm all backend API calls use HTTPS.
- [ ] Ensure `.env` is ignored by `.gitignore`.
- [ ] Generate a production release keystore (if not using Play App Signing).
- [ ] Update `versionCode` and `versionName` in `app/build.gradle.kts`.

## 2. Performance
- [ ] Verify ProGuard/R8 is enabled (`isMinifyEnabled = true`).
- [ ] Run `./gradlew assembleRelease` and ensure build passes.
- [ ] Verify the APK/AAB size is reasonable (target < 15MB).

## 3. UI/UX Verification
- [ ] Test the app on Light and Dark modes.
- [ ] Verify animations and haptics on physical devices.
- [ ] Verify empty states when signed out.
- [ ] Test across different form factors (phone, tablet).

## 4. Feature Verification
- [ ] **NFC:** Successfully program an NDEF tag.
- [ ] **QR Code:** Verify QR code can be scanned and saved to the gallery.
- [ ] **Auth:** Sign in, Sign up, and Sign out successfully.
- [ ] **Sync:** Verify offline editing queues changes and syncs upon network restoration.
- [ ] **Images:** Verify Profile Photo and Company Logo upload to Supabase.

## 5. Store Listing
- [ ] Capture hi-res screenshots for the Play Store.
- [ ] Write a compelling Store Description.
- [ ] Update Privacy Policy URL in the Play Console.
