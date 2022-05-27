package com.estech.takephotosample

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.estech.takephotosample.databinding.ActivityMainBinding
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var uri: Uri
    private lateinit var uriCam : Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btCamara.setOnClickListener {
            tomarImagenCamara()
        }

        binding.btGaleria.setOnClickListener {
            tomarImagenGaleria()
        }
    }

    private fun tomarImagenCamara() {
       val archivoFoto =  crearArchivoParaFoto()
        uriCam = FileProvider.getUriForFile(this ,"${packageName}.fileprovider", archivoFoto)

        ICamara.launch(uriCam)
    }




    @Throws(IOException::class)
    private fun crearArchivoParaFoto(): File {
        //nombre de archivo con fecha y hora actual
        val timeStamp: String =
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        // ruta a la carpeta privada de la App
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        // Crea un objeto File con el nombre de archivo, la extensión y la carpeta donde se almacena el archivo
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefijo */
            ".jpg", /* sufijo */
            storageDir /* directorio */
        )
    }

    private fun tomarImagenGaleria(){
        IGaleria.launch(arrayOf("image/*"))
    }

    private val IGaleria = registerForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri ->
        uri?.let {
            binding.imageView.setImageURI(uri)
        }
    }

    private val ICamara = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSaved ->
        if (isSaved) {
            binding.imageView.setImageURI(uriCam)
        }
        else{
            val rutaArchivo = getExternalFilesDir("/Imagenes/" + uriCam.path)

            rutaArchivo?.let {
                val resultado = rutaArchivo.delete()
                if (resultado) Toast.makeText(this, "Imagen Eliminada", Toast.LENGTH_SHORT).show()
                else Toast.makeText(this, "ERROR BORRANDO", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /*<external-path name="my_images" path="/" />
    val storageDir: File? = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)*/
}