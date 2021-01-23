package com.example.genshindomaindb

import android.content.Context
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper

class DBHelper(context: Context) : SQLiteAssetHelper(context, DB_NAME, null, DB_VERSION) {
    companion object
    {
        val DB_NAME : String = "domains.db"
        val DB_VERSION : Int = 1
    }
}