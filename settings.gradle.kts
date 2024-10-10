pluginManagement {
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
    }
}

rootProject.name = "AutoHub"
include(":app")
include(":core:storage")
include(":core:utils")
include(":feature")
include(":feature:common")
include(":feature:settings")
include(":feature:navigation")
include(":feature:search")
include(":feature:authentication")
include(":feature:car_offers")
