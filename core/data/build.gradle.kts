@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.loldex.android.library)
    alias(libs.plugins.loldex.android.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.example.loldex.core.data"
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