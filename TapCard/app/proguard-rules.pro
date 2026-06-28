-dontwarn org.slf4j.impl.StaticLoggerBinder
-keep class org.slf4j.impl.StaticLoggerBinder { *; }

# Coil
-keep class coil.** { *; }
-dontwarn coil.**
-dontwarn okio.**
-dontwarn javax.annotation.**

# Supabase and Ktor
-keep class io.ktor.** { *; }
-keep class io.github.jan.supabase.** { *; }
-dontwarn io.ktor.**
-dontwarn io.github.jan.supabase.**

# Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}

# Room
-keep class * extends androidx.room.RoomDatabase
-dontwarn androidx.room.paging.**
