plugins {
    alias(libs.plugins.loldex.android.feature)
    alias(libs.plugins.loldex.android.library.compose)
    alias(libs.plugins.loldex.android.hilt)
}

android {
    namespace = "com.example.loldex.feature.home"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.designsystem)
    implementation(projects.core.domain)
}