package com.codesroots.androidprojects.wokhouse.presentation.mainfragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.ViewPager
import com.codesroots.androidprojects.wokhouse.R
import com.codesroots.androidprojects.wokhouse.model.CategoryModel
import com.codesroots.androidprojects.wokhouse.model.SliderData
import com.codesroots.androidprojects.wokhouse.presentation.mainfragment.mainfragment_adapter.SliderAdapter
import com.codesroots.androidprojects.wokhouse.presentation.mainfragment.mainfragment_adapter.MenuAdapter
import com.codesroots.androidprojects.wokhouse.presentation.mainfragment.departmentdetails.model.PageViewModel


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.viewpagerindicator.CirclePageIndicator
import kotlinx.android.synthetic.main.fragment_common.*
import kotlinx.android.synthetic.main.fragment_common.view.*
import okhttp3.*
import java.io.IOException
import java.util.*

class MainFragment : Fragment(){

    private lateinit var viewModel : PageViewModel
    lateinit var MenuAdapter: MenuAdapter

    var  data : CategoryModel? = null
    var pagers: ViewPager? = null
    var indicator: CirclePageIndicator? = null

    var NUM_PAGES = 0
    var currentPage = 0

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?  {

        var view = inflater.inflate(R.layout.fragment_common, container, false)

        pagers = view!!.pager

        indicator = view.indicator




        view.recycelView_main.layoutManager = GridLayoutManager(activity,2)
        viewModel = ViewModelProviders.of(this).get(PageViewModel:: class.java)
        viewModel.getcompanyData()
        viewModel.getsliderData()

        pagers = view!!.pager
        viewModel.CategoryResponseLD?.observe(this, androidx.lifecycle.Observer {
            MenuAdapter =
                MenuAdapter(
                    viewModel,
                    context as FragmentActivity,
                    it
                )
            view.recycelView_main.layoutManager = GridLayoutManager(activity,2)
            view.recycelView_main?.adapter = MenuAdapter
        })

        viewModel.SliderResponseLD?.observe(this, androidx.lifecycle.Observer {
            pagers!!.adapter = it?.let { it1 -> SliderAdapter(activity!!, it1.data!!) }
            indicator!!.setViewPager(view.pager)
            it.data!!.let { it1 -> init(it1.size) }

        })
        Timer()
        return view

    }

    private fun init(size: Int) {
        val density = getResources().getDisplayMetrics().density
        indicator!!.setRadius(4 * density)

        NUM_PAGES = size
        val handler = Handler()
        val Update:Runnable =Runnable {
            if (currentPage == NUM_PAGES) {
                currentPage = 0
            }
            pagers?.setCurrentItem(currentPage++, true)
        }

        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 4000, 10000)
        indicator!!.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                currentPage = position
            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {}
            override fun onPageScrollStateChanged(pos: Int) {}
        })
    }








}

