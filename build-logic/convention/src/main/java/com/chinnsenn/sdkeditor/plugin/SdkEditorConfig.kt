package com.chinnsenn.sdkeditor.plugin

import org.gradle.api.Project

open class SdkEditorConfig {

	/**
	 * 是否开启并行处理任务，false单线程处理，true使用协程多线程处理任务
	 */
	var parallel: Boolean = false

	/**
	 * Alias for [extraJarNames]
	 */
	var fixedJarNames: Set<String>? = null

	fun fixedJarNamesSet() = fixedJarNames

	companion object {
		fun create(project: Project): SdkEditorConfig =
			project.extensions.create(SdkEditorPlugin.PLUGIN_NAME, SdkEditorConfig::class.java)

		operator fun get(project: Project): SdkEditorConfig =
			(project.extensions.getByName(SdkEditorPlugin.PLUGIN_NAME) as? SdkEditorConfig) ?: SdkEditorConfig()
	}
}