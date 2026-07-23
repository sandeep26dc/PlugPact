package com.sandeep26dc.plugpact.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GlassyPageIndicator(pagerState: PagerState, pageCount: Int) {
    Row(
        modifier = Modifier
            .height(30.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { index ->
            val isSelected = pagerState.currentPage == index
            
            // Width: Minimalist 6dp dot morphing into an elegant 22dp thin line
            val width by animateDpAsState(
                targetValue = if (isSelected) 22.dp else 6.dp,
                animationSpec = tween(durationMillis = 500),
                label = "Width"
            )

            // Muted Zen Colors (Low Alpha/Transparency for a "Ghostly" feel)
            val zenColor = when(index) {
                0 -> Color(0x6600F0FF) // Muted Ethereal Cyan
                1 -> Color(0x6600FF9D) // Muted Soft Emerald
                else -> Color(0x66B18AFF) // Muted Dust Purple
            }

            val color by animateColorAsState(
                targetValue = if (isSelected) zenColor else Color(0x11FFFFFF), 
                animationSpec = tween(durationMillis = 500),
                label = "Color"
            )

            // The Indicator Bead (3dp thin for extreme premium look)
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(width = width, height = 3.dp) 
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}
