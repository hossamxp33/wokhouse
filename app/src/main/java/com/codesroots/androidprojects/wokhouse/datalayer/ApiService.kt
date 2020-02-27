package com.codesroots.androidprojects.wokhouse.datalayer

import com.codesroots.androidprojects.wokhouse.model.CategoryModel
import com.codesroots.androidprojects.wokhouse.model.ItemsModel
import com.codesroots.androidprojects.wokhouse.model.SliderData
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface APIServices {

    @GET("api/Categories/getitemcategories.json?fbclid=IwAR0TlPjFkGzVyYW9JBnOtGwa2-W5hQbI8xLisw0pDiFWteiMmwWee9clEWQ")/*{company_id}*/
    fun GetCategoryData():
            Observable<CategoryModel>

    @GET("api/sliders.json")/*{company_id}*/
    fun GetSlider():
            Observable<SliderData>

    @GET("api/items/getitemsbytype/{id}/1.json")/*{company_id}*/
    fun GetItemByTybe(@Path("id") id: Int):
            Observable<ItemsModel>


    companion object {
        fun create(): APIServices {

            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor( HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("http://wokhouse.codesroots.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory( RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(okHttpClient)
                .build();


            return retrofit.create(APIServices::class.java)
        }

    }}