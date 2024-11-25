package com.example.composiblevideoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.composiblevideoapp.models.VideoData
import com.example.composiblevideoapp.utils.AppConstants

class DataViewModel() : ViewModel() {
    private val _dateItems = MutableLiveData<List<VideoData>>(emptyList())
    private val dateItems: LiveData<List<VideoData>> = _dateItems

    init {
        val items = ArrayList<VideoData>()
        items.add(VideoData(AppConstants.videoUrl_1, "TP"))
        items.add(VideoData(AppConstants.videosUrl_2, "TTP"))
        items.add(VideoData(AppConstants.videosUrl_3, "TP"))
        items.add(VideoData(AppConstants.videoUrl_1, "TP"))
        items.add(VideoData(AppConstants.videosUrl_2, "TTP"))
        items.add(VideoData(AppConstants.videosUrl_3, "TP"))
        items.add(VideoData(AppConstants.videoUrl_1, "TP"))
        items.add(VideoData(AppConstants.videosUrl_2, "TTP"))
        items.add(VideoData(AppConstants.videosUrl_3, "TP"))
        items.add(VideoData(AppConstants.videoUrl_1, "TP"))
        items.add(VideoData(AppConstants.videosUrl_2, "TTP"))
        items.add(VideoData(AppConstants.videosUrl_3, "TP"))
        items.add(VideoData(AppConstants.videoUrl_1, "TP"))
        items.add(VideoData(AppConstants.videosUrl_2, "TTP"))
        items.add(VideoData(AppConstants.videosUrl_3, "TP"))
        items.add(VideoData(AppConstants.videoUrl_1, "TP"))
        items.add(VideoData(AppConstants.videosUrl_2, "TTP"))
        items.add(VideoData(AppConstants.videosUrl_3, "TP"))


        _dateItems.value = items
    }

    fun getData(): List<VideoData> {
        return if (dateItems.value?.isEmpty() == true) emptyList() else dateItems.value!!
    }
}