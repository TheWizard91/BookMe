package com.example.myapplication.json_data

import android.content.Context
import okio.IOException

class Utils {

    fun getJsonDataFromAssets(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}