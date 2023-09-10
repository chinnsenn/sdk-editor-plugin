@file:Suppress("DSL_SCOPE_VIOLATION", "UnstableApiUsage")
enableFeaturePreview("VERSION_CATALOGS")
// 配置项目的依赖源
dependencyResolutionManagement {
	repositories {
		google()
		mavenCentral()
		maven("https://jitpack.io")
		maven("https://plugins.gradle.org/m2/")
	}

	versionCatalogs {
		create("libs") {
			alias("common").to("com.android.tools:common:27.2.1")
			alias("sdk-common").to("com.android.tools:sdk-common:27.2.1")
			alias("commons-io").to("commons-io:commons-io:2.13.0")
			alias("core-ktx").to("androidx.core:core-ktx:1.6.0")
			alias("gradle-build").to("com.android.tools.build:gradle:7.0.1")
			alias("gradle-api").to("com.android.tools.build:gradle-api:7.0.1")
			alias("javassist").to("org.javassist:javassist:3.29.2-GA")
			alias("junit").to("junit:junit:4.13.2")
			alias("androidx-test-ext-junit").to("androidx.test.ext:junit:1.1.5")
			alias("espresso-core").to("androidx.test.espresso:espresso-core:3.5.1")
			alias("appcompat").to("androidx.appcompat:appcompat:1.2.0")
			alias("constraintlayout").to("androidx.constraintlayout:constraintlayout:2.1.4")
			alias("kotlin-stdlib").to("org.jetbrains.kotlin:kotlin-stdlib:1.4.31")
			alias("kotlin-jdk8").to("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.31")
			alias("sdk-editor-domain").to("com.github.iwhys:sdk-editor-domain:1.2.0")
			alias("com-android-tools-build").to("com.android.tools.build:gradle:7.0.1")
			alias("org-jetbrains-kotlin").to("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.31")
		}
	}
}
// 将 sdk-editor 模块加入编译
include(":sdk-editor")