package com.example.composiblevideoapp.uicomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.composiblevideoapp.utils.AppUtils
import com.example.composiblevideoapp.viewmodel.DataViewModel

@Composable
fun ContentList(viewData: DataViewModel) {
    LazyColumn(modifier = Modifier.padding(horizontal = if (AppUtils.isTablet(LocalConfiguration.current)) 100.dp else 10.dp)) {
        items(items = viewData.getData()) { item ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 5.dp, bottom = 5.dp)
                    .height(300.dp)
                    .background(color = Color.White)

            ) {
                VideoWebView(item.videoUrl)
            }
        }

    }
}