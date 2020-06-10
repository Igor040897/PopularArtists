package com.example.popularartists.data

import com.example.popularartists.data.models.Artist
import com.example.popularartists.data.models.Location
import com.example.popularartists.data.network.ResultObject

interface Repository {

    suspend fun topArtistByCountry(country: String): ResultObject<List<Artist>>

//    val allPurchaseProducts: LiveData<List<Product>>
//
//    val allNotPurchaseProducts: LiveData<List<Product>>
//
//    fun saveItemProduct(product: Product)
//
//    fun saveItemsProduct(products: List<Product>)
//
//    fun getTempImageFileUri(name: String): Uri?
//
//    fun saveImageToFile(uri: Uri): File?

}