package edu.temple.desserrtapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.res.Configuration
import android.util.Log

class SelectionActivity : AppCompatActivity() {

    /**
     * Companion objects are used in Kotlin
     * as containers of public static fields
     */
    companion object {
        val ITEM_KEY = "key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = getString(R.string.selection_activity_title)

        val items = generateTestData()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val columnCount = when {
            resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE -> 4
            resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE -> 4
            else -> 3
        }

        recyclerView.layoutManager = GridLayoutManager(this, columnCount)

        val clickEvent = {item: Item ->
            // Item object can be placed directly inside Intent because
            // the Item class implements the Parcelable interface
            val launchIntent = Intent(this, DisplayActivity::class.java)
                .putExtra(ITEM_KEY, item)

            startActivity(launchIntent)
        }

        recyclerView.adapter = ImageAdapter(items, clickEvent)
    }

    /**
     * Generate test info for app
     */
    fun generateTestData(): Array<Item> {
        val dessertNames = resources.getStringArray(R.array.dessert_names)
        
        // Add this debugging line
        Log.d("SelectionActivity", "Loaded dessert names: ${dessertNames.joinToString()}")
        
        return arrayOf(
            Item(R.drawable.ccf_original, dessertNames[0]),
            Item(R.drawable.ccf_freshstrawberry, dessertNames[1]),
            Item(R.drawable.ccf_chocolatecaramelicious, dessertNames[2]),
            Item(R.drawable.ccf_pineappleupsidedown, dessertNames[3]),
            Item(R.drawable.ccf_celebration, dessertNames[4]),
            Item(R.drawable.ccf_caramelapple, dessertNames[5]),
            Item(R.drawable.ccf_verycherryghirardellichocolate, dessertNames[6]),
            Item(R.drawable.ccf_lowlicious, dessertNames[7]),
            Item(R.drawable.ccf_cinnaboncinnamoncwirl, dessertNames[8]),
            Item(R.drawable.ccf_godiva, dessertNames[9]),
            Item(R.drawable.ccf_coconutcreampie, dessertNames[10]),
            Item(R.drawable.ccf_saltedcaramel, dessertNames[11])
        )
    }
}
