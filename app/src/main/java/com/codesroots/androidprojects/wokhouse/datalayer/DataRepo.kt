package com.codesroots.androidprojects.wokhouse.datalayer

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.codesroots.androidprojects.wokhouse.model.CategoryModel
import com.codesroots.androidprojects.wokhouse.model.ItemsModel
import com.codesroots.androidprojects.wokhouse.model.SliderData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DataRepo {


    @SuppressLint("CheckResult")
    fun GetSubCategoryData(id:Int,livedata: MutableLiveData<ItemsModel>?) {

        APIServices.create()
            .GetItemByTybe(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { data -> data }
            .subscribe(
                { success ->
                    livedata?.postValue(success)
                },
                { error ->

                }
            )
    }

    @SuppressLint("CheckResult")
    fun GetSliderData(livedata: MutableLiveData<SliderData>?) {

        APIServices.create()
            .GetSlider()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { data -> data }
            .subscribe(
                { success ->
                    livedata?.postValue(success)
                },
                { error ->

                }
            )
    }

    @SuppressLint("CheckResult")
    fun GetCategoryData(livedata: MutableLiveData<CategoryModel>?) {

        APIServices.create()
            .GetCategoryData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { data -> data }
            .subscribe(
                { success ->
                    livedata?.postValue(success)
                },
                { error ->

                }
            )
    }


}


