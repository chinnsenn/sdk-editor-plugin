@file:Suppress("UnstableApiUsage")

pluginManagement {
	includeBuild("sdk-editor-plugin")
	repositories {
		google()
		mavenCentral()
		gradlePluginPortal()
		mavenLocal()
		maven("https://jitpack.io")
		maven("https://plugins.gradle.org/m2/")
	}
}
dependencyResolutionManagement {
	repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
	repositories {
		google()
		mavenCentral()
		mavenLocal()
		maven("https://jitpack.io")
		maven("https://plugins.gradle.org/m2/")
	}
}

rootProject.name = "sdk-editor-plugin-agp7"
include(":app")
