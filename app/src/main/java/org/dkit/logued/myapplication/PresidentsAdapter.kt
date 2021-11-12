package org.dkit.logued.myapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// DL The class definition below taked two parameters-  "data" and "listener"
// This causes two instance variables to be created, and also acts as
// a constructor to initialize the two fields.
// These fields are accessible throughout the class.
//
class PresidentsAdapter(val data: Array<String>,
                        val listener: PresidentListener)
    : RecyclerView.Adapter<TextItemViewHolder>() {

    override fun getItemCount() = data.size
// The above line is equivalent to the following function:
//    override fun getItemCount() : Int  {
//        return data.size
//    }

    // onCreateViewHolder() is called by this RecyclerView.Adapter
    // when it needs to instantiate (create) a new item view (a row view).
    // onBindViewHolder() will subsequently be called in order to fill the
    // view with data from theAdapters data source (the array)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.item_layout, parent, false)
        return TextItemViewHolder(view)
    }

    // DL - called by system to update the view (row) in a RecyclerView
    // - holder - is the view to be populated for a row of data
    // - position - is the index position of the data item within the Adapter
    // This gives use the chance to load data into the view (row)
    // This is called when a new row is about to appear on screen due
    // to scrolling up or down.
    // This may be called to load a new view, or to update the content of
    // an existing view in the holder.
    //
    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {

        // holder.textView.text = data[position]  // assign a data value into the view

        holder.bind(data[position], listener)   // call bind() to set data and register listener
    }
}

// Create our own ViewHolder class that matches the structure of the
// view that makes up one row of the RecyclerView
//
class TextItemViewHolder(view: View): RecyclerView.ViewHolder(view) {

    // get the TextView view (representing one row in the RecyclerView
    val textView: TextView = view.findViewById(R.id.itemTextView)

    // bind the data to the view, and register a listener to the view
    fun bind(presidentName: String, listener: PresidentListener){

        //DL - in th eemulator, croll up and down in the list to see
        // that the bind() method is called as new names scroll into
        // the list (from top or bottom).  Don't leave this on as it
        // slows down the app.
        Log.e("President", "name: " +  presidentName)

        textView.text = presidentName;
        textView.setOnClickListener { listener.onItemClick(presidentName)}
    }
}

interface PresidentListener{

    fun onItemClick(president: String)
}
