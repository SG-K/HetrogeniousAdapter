package com.m.genericadaptersample.adapter.models

import com.m.genericadaptersample.Constants

data class HorizontalImageListModel(val Images : ArrayList<HorizontalImageModel>,
                                    val type : Int = Constants.HORIZONTAL_LIST,
                                    val id : Int = Constants.HORIZONTAL_LIST,
                                    var title : String = "") {
}