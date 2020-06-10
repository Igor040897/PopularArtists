package com.example.popularartists.data.dataBase

class DbStorageManager private constructor(
    private val appDataBase: AppDataBase
) {

//    fun saveItemProduct(product: Product) {
//        appDataBase.productDao().insertProduct(product)
//    }

    companion object {

        @Volatile
        private var INSTANCE: DbStorageManager? = null

        fun getInstance(appDataBase: AppDataBase): DbStorageManager {
            return INSTANCE
                ?: synchronized(this) {
                INSTANCE
                    ?: DbStorageManager(
                        appDataBase
                    ).also { INSTANCE = it }
            }
        }
    }
}