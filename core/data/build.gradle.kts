@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.dueldex.android.library)
    alias(libs.plugins.dueldex.android.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.example.dueldex.core.data"
}

dependencies {
    api(projects.core.common)
    api(projects.core.database)
    api(projects.core.datastore)
    api(projects.core.model)
    api(projects.core.network)

    implementation(libs.sandwich)
    implementation(libs.kotlinx.serialization.json)

    api(libs.androidx.paging.runtime)
    api(libs.androidx.paging.compose)
}