@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.dueldex.jvm.library)
    id("kotlinx-serialization")
}

dependencies {
    api(libs.kotlinx.datetime)

    implementation(libs.kotlinx.serialization.json)
}