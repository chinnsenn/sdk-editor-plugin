buildscript {
	dependencies {
		classpath("com.chinnsenn:sdk-editor:0.0.3-SNAPSHOT")
	}
}
plugins {
	id("com.android.application") version "7.0.2" apply false
	id("org.jetbrains.kotlin.android") version "1.4.31" apply false
}