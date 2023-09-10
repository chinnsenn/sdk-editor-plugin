plugins {
	// 使用 kotlin dsl 作为 gradle 构建脚本语言
	`kotlin-dsl`
	`java-gradle-plugin`
	id("com.gradle.plugin-publish") version "1.2.1"
}

// 配置字节码的兼容性
java {
	sourceCompatibility = JavaVersion.VERSION_11
	targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
	// AGP 依赖
	implementation(libs.gradle.build)
	implementation(libs.gradle.api)
	implementation(libs.common)
	implementation(libs.commons.io)
	implementation(libs.sdk.common)

	implementation(gradleApi())
	implementation(libs.javassist)
	implementation(libs.sdk.editor.domain)
	implementation(libs.kotlin.stdlib)
	implementation(libs.kotlin.jdk8)
}

group = "com.chinnsenn"
version = "0.0.1"

gradlePlugin {
	plugins {
		// 注册插件，这样可以在其他地方 apply
		register("sdkEditorPlugin") {
			// 注册插件的 id，需要应用该插件的模块可以通过 apply 这个 id
			id = "${group}.${project.name}"
			displayName = "SDK Editor Plugin"
			description =
				"sdk-editor是一个在APP编译期修改类的轻量Gradle插件，插件利用Android Plugin官方提供的Transform API干预APK Build流程，实现对特定类的替换修改。无其他不安全Hook操作，100%可靠。\n注：该项目 fork 自 https://github.com/iwhys/sdk-editor-plugin，由于原项目年久失修，不支持 AGP 7 版本，故将其更新支持 AGP 7+"
			implementationClass = "${group}.sdkeditor.plugin.SdkEditorPlugin"
		}
	}
}

pluginBundle {
	website = "https://github.com/chinnsenn/sdk-editor-plugin"
	vcsUrl = "https://github.com/chinnsenn/sdk-editor-plugin.git"
	tags = listOf("sdk", "javassist", "replaceClass", "AGP7")
}

//tourist https://docs.gradle.org/current/userguide/publishing_gradle_plugins.html#consume

publishing {
	publications {
		register(project.name, MavenPublication::class.java) {
			artifactId = project.name
			from(components["java"])
		}
	}
	repositories {
		maven {
			url = uri("../local-plugin-repository")
		}
	}
}