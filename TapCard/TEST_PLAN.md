# Test Plan

This document outlines the testing strategy for the TapCard application.

## 1. Unit Testing
### ViewModels
- `ProfileViewModelTest`:
    - Validates username regex.
    - Validates state transitions during profile saves.
    - Asserts sync errors map to the correct UI states.

### Repositories
- `SyncProfileRepositoryImplTest`:
    - Validates offline behavior (saves to Room, sets `isPendingSync = true`).
    - Validates Supabase API integration and error handling (RestException handling).

## 2. Integration Testing
### Room Database
- `ProfileDaoTest` (Instrumented):
    - Asserts that entities can be inserted, retrieved, and updated.
    - Verifies default nullability and constraint behavior.

## 3. UI/Compose Testing
- (Future Scope)
- Use Compose Test Rules to verify screen navigation.
- Validate empty state assertions and button enablement (e.g. disabled Save button when username is invalid).

## 4. Manual QA Path
1. Launch app -> Complete Onboarding -> Verify Dashboard renders offline placeholder.
2. Go to Editor -> Fill details -> Ensure validation rejects bad usernames.
3. Save -> Check "Syncing..." status -> Verify Supabase dashboard for data presence.
4. Sign out -> Verify local Room cache is preserved or cleared based on product rules.
5. Disable Network -> Save profile -> Verify "Sync Failed" / "Pending Sync" state.
6. Enable Network -> Resume app -> Verify auto-sync or manual sync trigger.
