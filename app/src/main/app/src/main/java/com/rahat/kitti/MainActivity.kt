package com.rahat.kitti

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val requiredPermissions = arrayOf(
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.READ_CONTACTS,
        Manifest.permission.CALL_PHONE,
        Manifest.permission.SEND_SMS
    )

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            var grantedAll = true
            permissions.entries.forEach {
                if (!it.value) grantedAll = false
            }
            if (grantedAll) {
                Toast.makeText(this, "Permissions granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please grant required permissions", Toast.LENGTH_LONG).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_start).setOnClickListener {
            ensurePermissionsThen { startKittiService() }
        }

        findViewById<Button>(R.id.btn_stop).setOnClickListener {
            stopKittiService()
        }
    }

    private fun ensurePermissionsThen(onOk: () -> Unit) {
        val missing = requiredPermissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }
        if (missing.isEmpty()) {
            onOk()
        } else {
            requestPermissionLauncher.launch(missing.toTypedArray())
        }
    }

    private fun startKittiService() {
        val i = Intent(this, KittiForegroundService::class.java)
        ContextCompat.startForegroundService(this, i)
        Toast.makeText(this, "Kitti service started", Toast.LENGTH_SHORT).show()
    }

    private fun stopKittiService() {
        val i = Intent(this, KittiForegroundService::class.java)
        stopService(i)
        Toast.makeText(this, "Kitti service stopped", Toast.LENGTH_SHORT).show()
    }
}
