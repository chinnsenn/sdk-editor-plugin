// 配置项目的依赖源
dependencyResolutionManagement {
	repositories {
		google()
		mavenCentral()
		maven("https://jitpack.io")
		maven("https://plugins.gradle.org/m2/")
	}
}
// 将 convention 模块加入编译
include(":convention")