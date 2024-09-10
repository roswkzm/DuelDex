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

rootProject.name = "DuelDex"
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
include(":core:ui")
include(":core:database")

include(":feature:home")
include(":feature:decks")
include(":feature:detail")
include(":feature:search")
