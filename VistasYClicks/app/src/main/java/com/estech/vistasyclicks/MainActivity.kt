package com.estech.vistasyclicks

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.tv_mensaje)
        val button = findViewById<Button>(R.id.button)
        val imageButton = findViewById<ImageButton>(R.id.imageButton)
        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        val buttonChip = findViewById<Chip>(R.id.button_chip)
        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        val toggleButton = findViewById<ToggleButton>(R.id.toggleButton)
        val switchCompat = findViewById<SwitchCompat>(R.id.switch1)
        val radioGroup = findViewById<RadioGroup>(R.id.radiogroup)
        val radio1 = findViewById<RadioButton>(R.id.radio1)
        val radio2 = findViewById<RadioButton>(R.id.radio2)
        val chipGroup = findViewById<ChipGroup>(R.id.chipgroup)
        val et_name = findViewById<EditText>(R.id.et_person_name)
        val et_pass = findViewById<TextInputEditText>(R.id.et_password)
        val imageView = findViewById<ImageView>(R.id.imageView)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)

        val toast = Toast.makeText(this, "hola", Toast.LENGTH_LONG)
        toast.show()

        Toast.makeText(this, "hola", Toast.LENGTH_LONG).show()

        var a = 5
        Log.d("MainActivity", "me")


        button.setOnClickListener {
            a = 6

            Toast.makeText(this, "hola $a", Toast.LENGTH_LONG).show()
        }


        button.setOnLongClickListener {
            Toast.makeText(this, "Hola otra vez", Toast.LENGTH_LONG).show()
            true
        }
        var contador = 0

        checkBox.setOnCheckedChangeListener { a, b ->
            contador++
            Toast.makeText(this, "Contador: $contador", Toast.LENGTH_SHORT).show()
            if (b) {
                textView.visibility = View.VISIBLE
            } else {
                textView.visibility = View.GONE
            }
        }
    }
}