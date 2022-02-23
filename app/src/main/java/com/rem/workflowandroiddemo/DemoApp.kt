package com.rem.workflowandroiddemo

import android.app.Application
import com.rem.workflowlibrary.Workflow

class DemoApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Workflow.init(this, BuildConfig.WORKFLOW_API_KEY)
    }

}