package com.codesroots.androidprojects.wokhouse.presentation.mainfragment.homesubcategorypage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codesroots.androidprojects.wokhouse.R
import com.codesroots.androidprojects.wokhouse.databinding.SubcategoryRowBinding
import com.codesroots.androidprojects.wokhouse.model.ItemData
import com.codesroots.androidprojects.wokhouse.model.ItemsModel
import com.codesroots.androidprojects.wokhouse.model.Subcategory
import com.codesroots.androidprojects.wokhouse.presentation.ClickHandler


class subcategoryAdapter(var viewModel: CategoryViewModel,var listitem:ItemsModel, var activity: Context?):RecyclerView.Adapter<CustomViewHolderss>(){

private  val listItemData:ItemsModel = listitem

    override fun getItemCount(): Int {
        return listItemData.data!!.count()
    }

    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): CustomViewHolderss {

        val menubinding: SubcategoryRowBinding = DataBindingUtil.inflate (
            LayoutInflater.from(p0.context), R.layout.subcategory_row,p0,
            false)

        return CustomViewHolderss(
            menubinding
        )
    }



    override fun onBindViewHolder(p0: CustomViewHolderss, p1: Int) {

        p0.bind(listitem.data!!.get(p1),activity!!)


    }


}



class CustomViewHolderss(

     val binding: SubcategoryRowBinding

) : RecyclerView.ViewHolder(binding.root){

    fun bind(
         data: ItemData,context:Context) {
        binding.data = data
        binding.listener = ClickHandler()
        binding.context = context as SubcatPages

        }


    }

