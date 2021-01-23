package com.example.genshindomaindb

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

const val EXTRA_CHARACTER = "com.example.genshindomaindb.CHARACTER_NAME"
const val EXTRA_BOOKSET = "com.example.genshindomaindb.BOOK_SET_ID"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        testReadDB()

        // generate list
        var adapter = SimpleAdapter(generateItemList(testReadDB()))

        // generate view
        var recyclerView: RecyclerView = findViewById(R.id.domain_recyclerview) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    // test function read from database
    fun testReadDB () : List<String>
    {
        // open database
        val db: DBHelper = DBHelper(this)

        // query database
        val c: Cursor = db.readableDatabase.rawQuery("select * from items", null)
        c.moveToFirst()

        val items : MutableList<String> = ArrayList()

        Log.i("myapp", "testing $c.toString() ${c.isAfterLast}")

        // keep iterating through all rows while there's still data to read
        while (c.isAfterLast == false)
        {

            val item : MutableList<String> = ArrayList()
            // read each column of current row
            for (i in 0 until c.columnCount)
            {
                // print all row to log
                Log.i("myapp", c.getString(i))
                item.add(c.getString(i))
            }
            items.add(item.joinToString(" "))

            // move to next row
            c.moveToNext()
        }

        // close database
        c.close()

        return items
    }

    // test function populate
    fun testPopulateTable()
    {
        
    }

    ///// EARLY TESTING FUNCTION FOR RECYCLER VIEW /////

    private fun generateSimpleList() : List<SimpleViewModel> {
        var simpleViewModeList = ArrayList<SimpleViewModel>()

        for (i in 0..100) {
            simpleViewModeList.add(SimpleViewModel("This is item ${i}"))
        }

        return simpleViewModeList
    }

    private fun generateItemList(items : List<String>) : List<SimpleViewModel>
    {
        var simpleViewModelList = ArrayList<SimpleViewModel>()

        for (item in items)
        {
            simpleViewModelList.add(SimpleViewModel(item))
        }

        return simpleViewModelList
    }


    // open up character page
    fun _startActivity(view: View)
    {

        val intent = Intent(this, CharacterPageActivity::class.java)

        // pass data for page to parse
        intent.putExtra(EXTRA_CHARACTER, "Chongyun")
        intent.putExtra(EXTRA_BOOKSET, 10)

        startActivity(intent)
    }

}