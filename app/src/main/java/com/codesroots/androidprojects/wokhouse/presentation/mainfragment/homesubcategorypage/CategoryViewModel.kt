package com.codesroots.androidprojects.wokhouse.presentation.mainfragment.homesubcategorypage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codesroots.androidprojects.wokhouse.datalayer.DataRepo
import com.codesroots.androidprojects.wokhouse.model.ItemsModel
import com.codesroots.androidprojects.wokhouse.model.SliderData
import java.util.*

class CategoryViewModel : ViewModel() {
    var DateRepoCompnay: DataRepo = DataRepo()

    var CategoriesResponseLD : MutableLiveData<ItemsModel>? = null

    init {
        CategoriesResponseLD = MutableLiveData()
    }

    fun getCatData(id:Int){
        DateRepoCompnay.GetSubCategoryData(id,CategoriesResponseLD)
    }
}