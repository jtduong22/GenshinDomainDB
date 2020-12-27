package com.example.genshindomaindb

import android.app.Activity
import android.content.Context
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        test()
    }

    // test function read from database
    fun test ()
    {
        // open database
        val db: DBHelper = DBHelper(this)

        // query database
        val c: Cursor = db.readableDatabase.rawQuery("select * from items", null)
        c.moveToFirst()

        Log.i("myapp", "testing $c.toString() ${c.isAfterLast}")

        // keep iterating through all rows while there's still data to read
        while (c.isAfterLast == false)
        {
            // read each column of current row
            for (i in 0 until c.columnCount)
            {
                // print all row to log
                Log.i("myapp", c.getString(i))
            }

            // move to next row
            c.moveToNext()
        }

        c.close()
    }
}