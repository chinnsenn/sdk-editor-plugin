plugins {
	// 使用 kotlin dsl 作为 gradle 构建脚本语言
	`kotlin-dsl`
//	`maven-publish`
}

// 配置字节码的兼容性
java {
	sourceCompatibility = JavaVersion.VERSION_17
	targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
	val agpVersion = "7.4.2"
	val kotlinVersion = "1.8.20"
	val catVersion = "31.1.1"
	// AGP 依赖
	implementation("com.android.tools.build:gradle:$agpVersion")
	implementation("com.android.tools.build:gradle-api:$agpVersion")
	implementation("com.android.tools:common:$catVersion")
	implementation("commons-io:commons-io:2.13.0")
	implementation("com.android.tools:sdk-common:$catVersion")

	implementation(gradleApi())
	implementation("org.javassist:javassist:3.29.2-GA")
	implementation("com.github.iwhys:sdk-editor-domain:1.2.0")
}

gradlePlugin {
	plugins {
		// 注册插件，这样可以在其他地方 apply
		register("SdkEditorPlugin") {
			// 注册插件的 id，需要应用该插件的模块可以通过 apply 这个 id
			id = "com.chinnsenn.sdkeditor.plugin"
			implementationClass = "com.chinnsenn.sdkeditor.plugin.SdkEditorPlugin"
		}
	}
}

//publishing {
//	publications {
//		create<MavenPublication>("maven") {
//			groupId = "com.chinnsenn"
//			artifactId = "sdk-editor"
//			version = "1.0"
//
//			from(components["java"])
//		}
//	}
//
//	repositories {
//		maven("../../repo")
//	}
//}