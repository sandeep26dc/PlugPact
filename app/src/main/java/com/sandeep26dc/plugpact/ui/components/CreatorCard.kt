package com.sandeep26dc.plugpact.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CreatorCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFF131B26))
            .padding(16.dp)
    ) {
        Column {
            Text("ARCHITECTED BY", fontSize = 10.sp, color = Color(0xFF00F0FF))
            Text("SANDEEP SOM", fontSize = 18.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
            Text("Lead Android APK Expert", fontSize = 12.sp, color = Color.Gray)
        }
    }
}
