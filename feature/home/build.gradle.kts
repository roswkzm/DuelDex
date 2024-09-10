plugins {
    alias(libs.plugins.dueldex.android.feature)
    alias(libs.plugins.dueldex.android.library.compose)
    alias(libs.plugins.dueldex.android.hilt)
}

android {
    namespace = "com.example.dueldex.feature.home"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.designsystem)
    implementation(projects.core.domain)
}