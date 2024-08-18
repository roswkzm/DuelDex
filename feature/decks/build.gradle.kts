plugins {
    alias(libs.plugins.loldex.android.feature)
    alias(libs.plugins.loldex.android.library.compose)
}

android {
    namespace = "com.example.loldex.feature.decks"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.domain)
}