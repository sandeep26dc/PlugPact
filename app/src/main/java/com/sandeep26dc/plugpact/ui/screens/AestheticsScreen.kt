@Composable
fun AestheticsScreen() {
    Column(modifier = Modifier.padding(24.dp)) {
        Text("AESTHETICS", color = Color(0xFF00F0FF), fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Text("HUD Configuration", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        
        Spacer(modifier = Modifier.height(30.dp))
        
        // Style Selection Grid
        Row(modifier = Modifier.fillMaxWidth()) {
            StyleTile("CYBER BLUE", Color(0xFF00F0FF), Modifier.weight(1f))
            StyleTile("EMERALD", Color(0xFF00FF9D), Modifier.weight(1f))
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            StyleTile("PLASMA", Color(0xFFFF00FF), Modifier.weight(1f))
            StyleTile("STEALTH", Color.White, Modifier.weight(1f))
        }
    }
}

@Composable
fun StyleTile(name: String, color: Color, modifier: Modifier) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .height(140.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Color(0xFF0F141C))
            .border(2.dp, color.copy(alpha = 0.3f), RoundedCornerShape(24.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(modifier = Modifier.size(40.dp).clip(CircleShape).background(color.copy(alpha = 0.2f)).border(1.dp, color, CircleShape))
            Spacer(modifier = Modifier.height(12.dp))
            Text(name, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
    }
}
