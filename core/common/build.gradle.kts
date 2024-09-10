@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.dueldex.android.library)
    alias(libs.plugins.dueldex.android.hilt)
}

android {
    namespace = "com.example.dueldex.core.common"
}

dependencies {
}