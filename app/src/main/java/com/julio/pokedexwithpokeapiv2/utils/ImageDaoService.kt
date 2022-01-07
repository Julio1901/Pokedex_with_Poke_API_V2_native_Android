package com.julio.pokedexwithpokeapiv2.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LifecycleCoroutineScope

import com.julio.pokedexwithpokeapiv2.dao.PokemonDaoEntity
import com.julio.pokedexwithpokeapiv2.dao.PokemonDataBase
import kotlinx.coroutines.launch

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class ImageDaoService {

    //Receive image that will be sent as BitMap
    fun saveImageInBank(imageBitmap: Bitmap) : ByteArray{

        val stream = ByteArrayOutputStream()
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        val imagemBytes = stream.toByteArray()

        return imagemBytes
    }

    //Takes image from database and converts to display
    fun convertBankImageToDisplay(bankImage : ByteArray) : Bitmap {

        val imageStream = ByteArrayInputStream(bankImage)
        val imageBitmap = BitmapFactory.decodeStream(imageStream)

        return imageBitmap
    }


}