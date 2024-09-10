plugins {
    alias(libs.plugins.dueldex.android.feature)
    alias(libs.plugins.dueldex.android.library.compose)
}

android {
    namespace = "com.example.dueldex.feature.decks"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.domain)
}