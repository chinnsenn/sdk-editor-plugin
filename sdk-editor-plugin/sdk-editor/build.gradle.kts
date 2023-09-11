plugins {
	// 使用 kotlin dsl 作为 gradle 构建脚本语言
	`kotlin-dsl`
	`java-gradle-plugin`
	`maven-publish`
	signing
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

val GROUP: String by project
val VERSION_NAME: String by project

val POM_NAME: String by project
val POM_DESCRIPTION: String by project
val POM_INCEPTION_YEAR: String by project
val POM_URL: String by project

val POM_LICENSE_NAME: String by project
val POM_LICENSE_URL: String by project
val POM_LICENSE_DIST: String by project

val POM_SCM_URL: String by project
val POM_SCM_CONNECTION: String by project
val POM_SCM_DEV_CONNECTION: String by project

val POM_DEVELOPER_ID: String by project
val POM_DEVELOPER_NAME: String by project
val POM_DEVELOPER_EMAIL: String by project
val POM_DEVELOPER_URL: String by project

val ossrhUsername: String by project
val ossrhPassword: String by project

group = GROUP
version = VERSION_NAME

gradlePlugin {
	plugins {
		// 注册插件，这样可以在其他地方 apply
		register("sdkEditorPlugin") {
			// 注册插件的 id，需要应用该插件的模块可以通过 apply 这个 id
			id = "${group}.${project.name}"
			displayName = POM_NAME
			description = POM_DESCRIPTION
			implementationClass = "${group}.sdkeditor.plugin.SdkEditorPlugin"
		}
	}
}

publishing {
	afterEvaluate {
		publications {
			val sourceSets = the<SourceSetContainer>()
			val sourcesJar = tasks.register("packageSourcesFor${name.capitalize()}", Jar::class.java) {
				archiveClassifier.set("sources")
				from(sourceSets["main"].allSource)
			}
			val javadocJar = tasks.register("packageJavaDocFor${name.capitalize()}", Jar::class.java) {
				archiveClassifier.set("javadoc")
			}
			register(project.name, MavenPublication::class.java) {
				groupId = group.toString()
				artifactId = project.name
				afterEvaluate {
					from(components["java"])
				}
				artifact(sourcesJar)
				artifact(javadocJar)
				pom {
					name.set(POM_NAME)
					description.set(POM_DESCRIPTION)
					url.set(POM_URL)
					licenses {
						license {
							name.set(POM_LICENSE_NAME)
							url.set(POM_LICENSE_URL)
						}
					}
					developers {
						developer {
							id.set(POM_DEVELOPER_ID)
							name.set(POM_DEVELOPER_NAME)
							email.set(POM_DEVELOPER_EMAIL)
							url.set(POM_DEVELOPER_URL)
						}
					}
					scm {
						url.set(POM_SCM_URL)
						connection.set(POM_SCM_CONNECTION)
						developerConnection.set(POM_SCM_DEV_CONNECTION)
					}

					withXml {
						val root = asNode()
						val dependencies = (root["dependencies"] as groovy.util.NodeList).firstOrNull() as groovy.util.Node?
							?: root.appendNode("dependencies")
						configurations.configureEach {
							this.dependencies.forEach {
								if (this.name == "implementation") {
									if (it.group?.isNotBlank() == true && (it.name.isNotBlank() || "unspecified" == it.name) && it.version?.isNotBlank() == true) {
										val dependency = dependencies.appendNode("dependency")
										dependency.appendNode("groupId", it.group)
										dependency.appendNode("artifactId", it.name)
										dependency.appendNode("version", it.version)
										dependency.appendNode("scope", "implementation")
									}
								}
							}
						}
					}
				}
			}

			named("sdkEditorPluginPluginMarkerMaven", MavenPublication::class.java) {
				pom {
					name.set(POM_NAME)
					description.set(POM_DESCRIPTION)
					url.set(POM_URL)
					licenses {
						license {
							name.set(POM_LICENSE_NAME)
							url.set(POM_LICENSE_URL)
						}
					}
					developers {
						developer {
							id.set(POM_DEVELOPER_ID)
							name.set(POM_DEVELOPER_NAME)
							email.set(POM_DEVELOPER_EMAIL)
							url.set(POM_DEVELOPER_URL)
						}
					}
					scm {
						url.set(POM_SCM_URL)
						connection.set(POM_SCM_CONNECTION)
						developerConnection.set(POM_SCM_DEV_CONNECTION)
					}
				}
			}
		}
	}

	repositories {
		maven {
			val versionIsSnapshot = version.toString().endsWith("-SNAPSHOT")
			url =
				uri(if (versionIsSnapshot) "https://s01.oss.sonatype.org/content/repositories/snapshots/" else "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
			credentials {
				// 这里就是之前在 issues.sonatype.org 注册的账号
				username = ossrhUsername
				password = ossrhPassword
			}
		}
	}
}

signing {
	sign(publishing.publications)
}