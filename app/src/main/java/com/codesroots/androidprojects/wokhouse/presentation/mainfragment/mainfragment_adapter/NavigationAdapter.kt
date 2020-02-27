package com.codesroots.androidprojects.wokhouse.presentation.mainfragment.mainfragment_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codesroots.androidprojects.wokhouse.databinding.BottomNavigationRowBinding
import com.codesroots.androidprojects.wokhouse.presentation.mainfragment.departmentdetails.model.PageViewModel
import com.codesroots.androidprojects.wokhouse.R
import com.codesroots.androidprojects.wokhouse.model.BottomNavData


class NavigationAdapter(val  viewModel: PageViewModel,val bottomNavTitle: ArrayList<BottomNavData>): RecyclerView.Adapter<NavigationAdapter.CustomViewHolder>() {




    override fun getItemCount(): Int {
        return  bottomNavTitle.size
    }



    override fun onBindViewHolder(p0: CustomViewHolder, p1: Int) {
        p0.bind(viewModel,p1,bottomNavTitle.get(p1))

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {

        val binding: BottomNavigationRowBinding = DataBindingUtil.inflate (LayoutInflater.from(p0.context),
        R.layout.bottom_navigation_row,p0,false)

        return CustomViewHolder(binding)
    }


    class CustomViewHolder(
        private val binding: BottomNavigationRowBinding
    ) : RecyclerView.ViewHolder(binding.root){

        fun bind(viewModel:PageViewModel, index:Int,data: BottomNavData) {

            binding.index = index
            binding.viewmodel = viewModel
            binding.data = data

        }
    }




}