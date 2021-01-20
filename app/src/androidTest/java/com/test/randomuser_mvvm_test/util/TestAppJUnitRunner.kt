package com.test.randomuser_mvvm_test.util

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.test.runner.AndroidJUnitRunner

class TestAppJUnitRunner : AndroidJUnitRunner() {

     override fun onCreate(arguments: Bundle?) {
          super.onCreate(arguments)


     }

     override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
          return super.newApplication(cl, TestApp::class.java.name, context)
     }
}