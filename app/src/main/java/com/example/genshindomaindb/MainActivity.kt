package com.example.genshindomaindb

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

const val EXTRA_CHARACTER = "com.example.genshindomaindb.CHARACTER_NAME"
const val EXTRA_BOOKSET = "com.example.genshindomaindb.BOOK_SET_ID"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Open to Character List
        _startCharacterListActivity()
    }

    // test function read from database
    fun testReadDB () : List<String>
    {
        // open database
        val db = DBHelper(this)

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
        db.close()

        return items
    }

    ///// EARLY TESTING FUNCTIONS TO OPEN STRAIGHT TO PAGE /////

    // open up character page
    fun _startActivity()
    {

        val intent = Intent(this, CharacterPageActivity::class.java)

        // pass data for page to parse
        intent.putExtra(EXTRA_CHARACTER, "Chongyun")
        intent.putExtra(EXTRA_BOOKSET, 10)

        startActivity(intent)
    }

    // open up character list page
    fun _startCharacterListActivity()
    {
        val intent = Intent(this, CharacterListPageActivity::class.java)

        startActivity(intent)
    }

}