package com.example.popularartists.data.dataBase

import androidx.room.Dao

@Dao
abstract class ArtistsDao {

//    @Query("SELECT * FROM Product WHERE isPurchase == 1 ")
//    abstract fun getAllPurchaseProducts(): LiveData<List<Product>>
//
//    @Query("SELECT * FROM Product WHERE isPurchase == 0")
//    abstract fun getAllNotPurchaseProducts(): LiveData<List<Product>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    abstract fun insertProduct(product: Product)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    abstract fun insertProducts(product: List<Product>)
}