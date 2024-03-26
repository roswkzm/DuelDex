@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.loldex.jvm.library)
}

dependencies {
    api(libs.kotlinx.datetime)
}