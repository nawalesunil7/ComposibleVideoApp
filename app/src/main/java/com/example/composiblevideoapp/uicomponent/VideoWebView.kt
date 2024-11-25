package com.example.composiblevideoapp.uicomponent

import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.composiblevideoapp.utils.FullScreenChromeClient

@Composable
fun VideoWebView(
    videoUrl: String, modifier: Modifier = Modifier
        .fillMaxWidth()
        .clip(shape = RectangleShape)
) {
    val displayLoader = remember {
        mutableStateOf(true)
    }

    val webViewState = remember {
        mutableStateOf<WebView?>(null)
    }

    val context = LocalContext.current

    val displayMetrics = context.resources.displayMetrics
    val activity = LocalContext.current as ComponentActivity
    //Width And Height Of Screen
    val width = displayMetrics.widthPixels
    val chromeClient = remember { FullScreenChromeClient(activity) }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = { context ->
                webViewState.value?.let {
                    return@AndroidView webViewState.value!!
                }

                WebView(context).apply {
                    webViewState.value = this
                    webChromeClient = chromeClient
                    webViewClient = WebViewClient()
                    settings.javaScriptEnabled = true
                    settings.domStorageEnabled = true
                    settings.loadWithOverviewMode = true
                    settings.useWideViewPort = true
                    settings.mediaPlaybackRequiresUserGesture = true
                    settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }
            },
            modifier = modifier,
            update = { webView ->
                webViewState.value?.webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        displayLoader.value = false
                        super.onPageFinished(view, url)
                    }

                    override fun shouldOverrideUrlLoading(
                        view: WebView?,
                        request: WebResourceRequest?
                    ): Boolean {
                        return super.shouldOverrideUrlLoading(view, request)
                    }
                }
                if (videoUrl.contains("www.youtube.com")) {
                    //Fixed video thumbnail aspect ratio same as iOS, height = width * 9 / 16, width is full screen width
                    val video =
                        "<iframe width=\"$width\" height=\"${width * 9 / 16}\" src=\"$videoUrl\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>"
                    webView.loadData(video, "text/html", "utf-8")
                } else {
                    webView.loadUrl(videoUrl)
                }
            })


    }
}