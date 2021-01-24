package com.example.genshindomaindb

import android.database.Cursor
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class CharacterListPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_list_page)

        // intialize adapter with list of characters taken from database
        val adapter  = CharacterAdapter(getCharacterInfo())

        // set options for recyclerview
        val recyclerView : RecyclerView = findViewById(R.id.characterListRecyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    // retrieve list of characters and info from database
    // gets name, id of books used, and image
    private fun getCharacterInfo() : List<CharacterViewModel>
    {
        val db = DBHelper(this)

        val command = "select name from characters"
        val cursor: Cursor = db.readableDatabase.rawQuery(command, null)
        cursor.moveToFirst()

        // list of all characters
        val characterList : MutableList<CharacterViewModel> = ArrayList<CharacterViewModel>()

        while (cursor.isAfterLast == false)
        {
            val characterName = cursor.getString(0)
            val bookSet = 0
            val imageID : Int

            // check if image exists in drawable folder
            // default to diluc if image does not exist
            // NOTE: images are in lowercase while names from db are uppercase
            if (resources.getIdentifier(characterName?.toLowerCase(Locale.ROOT), "drawable", packageName) != 0)
            {
                imageID = resources.getIdentifier(characterName?.toLowerCase(Locale.ROOT), "drawable", packageName)
            }
            else
            {
                imageID = R.drawable.diluc
            }

            val character = CharacterViewModel(characterName, bookSet,  resources.getDrawable(imageID))

            // add character to list
            characterList.add(character)
            Log.i("myapp", cursor.getString(0))

            cursor.moveToNext()
        }

        cursor.close()
        db.close()

        return characterList
    }

}


