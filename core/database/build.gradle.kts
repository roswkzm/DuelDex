plugins {
    alias(libs.plugins.loldex.android.library)
    alias(libs.plugins.loldex.android.hilt)
    alias(libs.plugins.loldex.android.room)
}

android {
    namespace = "com.example.loldex.core.database"
}

dependencies {
    api(projects.core.model)
    implementation(libs.kotlinx.datetime)
    implementation(libs.gson)
}