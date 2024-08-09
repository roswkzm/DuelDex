pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

rootProject.name = "LolDex"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":core")
include(":core:designsystem")
include(":core:network")
include(":core:model")
include(":core:domain")
include(":core:data")
include(":core:common")
include(":core:datastore")
include(":core:datastore-proto")

include(":feature:home")
include(":feature:bookmarks")
include(":feature:settings")
include(":feature:detail")
include(":core:ui")
