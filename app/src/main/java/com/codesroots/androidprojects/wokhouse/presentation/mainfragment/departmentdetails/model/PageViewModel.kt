package com.codesroots.androidprojects.wokhouse.presentation.mainfragment.departmentdetails.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.codesroots.androidprojects.wokhouse.datalayer.DataRepo
import com.codesroots.androidprojects.wokhouse.model.CategoryModel
import com.codesroots.androidprojects.wokhouse.model.SliderData


class PageViewModel : ViewModel() {

    var DateRepoCompnay: DataRepo = DataRepo()


    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = Transformations.map(_index) {
        "Hello world from section: $it"
    }

    var CategoryResponseLD : MutableLiveData<CategoryModel>? = null
    var SliderResponseLD : MutableLiveData<SliderData>? = null
    var CompanyResponseLD : MutableLiveData<Int>? = null

    init {
        CompanyResponseLD = MutableLiveData()
        CategoryResponseLD = MutableLiveData()
        SliderResponseLD  = MutableLiveData()
    }

    fun setIndex(index: Int) {
        CompanyResponseLD!!.postValue(index)

    }

    fun getcompanyData(){
        DateRepoCompnay.GetCategoryData(CategoryResponseLD)
    }
    fun getsliderData(){
        DateRepoCompnay.GetSliderData(SliderResponseLD)
    }

}
