plugins {
    alias(libs.plugins.dueldex.android.library)
    alias(libs.plugins.dueldex.android.hilt)
}

android {
    namespace = "com.example.dueldex.core.data.test"
}

dependencies {
    api(projects.core.data)
    implementation(libs.sandwich)

    implementation(libs.hilt.android.testing)
}