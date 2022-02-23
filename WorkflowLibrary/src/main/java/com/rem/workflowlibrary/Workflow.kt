package com.rem.workflowlibrary

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

object Workflow {

    private lateinit var flutterEngine: FlutterEngine
    private const val WORKFLOW_ENGINE = "workflow_engine"
    private const val WORKFLOW_CHANNEL = "workflow_channel"
    private const val INITIAL_ROUTE = "/"
    private lateinit var methodChannel : MethodChannel

    private const val TEST_METHOD = "test"


    fun init(context: Context, apiKey: String) {
        //Log.d("REM", "init")
        flutterEngine = FlutterEngine(context)
        initCacheFlutterEngine()
        sendInfo(apiKey)
    }

    fun starWorkflowActivity(appCompatActivity: AppCompatActivity): Intent {
        //Log.d("REM", "starWorkflowActivity")
        return FlutterActivity
            .withCachedEngine(WORKFLOW_ENGINE)
            .build(appCompatActivity)
    }

    private fun initCacheFlutterEngine() {
        //Log.d("REM", "initCacheFlutterEngine")
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )
        FlutterEngineCache.getInstance().put(WORKFLOW_ENGINE, flutterEngine)
        flutterEngine.navigationChannel.setInitialRoute(INITIAL_ROUTE)
    }

    private fun sendInfo(apiKey: String) {
        methodChannel =  MethodChannel(flutterEngine.dartExecutor.binaryMessenger,
            WORKFLOW_CHANNEL)
        methodChannel.setMethodCallHandler { call, result ->
            handleMethod(call, result)
        }
    }

    private fun handleMethod(method:MethodCall, result: MethodChannel.Result) {
        when (method.method) {
            TEST_METHOD -> {
                Log.d("REM", "data method: ${method.arguments}")
                val map = method.arguments.equals("data") as MutableMap<String, String>
                map.forEach { (key, value) ->
                    Log.d("REM", "key: ${key} -> value: ${value}")
                }
                result.success("Hai from android and this is the data you send")
            }
        }
    }

}