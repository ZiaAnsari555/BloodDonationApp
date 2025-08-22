package com.villarica.villarica.local

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yodeck.models.Data

class SharedPrefs private constructor() {
    companion object {
        const val SUBSCRIPTION_PURCHASED = "SUBSCRIPTION_PURCHASED"
        private val sharePref = SharedPrefs()
        private lateinit var sharedPreferences: SharedPreferences
        fun getInstance(context: Context): SharedPrefs {
            if (!Companion::sharedPreferences.isInitialized) {
                synchronized(SharedPrefs::class.java) {
                    if (!Companion::sharedPreferences.isInitialized) {
                        sharedPreferences =
                                context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)
                    }
                }
            }
            return sharePref
        }

        const val isLoggedIn = "isLoggedIn"
        const val email = "email"
        const val password = "password"
        const val token = "token"
        const val firebaseToken = "firebase_token"
        const val user = "user"
        const val userId = "userId"
        const val suppliers = "suppliers"
        const val banks = "banks"
        const val categories = "categories"
        const val basket_products = "basket_products"
        const val customers = "customers"
        const val noSaleReasons = "noSaleReasons"
        const val lat = "lat"
        const val lng = "lng"
        const val products = "products"

        fun setSubscriptionToggle(context: Context, isPurchased: String) {
            getPrefs(context).edit().putString(SUBSCRIPTION_PURCHASED, isPurchased).apply()
        }

        fun getSubscriptionToggle(context: Context): String? {
            return getPrefs(context).getString(SUBSCRIPTION_PURCHASED, null)
        }

        private fun getPrefs(context: Context): SharedPreferences {
            return context.getSharedPreferences(context.packageName, Context.MODE_MULTI_PROCESS)
        }

        //Save Booleans
        fun savePref(context: Context, key: String, value: Boolean) {
            getPrefs(context).edit().putBoolean(key, value).apply()
        }

    }

    var latitude: String?
    get(){
        return sharedPreferences.getString(lat, "")
    }
    set(value) {
        sharedPreferences.edit().putString(lat, value).apply()
    }

    var longitude: String?
        get(){
            return sharedPreferences.getString(lng, "")
        }
        set(value) {
            sharedPreferences.edit().putString(lng, value).apply()
        }


    var userData: Data?
        get() {
            val json = sharedPreferences.getString(user, null)
            return Gson().fromJson(json, Data::class.java)
        }
        set(value) {
            sharedPreferences.edit().putString(user, Gson().toJson(value)).apply()
        }

//    var suppliersData: SuppliersResponse?
//        get() {
//            val json = sharedPreferences.getString(suppliers, null)
//            return Gson().fromJson(json, SuppliersResponse::class.java)
//        }
//        set(value) {
//            sharedPreferences.edit().putString(suppliers, Gson().toJson(value)).apply()
//        }
//
//    var banksData: BanksResponse?
//        get() {
//            val json = sharedPreferences.getString(banks, null)
//            return Gson().fromJson(json, BanksResponse::class.java)
//        }
//        set(value) {
//            sharedPreferences.edit().putString(banks, Gson().toJson(value)).apply()
//        }
//
//    var categoriesData: BanksResponse?
//        get() {
//            val json = sharedPreferences.getString(categories, null)
//            return Gson().fromJson(json, BanksResponse::class.java)
//        }
//        set(value) {
//            sharedPreferences.edit().putString(categories, Gson().toJson(value)).apply()
//        }
//
//    var basketProducts: BasketProductsResponse?
//        get() {
//            val json = sharedPreferences.getString(basket_products, null)
//            return Gson().fromJson(json, BasketProductsResponse::class.java)
//        }
//        set(value) {
//            sharedPreferences.edit().putString(basket_products, Gson().toJson(value)).apply()
//        }
//
//    var customersData: BanksResponse?
//        get() {
//            val json = sharedPreferences.getString(customers, null)
//            return Gson().fromJson(json, BanksResponse::class.java)
//        }
//        set(value) {
//            sharedPreferences.edit().putString(customers, Gson().toJson(value)).apply()
//        }
//
//    var noSalesReasonsData: NoSaleReasonsResponse?
//        get() {
//            val json = sharedPreferences.getString(noSaleReasons, null)
//            return Gson().fromJson(json, NoSaleReasonsResponse::class.java)
//        }
//        set(value) {
//            sharedPreferences.edit().putString(noSaleReasons, Gson().toJson(value)).apply()
//        }
//
//    var productsList : HashMap<String, List<com.erp.erp.models.supplier_products.Data>>?
//    get(){
//        val json = sharedPreferences.getString(products, null)
//        val type = object : TypeToken<HashMap<String, List<com.erp.erp.models.supplier_products.Data>>>() {}.type
//        return Gson().fromJson(json, type)
//    }
//    set(value){
//        sharedPreferences.edit().putString(products, Gson().toJson(value)).apply()
//    }
//    fun saveProducts(value: HashMap<Int, List<com.erp.erp.models.supplier_products.Data>>): String {
//        val gson = Gson()
//        return gson.toJson(value)
//    }
//
//    @TypeConverter
//    fun toHashMap(value: String): HashMap<String, Any> {
//        val gson = Gson()
//        val type = object : TypeToken<HashMap<String, Any>>() {}.type
//        return gson.fromJson(value, type)
//    }


    fun put(
            key: String?,
            `object`: Any
    ) {

        val editor = sharedPreferences.edit()
        when (`object`) {
            is String -> {
                editor.putString(key, `object`)
            }
            is Int -> {
                editor.putInt(key, `object`)
            }
            is Boolean -> {
                editor.putBoolean(key, `object`)
            }
            is Float -> {
                editor.putFloat(key, `object`)
            }
            is Long -> {
                editor.putLong(key, `object`)
            }
            else -> {
                editor.putString(key, `object`.toString())
                editor.apply()
            }
        }
        editor.apply()
    }

    operator fun get(
            key: String?,
            defaultObject: Any?
    ): Any? {
        when (defaultObject) {
            is String -> {
                return sharedPreferences.getString(key, (defaultObject as? String) ?: "")
            }
            is Int -> {
                return sharedPreferences.getInt(key, (defaultObject as Int?) ?: 0)
            }
            is Boolean -> {
                return sharedPreferences.getBoolean(key, (defaultObject as Boolean?) ?: false)
            }
            is Float -> {
                return sharedPreferences.getFloat(key, (defaultObject as Float?) ?: 0f)
            }
            is Long -> {
                return sharedPreferences.getLong(key, (defaultObject as Long?) ?: 0L)
            }
            else -> return null
        }
    }

    fun removeKey(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }

}