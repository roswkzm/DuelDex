plugins {
    alias(libs.plugins.dueldex.android.library)
    alias(libs.plugins.dueldex.android.library.compose)
}

android {
    namespace = "com.example.dueldex.core.ui"
}

dependencies {
    api(projects.core.designsystem)
    api(projects.core.model)

    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)
}