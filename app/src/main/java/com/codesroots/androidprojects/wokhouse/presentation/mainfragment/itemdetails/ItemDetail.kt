package com.codesroots.androidprojects.wokhouse.presentation.mainfragment.ItemDetail

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.codesroots.androidprojects.wokhouse.R
import com.codesroots.androidprojects.wokhouse.databinding.ItemDetailBinding
import com.codesroots.androidprojects.wokhouse.model.ItemData
import com.codesroots.androidprojects.wokhouse.presentation.mainfragment.itemdetails.ItemSliderAdapter
import com.codesroots.androidprojects.wokhouse.presentation.your_order.Yourorderlist
import com.viewpagerindicator.CirclePageIndicator
import kotlinx.android.synthetic.main.item_detail.*
import kotlinx.android.synthetic.main.your_order.*
import java.util.*

class ItemDetail: AppCompatActivity() {

    var pagers: ViewPager? = null
    var indicators: CirclePageIndicator? = null
    var data :ItemData ? = null

     var minteger = 0

    var NUM_PAGES = 0
    var currentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ItemDetailBinding>(this,R.layout.item_detail)

        val plus = findViewById<Button>(R.id.increase)
        val minus = findViewById<Button>(R.id.decrease)


        var extras = intent.extras
       // val value = extras!!.getInt("id")
        val itemdata = extras!!.getParcelable<ItemData>("itemdata")

        binding.data = itemdata

        val data = itemdata
        pagers = pager
        indicators = indicator


        pagers!!.adapter = ItemSliderAdapter(this , data!!.item_photo!!)
        indicators!!.setViewPager(pager)
        init(data!!.item_photo!!.size)
         Timer()

        basketButton.setOnClickListener{
            val intent = Intent(this, Yourorderlist::class.java)
            (this).startActivity(intent)
            finish()
        }

        plus.setOnClickListener {
            increaseInteger(plus)
        }

        minus.setOnClickListener {
            decreaseInteger(minus)
        }
        return
    }

    fun increaseInteger(view: View) {
        minteger += 1
        display(minteger)

    }

    fun decreaseInteger(view: View) {
        minteger -= 1
        display(minteger)
    }

    private fun display(number: Int) {
        val displayInteger = findViewById<View>(R.id.integer_number) as TextView
            displayInteger.text = "" + number
    }




    private fun init(size: Int) {
        val density = getResources().getDisplayMetrics().density
        indicators!!.setRadius(4 * density)

        NUM_PAGES = size
        val handler = Handler()
        val Update:Runnable =Runnable {
            if (currentPage == NUM_PAGES) {
                currentPage = 0
            }
            pager?.setCurrentItem(currentPage++, true)
        }

        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 4000, 10000)
        indicators!!.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                currentPage = position
            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {}
            override fun onPageScrollStateChanged(pos: Int) {}
        })
    }

    }



