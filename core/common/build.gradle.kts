@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.loldex.android.library)
    alias(libs.plugins.loldex.android.hilt)
}

android {
    namespace = "com.example.loldex.core.common"
}

dependencies {
}