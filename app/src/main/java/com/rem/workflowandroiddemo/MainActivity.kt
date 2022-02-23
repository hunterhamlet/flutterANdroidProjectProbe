package com.rem.workflowandroiddemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rem.workflowandroiddemo.databinding.ActivityMainBinding
import com.rem.workflowlibrary.Workflow
import io.flutter.embedding.android.FlutterActivity

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.navigateToWorkflowScreen.setOnClickListener {
            startActivity(Workflow.starWorkflowActivity(this))
        }
    }
}