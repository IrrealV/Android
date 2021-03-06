package com.estech.vistasyclicks

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.widget.*
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvMensaje = findViewById<TextView>(R.id.tv_mensaje)
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

        //***********SEEKBAR******************/
        seekBar.max = 20 //configura el m??ximo del seekbar a 20 puntos
        seekBar.progress = 5 //el seekbar avanzar?? de 5 en 5

        //listener que se activa cuando el usuario desplaza el seekbar, tiene 3 funciones
        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            //esta funci??n se activa cuando el progreso del seekbar ha cambiado
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                tvMensaje.text =
                    "Progreso: $progress" //mostramos en el TextView el progreso del seekbar
            }

            //esta funci??n se activa justo cuando el usuario empieza a desplazar el indicador
            override fun onStartTrackingTouch(seekBar: SeekBar) {
                //no hemos implementado nada
            }

            //esta funci??n se activar??a cuando el usuario deja de desplazar el indicador
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })


        //***********RATINGBAR******************/
        ratingBar.numStars = 4 //configura 4 corazones como m??ximo en el rating bar
        ratingBar.stepSize = .3f //la votaci??n en el ratingbar avanzar?? en pasos de 0.3
        ratingBar.rating = 1.3f //de inicio, el ratingbar muestra una votaci??n de 1.3

        //listener que se activa cuando se cambia la votaci??n del rating bar
        ratingBar.setOnRatingBarChangeListener { rBar, fl, b ->
            tvMensaje.text = "Ha votado $fl" //mostrarmos en textview el voto seleccioando
        }

        //***********IMAGEVIEW******************/
        imageView.setImageResource(R.drawable.pulp_filction) //cambia la imagen por una en recursos
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP //cambia el escalado del imageview

        //***********TEXTVIEW MENSAJE******************/
        tvMensaje.text = "Bienvenido!!" //Cambiamos el mensaje inicial
        val myDrawable = ContextCompat.getDrawable(
            this,
            R.drawable.cuadro_amarillo
        ) //la variable myDrawable contiene un drawable de recursos
        tvMensaje.background = myDrawable //Cambiamos el background
        val myColor = ContextCompat.getColor(
            this,
            R.color.purple_700
        ) //la variable myColor contiene un color de nuestros recursos
        tvMensaje.setTextColor(myColor) //cambiamos el color de texto
        tvMensaje.isAllCaps = true //ponemos el texto con may??sculas


        //***********BUTTON******************/
        button.isClickable = false //deshabilitamos el click
        //tener en cuenta que si despues de esta instrucci??n se usa el setOnclickListener, el clic vuelve a habilitarse

        // listener de pulsaci??n simple sobre un bot??n
        button.setOnClickListener {
            showToast("Bot??n pulsado")
        }
        // listener de pulsaci??n larga sobre un bot??n
        button.setOnLongClickListener {
            showToast("Pulsaci??n larga del Button")
            //si devuelve true, se ejecuta el click largo nada mas
            //si devuelve false, tras el click largo se ejecuta tambi??n el listener del click corto, si existe
            false
        }


        //***********IMAGEBUTTON******************/
        //esta instrucci??n deshabilitar??a el bot??n
        imageButton.isEnabled = false
        //click largo sobre el imagebutton
        imageButton.setOnLongClickListener {
            showToast("Pulsaci??n larga del ImageButton")
            true
        }

        //***********FLOATING ACTION BUTTON******************/
        //click corto sobre el floating action button
        fab.setOnClickListener {
            tvMensaje.text = "Ha pulsado el bot??n redondo"
            tvMensaje.text = et_name.text.toString() // al pulsar, cogemos el texto del edittext y lo mostramos en el textview superior
        }


        //***********CHIP BUTTON******************/
        // click sobre el bot??n cerrar del button chip
        buttonChip.setOnCloseIconClickListener {
            showToast("Cerrado")
            buttonChip.visibility = View.GONE
        }
        // listener sobre el cambio de estado del chipgroup
        chipGroup.setOnCheckedChangeListener { group, checkedId -> } //est?? vac??o, no hace nada

        //***********CHECKBOX******************/
        //listener para el cambio de estado del checkbox
        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            //cambiamos el texto seg??n el estado del checkbox
            if (isChecked) {
                tvMensaje.text = "Checkbox marcado"
            } else {
                tvMensaje.text = "Checkbox no marcado"
            }
        }
        checkBox.isChecked =
            true //al cargar la app, el checkbox se mostrar?? marcado autom??ticamente


        //***********TOGGLEBUTTON******************/
        toggleButton.textOff = "Apagar" //cambia el texto en off del togglebutton
        toggleButton.textOn = "Encender"//cambia el texto en on del togglebutton

        //listener para el cambio de estado del toggle button
        toggleButton.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) progressBar.visibility = View.VISIBLE //si se activa, se muestra el progressbar
            else progressBar.visibility = View.INVISIBLE //si se desactiva, se oculta el progressbar
        }


        //***********RADIOGROUP******************/
        // listener para el cambio de estado de un radiogroup, es decir, comprende varios radiobuttons
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            // el listener devuelve la id del radiobutton seleccionado
            if (checkedId == R.id.radio1) {
                tvMensaje.text = "Pulsado Radio 1" //cambiamos el texto dependiendo del radiobutton seleccionado
            } else if (checkedId == R.id.radio2) {
                tvMensaje.text = "Pulsado Radio 2"
            }
        }

        //***********RADIOBUTTONBUTTON******************/
        //listener para el cambio de estado de un ??nico radiobutton
        //es una funci??n que, normalmente, no tiene sentido aplicar en un solo radiobutton
        radio1.setOnCheckedChangeListener { buttonView, isChecked -> }


        //***********SWITCH******************/
        // listener para el cambio de estado del switch
        switchCompat.setOnCheckedChangeListener { buttonView, isChecked ->
            // muestra un mensaje de error en el edittext al activar el switch
            if (isChecked) {
                et_name.error = "Debes escribir tu nombre"
            } else {
                et_name.error = null
            }
        }

        //***********EDITTEXT******************/
        //con la siguiente instrucci??n har??amos el edittext del nombre no focusable, es decir, no se podr??a pinchar en ??l para escribir
        //et_name.isFocusable = false
        et_name.hint = "Nooombre" //cambia el hint de un edittext
        et_pass.requestFocus() //hace que el edittext gane el foco, es decir, al ejecutar la App, directarmente se activa este edittext para escribir sobre ??l
        et_pass.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD //cambiar el inputtype

        //listener que se activa al cambiar el texto de un edittext
        et_pass.addTextChangedListener(object : TextWatcher {
            //esta funci??n se activa al escribir en el edittext, justo antes de que la letra se muestre en pantalla
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            //esta funci??n se activa al escribir en el edittext, justo despues de que la letra se muestre en pantalla
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                //como la variable start indica la posici??n dentro del string donde se ha a??adido el caracter
                //podemos contar cu??ntas letras se han escrito
                if (s.length > 10) {
                    tvMensaje.text = "Te has pasado"
                } else if (s.length < 5) {
                    tvMensaje.text = "No has llegado"
                } else {
                    tvMensaje.text = "Vas bien"
                }
            }

            // funci??n que se activa cuando ya se ha escrito un caracter en el edittext, devolviendo la palabra resultante
            override fun afterTextChanged(s: Editable) {}
        })

    }

    /**
     * funci??n para mostrar un toast
     * Recibe como par??metro un mensaje de tipo String
     */
    private fun showToast(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}