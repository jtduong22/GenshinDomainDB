package com.example.genshindomaindb

import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.io.IOException
import java.lang.Exception
import java.util.*

class CharacterPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_page)

        // get information from previous page
        val character = intent.getStringExtra(EXTRA_CHARACTER)
        val bookSetId = intent.getIntExtra(EXTRA_BOOKSET, 0)

        // display toast for debugging purposes
        val test = "${character} ${bookSetId}"
        Toast.makeText(this, test, Toast.LENGTH_SHORT).show()

        // set picture
        // pictures are from FateTempest on reddit
        // source : https://www.reddit.com/r/Genshin_Impact/comments/km1z2t/more_f2u_genshin_impact_icons_ganyu_albedo_and/
        val characterImageView : ImageView = findViewById(R.id.characterImageView) as ImageView
        val imageResource = resources.getIdentifier(character?.toLowerCase(Locale.ROOT), "drawable", packageName)
        var characterAvatarDrawable : Drawable

        // try to set picture to specified character
        // defaults to diluc if picture does not exist
        try {
            characterAvatarDrawable = resources.getDrawable(imageResource)
        }
        catch ( e : Resources.NotFoundException)
        {
            characterAvatarDrawable = resources.getDrawable(R.drawable.diluc)
        }

        characterImageView.setImageDrawable(characterAvatarDrawable)

        // set textviews
        val nameTextView : TextView = findViewById(R.id.characterNameTextView) as TextView
        nameTextView.setText(character)

        val bookSetIdNameTextView : TextView = findViewById(R.id.characterBookSetTextView) as TextView
        bookSetIdNameTextView.setText(bookSetId.toString())
    }


}