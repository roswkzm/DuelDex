plugins {
    alias(libs.plugins.dueldex.android.feature)
    alias(libs.plugins.dueldex.android.library.compose)
}

android {
    namespace = "com.example.dueldex.feature.detail"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.domain)
    implementation(libs.androidx.browser)
    implementation(libs.vico.compose)
    implementation(libs.vico.compose.m3)
    implementation(libs.vico.core)
}