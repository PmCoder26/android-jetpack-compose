package com.example.jetpack_compose_practice.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_practice.R
import kotlin.collections.listOf


/*
        1. Introduction:
            A carousel displays a scrollable list of items that adapt dynamically based on window size.
            Use carousels to showcase a collection of related content. Carousel items emphasize visuals,
            but can also contain brief text that adapts to the item size.

            There are four carousel layouts available to suit different use cases:
            Multi-browse: Includes differently sized items. Recommended for browsing many items
                          at once, like photos.
            Uncontained: Contains items that are a single size and flow past the edge of the screen.
                         Can be customized to show more text or other UI above or below each item.
            Hero: Highlights one large image to focus on and provides a peek of what's next with
                  a small item. Recommended for spotlighting content that you want to emphasize,
                  like movie or show thumbnails.
            Full-screen: Shows one edge-to-edge large item at a time and scrolls vertically.
                         Recommended for content that is taller than it is wide.

        2. Parameters:
            state: A CarouselState instance that manages the current item index and scroll position.
                   Create this state using rememberCarouselState { itemCount }, where itemCount is
                   the total number of items in the carousel.
            itemSpacing: Defines the amount of empty space between adjacent items in the carousel.
            contentPadding: Applies padding around the content area of the carousel. Use this to
                            add space before the first item or after the last item, or to provide
                            margins for the items within the scrollable region.
        3. Note - The HorizontalMultiBrowseCarousel() and HorizontalUncontainedCarousel() has same
                  parameters, just the concept is little different.
 */


data class CarouselItem(
    val id: Int,
    val imgResId: Int
)

@Preview(showBackground = true)
@Composable
fun CarouselsScreenPreview() {
    CarouselsScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarouselsScreen() {

    val items = remember {
        listOf(
            CarouselItem(0, R.drawable.images_1),
            CarouselItem(0, R.drawable.images_2),
            CarouselItem(0, R.drawable.images_3),
            CarouselItem(0, R.drawable.images_4),
            CarouselItem(0, R.drawable.images_5),
            CarouselItem(0, R.drawable.images_6),
        )
    }

    val carouselState = rememberCarouselState() { items.size }

    HorizontalMultiBrowseCarousel(
        state = carouselState,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        preferredItemWidth = 186.dp,
        itemSpacing = 7.dp,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) { itemIdx ->

        val item = items[itemIdx]

        Image(
            painter = painterResource(item.imgResId),
            contentDescription = "dessert",
            modifier = Modifier
                .size(200.dp)
                // 	Show the image clipped to extraLarge rounded corners.
                .maskClip(MaterialTheme.shapes.extraLarge),
            contentScale = ContentScale.Crop
        )

    }

}