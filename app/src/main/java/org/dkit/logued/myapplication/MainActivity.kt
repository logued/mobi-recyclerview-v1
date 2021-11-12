package org.dkit.logued.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), PresidentListener {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // (create) get reference to a RecyclerView as specified in XML layout file
        val presidentsListView : RecyclerView = findViewById(R.id.presidents_list);

        // get reference to the array of president names
        val presidentsArray = resources.getStringArray(R.array.presidents)

        // instantiate (create) an Adapter passing in the array data, and
        // a reference to this Activity, which is a listener - a PresidentListener
        val presidentsListAdapter = PresidentsAdapter(presidentsArray, this)

        // set the adapter for the RecyclerView to be the presidents List Adapter
        // (which is of type RecyclerView.Adapter)
        presidentsListView.adapter = presidentsListAdapter
    }

    override fun onItemClick(presidentName: String) {

       // create a short Toast message - Snackbars are now preferred, see below
       // Toast.makeText(baseContext, president, Toast.LENGTH_SHORT)
       // .show()

        //alternatively we can create a Snackbar instead of a Toast
        //android.R.id.content finds the root element of current view
        Snackbar.make(findViewById(android.R.id.content), presidentName, Snackbar.LENGTH_LONG)
            .show()

        val intent = Intent(this, WebviewActivity::class.java)
        intent.putExtra("president", presidentName) // a president's name
        startActivity(intent)
    }
}