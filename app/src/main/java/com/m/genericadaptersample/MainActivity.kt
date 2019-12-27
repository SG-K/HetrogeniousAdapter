package com.m.genericadaptersample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.m.genericadaptersample.adapter.models.*
import com.m.genericadaptersample.adapter.viewholder.HorizontalImagesListViewBinder
import com.m.genericadaptersample.adapter.viewholder.VerticalImagesListViewBinder
import com.m.genericadaptersample.adapter.viewholder.VerticalImagesViewBinder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val arrayListImages : ArrayList<Any> = ArrayList()
    val arrayListImagesNatureVertical : ArrayList<VeriticalImageInnerModel> = ArrayList()
    val arrayListHorizontalImages : ArrayList<HorizontalImageModel> = ArrayList()
    val arrayListFoodVerticalImages : ArrayList<VeriticalImageModel> = ArrayList()
    val arrayListCoupleVerticalImages : ArrayList<HorizontalImageModel> = ArrayList()
    private var adapter: FeedAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addData()
    }

    //region Add images

    private fun addData() {

        for (i in 1..4) {
            val veriticalImageModel : VeriticalImageInnerModel =
                VeriticalImageInnerModel(getVerticalImages(i),Constants.VERTICAL)
            arrayListImagesNatureVertical.add(veriticalImageModel)
        }

        for (i in 1..6) {
            val horizontalImageModel : HorizontalImageModel =
                HorizontalImageModel(getHorizontalImages(i),Constants.HORIZONTAL)
            arrayListHorizontalImages.add(horizontalImageModel)
        }

        for (i in 1..6) {
            val horizontalImageModel : VeriticalImageModel =
                VeriticalImageModel(getFoodVerticalImages(i),Constants.VERTICAL)
            arrayListFoodVerticalImages.add(horizontalImageModel)
        }

        for (i in 1..6) {
            val horizontalImageModel : HorizontalImageModel =
                HorizontalImageModel(getCoupleImages(i),Constants.VERTICAL_LIST)
            arrayListCoupleVerticalImages.add(horizontalImageModel)
        }

        val verticalImageListModel : VerticalImageListModel = VerticalImageListModel(arrayListImagesNatureVertical,Constants.VERTICAL_LIST)
        verticalImageListModel.title = "Nature"


        // Adding two food vertical images
        for (i in 0..1) {
            arrayListImages?.add(arrayListFoodVerticalImages[i])
        }

        // Adding two couple horizontal images list
        val horizontalImageListModel : HorizontalImageListModel = HorizontalImageListModel(arrayListCoupleVerticalImages,Constants.HORIZONTAL_LIST)
        horizontalImageListModel.title = "Couples"
        arrayListImages?.add(horizontalImageListModel)

        // Adding two vertical food images
        for (i in 2..3) {
            arrayListImages?.add(arrayListFoodVerticalImages[i])
        }

        //Adding verital images list
        arrayListImages?.add(verticalImageListModel)

        // Adding two more vertical food images
        for (i in 4..5) {
            arrayListImages?.add(arrayListFoodVerticalImages[i])
        }

        showFeedItems(vertical_recyclerview,arrayListImages)

    }

    //endregion

    //region gettring images from reosurces
    private fun getVerticalImages(numer : Int) : Int{
        return when(numer){
            1 -> R.drawable.v1
            2 -> R.drawable.v2
            3 -> R.drawable.v3
            4 -> R.drawable.v4
            else -> R.drawable.v1
        }
    }

    private fun getHorizontalImages(numer : Int) : Int{
        return when(numer){
            1 -> R.drawable.h1
            2 -> R.drawable.h2
            3 -> R.drawable.h3
            4 -> R.drawable.h4
            5 -> R.drawable.h5
            6 -> R.drawable.h6
            else -> R.drawable.h1
        }
    }

    private fun getFoodVerticalImages(numer : Int) : Int{
        return when(numer){
            1 -> R.drawable.fv1
            2 -> R.drawable.fv2
            3 -> R.drawable.fv3
            4 -> R.drawable.fv4
            5 -> R.drawable.fv5
            6 -> R.drawable.fv6
            else -> R.drawable.fv1
        }
    }

    private fun getCoupleImages(numer : Int) : Int{
        return when(numer){
            1 -> R.drawable.c1
            2 -> R.drawable.c2
            3 -> R.drawable.c3
            4 -> R.drawable.c4
            5 -> R.drawable.c5
            6 -> R.drawable.c6
            else -> R.drawable.c1
        }
    }
    //endregion

    //region adapter

    fun verticalImageClick(data : VeriticalImageModel){
//        Toast.makeText(this@MainActivity,"",Toast.LENGTH_SHORT).show()
    }

    fun verticalImageInnerClick(data : VeriticalImageInnerModel){
//        Toast.makeText(this@MainActivity,"",Toast.LENGTH_SHORT).show()
    }

    fun horizontalImageClick(data : HorizontalImageModel){

    }

    private fun showFeedItems(recyclerView: RecyclerView, list: ArrayList<Any>?) {

        if (adapter == null) {
            val viewBinders = mutableMapOf<FeedItemClass, FeedItemBinder>()

            val verticalImagesViewBinder = VerticalImagesViewBinder { data : VeriticalImageModel ->
                verticalImageClick(data)}

            val verticalImagesListViewBinder = VerticalImagesListViewBinder { data : VeriticalImageInnerModel ->
                verticalImageInnerClick(data)}

            val horizontalImagesViewBinder = HorizontalImagesListViewBinder { data : HorizontalImageModel ->
                horizontalImageClick(data)}

            @Suppress("UNCHECKED_CAST")
            viewBinders.put(
                verticalImagesViewBinder.modelClass,
                verticalImagesViewBinder as FeedItemBinder)

            @Suppress("UNCHECKED_CAST")
            viewBinders.put(
                horizontalImagesViewBinder.modelClass,
                horizontalImagesViewBinder as FeedItemBinder)

            @Suppress("UNCHECKED_CAST")
            viewBinders.put(
                verticalImagesListViewBinder.modelClass,
                verticalImagesListViewBinder as FeedItemBinder)

            adapter = FeedAdapter(viewBinders)
        }



        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL,false)
        }
        if (recyclerView.adapter == null) {
            recyclerView.adapter = adapter
        }
        (recyclerView.adapter as FeedAdapter).submitList(list ?: emptyList())
    }

    //endregion

}
