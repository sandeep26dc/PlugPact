package com.sandeep26dc.plugpact.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class HudTheme(val themeName: String, val color: Color) {
    CYBER_BLUE("CYBER BLUE", Color(0xFF00E5FF)),
    EMERALD("EMERALD", Color(0xFF00E676)),
    PLASMA("PLASMA", Color(0xFFD500F9)),
    STEALTH("STEALTH", Color(0xFF9E9E9E))
}

@Composable
fun AestheticsScreen() {
    var selectedTheme by remember { mutableStateOf(HudTheme.CYBER_BLUE) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF090A0F))
            .padding(24.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "AESTHETICS",
            color = Color(0xFF00E5FF),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.5.sp
        )
        Text(
            text = "HUD Configuration",
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        val themes = HudTheme.values()
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            for (i in themes.indices step 2) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ThemeCard(
                        theme = themes[i],
                        isSelected = selectedTheme == themes[i],
                        onClick = { selectedTheme = themes[i] },
                        modifier = Modifier.weight(1f)
                    )
                    if (i + 1 < themes.size) {
                        ThemeCard(
                            theme = themes[i + 1],
                            isSelected = selectedTheme == themes[i + 1],
                            onClick = { selectedTheme = themes[i + 1] },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ThemeCard(
    theme: HudTheme,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val borderColor by animateColorAsState(
        targetValue = if (isSelected) theme.color else Color(0xFF1E2230),
        label = "BorderColor"
    )

    Box(
        modifier = modifier
            .height(140.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF12141D))
            .border(2.dp, borderColor, RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(2.dp, theme.color, CircleShape)
                    .background(theme.color.copy(alpha = if (isSelected) 0.3f else 0.05f))
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = theme.themeName,
                color = if (isSelected) Color.White else Color.Gray,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        }
    }
}
