package com.example.lab_week_01

import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameDisplay = findViewById<TextView>(R.id.name_display)
        val numberDisplay = findViewById<TextView>(R.id.number_display)
        val submitButton = findViewById<Button>(R.id.submit_button)

        submitButton.setOnClickListener {
            val nameInput = findViewById<TextInputEditText>(R.id.name_input)
                ?.text.toString().trim()
            val numberInput = findViewById<TextInputEditText>(R.id.number_input)
                ?.text.toString().trim()

            // Handle name input
            if (nameInput.isNotEmpty()) {
                nameDisplay?.text = getString(R.string.name_greet).plus(" ").plus(nameInput)
            } else {
                Toast.makeText(this, getString(R.string.name_empty), Toast.LENGTH_LONG)
                    .apply {
                        setGravity(Gravity.CENTER, 0, 0)
                        show()
                    }
            }

            // Handle number input with 11-digit validation
            if (numberInput.isNotEmpty()) {
                if (numberInput.length == 11) {
                    numberDisplay?.text = getString(R.string.number_greet).plus(" ").plus(numberInput)
                } else {
                    Toast.makeText(this, getString(R.string.number_invalid_length), Toast.LENGTH_LONG)
                        .apply {
                            setGravity(Gravity.CENTER, 0, 0)
                            show()
                        }
                }
            } else {
                Toast.makeText(this, getString(R.string.number_empty), Toast.LENGTH_LONG)
                    .apply {
                        setGravity(Gravity.CENTER, 0, 0)
                        show()
                    }
            }
        }
    }
}
