@Suppress("DSL_SCOPE_VIOLATION")
plugins {
	alias(libs.plugins.com.android.application)
	alias(libs.plugins.org.jetbrains.kotlin.android)
	id("com.chinnsenn.sdkeditor.plugin")
}

android {
	namespace = "com.chinnsenn.sdkeditor"
	compileSdk = 33

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
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}
	kotlinOptions {
		jvmTarget = "17"
	}
}

dependencies {
	implementation(libs.core.ktx)
	implementation(libs.appcompat)
	implementation(libs.material)
	implementation(libs.constraintlayout)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.test.ext.junit)
	androidTestImplementation(libs.espresso.core)
}