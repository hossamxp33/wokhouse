package com.codesroots.androidprojects.wokhouse.presentation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.codesroots.androidprojects.wokhouse.presentation.mainfragment.departmentdetails.model.PageViewModel
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Context
import android.content.Intent
import android.text.Html
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.codesroots.androidprojects.wokhouse.R
import com.codesroots.androidprojects.wokhouse.model.BottomNavData
import com.codesroots.androidprojects.wokhouse.model.CategoryModel
import com.codesroots.androidprojects.wokhouse.model.ItemData
import com.codesroots.androidprojects.wokhouse.presentation.mainfragment.mainfragment_adapter.NavigationAdapter
import com.codesroots.androidprojects.wokhouse.presentation.mainfragment.departmentdetails.DepartmentDetailActivity
import com.codesroots.androidprojects.wokhouse.presentation.mainfragment.ItemDetail.ItemDetail
import com.codesroots.androidprojects.wokhouse.presentation.mainfragment.MainFragment
import com.codesroots.androidprojects.wokhouse.presentation.mainfragment.departmentdetails.adapter.ViewPagerAdapter
import com.codesroots.androidprojects.wokhouse.presentation.mainfragment.homesubcategorypage.SubcatPages
import com.codesroots.androidprojects.wokhouse.presentation.AboutUs.AboutUs
import com.codesroots.androidprojects.wokhouse.presentation.Rate.RateFragment


@BindingAdapter("app:imageResource")
fun setImageResource(imageView: ImageView, resource: String?) {
    Glide.with(imageView.context).load(resource).into(imageView)
}
@BindingAdapter("app:TextHTML")
fun setTextHTML(textview: TextView, resource: String?) {
    textview.text =  Html.fromHtml(resource).toString()
}






class MainActivity : AppCompatActivity() {

    val BottomNavTitle= ArrayList<BottomNavData>()

    private lateinit var pageViewModel: PageViewModel
    val adapter = ViewPagerAdapter(supportFragmentManager)


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java)
        NavigationRecycle.layoutManager =
            LinearLayoutManager(this, OrientationHelper.HORIZONTAL, true)
        NavigationRecycle.adapter = NavigationAdapter(pageViewModel,BottomNavTitle)


        adapter.addFragment(MainFragment(), "main")
        adapter.addFragment(RateFragment(), "favorites")
        adapter.addFragment(AboutUs(), "menu")

        BottomNavTitle.add(BottomNavData("الرئيسية", R.drawable.paifang))
        BottomNavTitle.add(BottomNavData("تقييم زيارتك", R.drawable.star))
        BottomNavTitle.add(BottomNavData("عن المطعم", R.drawable.belief))

        mainviewpager.adapter = adapter
        pageViewModel.CompanyResponseLD?.observe(this, Observer {


            adapter.notifyDataSetChanged()
            mainviewpager.currentItem = it



        })
        // Create messages
    }

}

class ClickHandler {

    fun SwitchToMenu(context: Context,int: Int,data:CategoryModel){

        val homeIntent = Intent(context, DepartmentDetailActivity()::class.java)
        homeIntent.putExtra("position",int)
        homeIntent.putExtra("myobj", data)
        (context as MainActivity).startActivity(homeIntent)

    }


    fun SwitchToItems(context: Context,id :Int, name: String ){

        val homeIntent = Intent(context, SubcatPages()::class.java)

        homeIntent.putExtra("id",id)
        homeIntent.putExtra("name",name)


        (context as DepartmentDetailActivity).startActivity(homeIntent)

    }

    fun SwitchToItemDetails(context: Context,data: ItemData){

        val homeIntent = Intent(context, ItemDetail()::class.java)

        homeIntent.putExtra("itemdata",data)

        (context as SubcatPages).startActivity(homeIntent)


    }

fun setColor(){

}

}




