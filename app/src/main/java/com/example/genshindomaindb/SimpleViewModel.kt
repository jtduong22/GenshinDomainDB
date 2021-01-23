package com.example.genshindomaindb

import org.jetbrains.annotations.NotNull

// object that holds data
public class SimpleViewModel {
    private var simpleText : String = ""

    constructor(@NotNull simpleText: String)
    {
        setSimpleText(simpleText)
    }

    fun setSimpleText(simpleText: String)
    {
        this.simpleText = simpleText
    }

    @NotNull
    fun getSimpleText(): String
    {
        return this.simpleText
    }




}