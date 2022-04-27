package com.estech.coordinatorlayoutsample

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.estech.coordinatorlayoutsample.bottomappbar.BottomAppBarActivity
import com.estech.coordinatorlayoutsample.collapsingconfab.CollapseYFab
import com.estech.coordinatorlayoutsample.collapsingtoolbar.CollapseCase1
import com.estech.coordinatorlayoutsample.collapsingtoolbar.CollapseCase2
import com.estech.coordinatorlayoutsample.collapsingtoolbar.CollapseCase3
import com.estech.coordinatorlayoutsample.collapsingtoolbar.CollapseCase4
import com.estech.coordinatorlayoutsample.databinding.ActivityMainBinding
import com.estech.coordinatorlayoutsample.floatingactionbutton.FabConSnackYScroll
import com.estech.coordinatorlayoutsample.floatingactionbutton.FabEnCabecera
import com.estech.coordinatorlayoutsample.parallax.ParallaxEfect
import com.estech.coordinatorlayoutsample.tablayout.ScrollTabLayout
import com.estech.coordinatorlayoutsample.toolbarsola.ToolbarEnterAlways
import com.estech.coordinatorlayoutsample.toolbarsola.ToolbarScroll
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->

            Snackbar.make(view, "Has enviado un correo", Snackbar.LENGTH_LONG)
                .setAction("Deshacer") {
                    Toast.makeText(this, "Acción revertida", Toast.LENGTH_SHORT).show()
                }
                .show()

        }

        val content = binding.content

        content.button1.setOnClickListener { view ->
            fabAlertDialog()
        }

        content.button3.setOnClickListener { view ->
            openActivity(BottomAppBarActivity::class.java)
        }

        content.button4.setOnClickListener { view ->
            toolbarAlertDialog()
        }

        content.button6.setOnClickListener { view ->
            colllapsingAlertDialog()
        }

        content.button10.setOnClickListener { view ->
            openActivity(ScrollTabLayout::class.java)
        }

        content.button11.setOnClickListener { view ->
            openActivity(CollapseYFab::class.java)
        }

        content.button12.setOnClickListener { view ->
            openActivity(ParallaxEfect::class.java)
        }
    }

    fun fabAlertDialog() {
        val lista = arrayOf("FAB hiding/showing con SnackBar", "FAB anclado entre vistas")

        val builder = AlertDialog.Builder(this)
        builder.setTitle("FAB Samples")
        builder.setItems(lista) { dialog, which ->
            if (which == 0) {
                openActivity(FabConSnackYScroll::class.java)
            } else {
                openActivity(FabEnCabecera::class.java)
            }
        }

        builder.create().show()
    }

    fun toolbarAlertDialog() {
        val lista = arrayOf("Toolbar son scroll", "Toolbar con snterAlways")

        val builder = AlertDialog.Builder(this)
        builder.setTitle("FAB Samples")
        builder.setItems(lista) { dialog, which ->
            if (which == 0) {
                openActivity(ToolbarScroll::class.java)
            } else {
                openActivity(ToolbarEnterAlways::class.java)
            }
        }

        builder.create().show()
    }

    fun colllapsingAlertDialog() {
        val lista = arrayOf(
            "Collapsing scroll|enterAlways",
            "Collapsing scroll|enterAlwaysCollapsed",
            "Collapsing scroll|enterAlways|enterAlwaysCollapsed",
            "Collapsing scroll|exitUntilCollapsed"
        )

        val builder = AlertDialog.Builder(this)
        builder.setTitle("FAB Samples")
        builder.setItems(lista) { dialog, which ->
            when (which) {
                0 -> openActivity(CollapseCase1::class.java)
                1 -> openActivity(CollapseCase2::class.java)
                2 -> openActivity(CollapseCase3::class.java)
                3 -> openActivity(CollapseCase4::class.java)
            }
        }

        builder.create().show()
    }

    fun openActivity(clase: Class<*>) {
        startActivity(Intent(this, clase))
    }
}