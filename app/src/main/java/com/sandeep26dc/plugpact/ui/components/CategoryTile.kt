package com.sandeep26dc.plugpact.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategoryTile(title: String, desc: String, icon: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF0F141C))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(40.dp).clip(RoundedCornerShape(12.dp)).background(Color(0x1A00F0FF)), contentAlignment = Alignment.Center) {
            Text(icon, fontSize = 18.sp)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(title, color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            Text(desc, color = Color.Gray, fontSize = 11.sp)
        }
    }
}

@Composable
fun AboutSandeepSomDialog(onDismiss: () -> Unit) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Color(0xFF0F141C),
        title = { Text("SYSTEM ARCHITECT", color = Color(0xFF00F0FF), fontSize = 14.sp) },
        text = {
            Column {
                Text("SANDEEP SOM", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text("Lead Android Performance Engineer", color = Color.Gray, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(10.dp))
                Text("PlugPact v1.0.0 Stable Build", color = Color.DarkGray, fontSize = 10.sp)
            }
        },
        confirmButton = { TextButton(onClick = onDismiss) { Text("CLOSE") } }
    )
}
