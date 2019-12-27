package com.m.genericadaptersample.adapter.models

import com.m.genericadaptersample.Constants

class VerticalImageListModel(val Images : ArrayList<VeriticalImageInnerModel>,
                             val type : Int = Constants.VERTICAL_LIST,
                             val id : Int = Constants.VERTICAL_LIST,
                             var title : String = "") {
}