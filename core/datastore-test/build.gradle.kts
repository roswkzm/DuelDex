plugins {
    alias(libs.plugins.dueldex.android.library)
    alias(libs.plugins.dueldex.android.hilt)
}

android {
    namespace = "com.example.dueldex.core.datastore.test"
}

dependencies {
    implementation(libs.hilt.android.testing)

    implementation(projects.core.common)
    implementation(projects.core.datastore)
}
