plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("androidx.navigation.safeargs.kotlin") version "2.8.2" apply false
    id("com.google.dagger.hilt.android") version "2.52" apply false
}