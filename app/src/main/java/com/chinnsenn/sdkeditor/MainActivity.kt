package com.chinnsenn.sdkeditor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.BuildCompat

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		BuildCompat.isAtLeastQ()
	}
}