plugins {
    alias(libs.plugins.dueldex.android.library)
    alias(libs.plugins.dueldex.android.hilt)
    alias(libs.plugins.dueldex.android.room)
}

android {
    namespace = "com.example.dueldex.core.database"
}

dependencies {
    api(projects.core.model)
    implementation(libs.kotlinx.datetime)
    implementation(libs.gson)
}