package com.codesroots.androidprojects.wokhouse.presentation.mainfragment.homesubcategorypage



import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.codesroots.androidprojects.wokhouse.R
import com.codesroots.androidprojects.wokhouse.model.ItemsModel
import kotlinx.android.synthetic.main.subcategory_items.*
import androidx.lifecycle.ViewModelProviders


class SubcatPages: AppCompatActivity() {
     lateinit var viewModel : CategoryViewModel
    lateinit var SubAdapter: subcategoryAdapter
    var  data : ItemsModel? = null
    var name : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.subcategory_items)

        var extras = intent.extras
        val value = extras!!.getInt("id")

        name = extras!!.getString("name")
        setTitle(name)

      //  getItems(value)
        subCategoryRecycle.layoutManager = GridLayoutManager(this,2)
        viewModel = ViewModelProviders.of(this).get(CategoryViewModel:: class.java)
        viewModel.getCatData(value)

        viewModel.CategoriesResponseLD?.observe(this, androidx.lifecycle.Observer {
            SubAdapter = subcategoryAdapter(viewModel,it,this)

            subCategoryRecycle.layoutManager = GridLayoutManager(this,2)
            subCategoryRecycle?.adapter = SubAdapter


        })

    }


}
