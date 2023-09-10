@file:Suppress("DSL_SCOPE_VIOLATION", "UnstableApiUsage")

plugins {
	id("com.android.application")
	id("kotlin-android")
	id("com.chinnsenn.sdk-editor")
}

android {
	namespace = "com.chinnsenn.sdkeditor"
	compileSdk = 31

	defaultConfig {
		applicationId = "com.chinnsenn.sdkeditor"
		minSdk = 19
		targetSdk = 33
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
	kotlinOptions {
		jvmTarget = JavaLanguageVersion.of(11).toString()
	}
}

dependencies {
	implementation(libs.core.ktx)
	implementation(libs.appcompat)
	implementation(libs.constraintlayout)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.test.ext.junit)
	androidTestImplementation(libs.espresso.core)
}