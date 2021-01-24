package com.example.genshindomaindb

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adapter for CharacterListPage
// Displays CharacterViewHolder into CardView
// Has a:   character image (ImageView)
//          character name (TextView)
//          character book set (TextView)
class CharacterAdapter(private val _characters : List<CharacterViewModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // list of all characters
//    private var _characters : List<CharacterViewModel> = ArrayList()

    // set layout to character_card_item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.character_card_item, parent, false)
        return CharacterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return _characters.size
    }

    // bind card to data
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder)
        {
            is CharacterViewHolder -> {
                holder.bind(_characters.get(position))
            }
        }
    }

    class CharacterViewHolder : RecyclerView.ViewHolder
    {
        val characterName : TextView
        val characterBook : TextView
        val characterImage : ImageView

        constructor(view : View) : super(view)
        {
            // get references
            this.characterName = view.findViewById(R.id.characterNameTextView) as TextView
            this.characterBook = view.findViewById(R.id.characterBookSetTextView) as TextView
            this.characterImage = view.findViewById(R.id.characterImageView) as ImageView

            // open character page on click
            view.setOnClickListener{openCharacterPage(view.context)}
        }

        fun bind(characterView : CharacterViewModel)
        {
            characterName.setText(characterView.getName())
            characterBook.setText(characterView.getBookSet().toString())
            characterImage.setImageDrawable(characterView.getImage())
        }

        // start the activity on the chosen character's page
        private fun openCharacterPage(context: Context)
        {
            val intent = Intent(context, CharacterPageActivity::class.java)

            // select character
            intent.putExtra(EXTRA_CHARACTER, characterName.text)
            intent.putExtra(EXTRA_BOOKSET, characterBook.text)

            context.startActivity(intent)
        }
    }


}