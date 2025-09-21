package com.example.nfc3

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edit = findViewById<EditText>(R.id.editNumber)
        val btn  = findViewById<Button>(R.id.btnSave)

        edit.setText(NumberHolder.value)
        btn.setOnClickListener {
            NumberHolder.value = edit.text.toString().ifEmpty { "0" }
        }
    }
}
