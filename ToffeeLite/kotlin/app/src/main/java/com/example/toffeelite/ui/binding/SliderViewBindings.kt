package com.example.toffeelite.ui.binding

import androidx.databinding.BindingAdapter
import com.example.toffeelite.data.model.omdb.Search
import com.example.toffeelite.ui.adapter.SliderAdapter
import com.smarteist.autoimageslider.SliderView

@BindingAdapter("bind_slider_images", "bind_slider_view")
fun SliderView.bindSliderImages(items: List<Search>?, sliderView: SliderView){
    var imageUrlList: ArrayList<String> = ArrayList(2)

    if (items == null) return

    items.forEach{it ->
        imageUrlList.add(it.Poster)
    }


    var fiveImageUrls = randomFiveImageUrl(imageUrlList)
    var sliderAdapter = SliderAdapter(fiveImageUrls)
    sliderView.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR

    sliderView.setSliderAdapter(sliderAdapter)
    sliderView.scrollTimeInSec = 3
    sliderView.isAutoCycle = true
    sliderView.startAutoCycle()


}

fun randomFiveImageUrl(imageUrlList: ArrayList<String>) : ArrayList<String> {
    var randomImageUrlList: ArrayList<String> = ArrayList(2)
    return try{
        val random = (0..10).random()
        for (i in random..random + 4){
            randomImageUrlList.add(imageUrlList.get(i))
        }
        randomImageUrlList
    }catch (e: Exception){
        for (i in 1..5){
            randomImageUrlList.add(imageUrlList.get(i))
        }
        randomImageUrlList
    }
}