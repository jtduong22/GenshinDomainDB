package com.example.genshindomaindb

import android.content.Context
import android.graphics.drawable.Drawable
import org.jetbrains.annotations.NotNull

class CharacterViewModel(private val NAME: String, private val BOOK_SET: Int, private val IMAGE: Drawable)
{
    fun getName() : String
    {
        return this.NAME
    }

    fun getBookSet() : Int
    {
        return this.BOOK_SET
    }

    fun getImage() : Drawable
    {
        return this.IMAGE
    }
}