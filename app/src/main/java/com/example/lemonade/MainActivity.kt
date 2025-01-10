package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun LemonadeApp(){
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "make lemonade 🍋",
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily.Monospace,
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color(0xFFEDD0CE)
                )
            )
        }

    ){innerPadding ->
        LemonTextAndImage(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .wrapContentSize(Alignment.Center)
        )
    }
}

@Composable
fun LemonTextAndImage(modifier: Modifier = Modifier) {

    var currentIndex by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    val (stepText, imageResource, contentDescriptionText) = when (currentIndex) {
        1 -> Triple(R.string.lemon_select, R.drawable.lemon_tree, R.string.lemon_tree_content_description)
        2 -> Triple(R.string.lemon_squeeze, R.drawable.lemon_squeeze, R.string.lemon_content_description)
        3 -> Triple(R.string.lemon_drink, R.drawable.lemon_drink, R.string.lemonade_content_description)
        4 -> Triple(R.string.lemon_empty_glass, R.drawable.lemon_restart, R.string.empty_glass_content_description)
        else -> Triple(R.string.lemon_select, R.drawable.lemon_tree, R.string.lemon_tree_content_description)
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.app_background),
            contentDescription = null, 
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(150.dp) // Kare boyutu
                    .background(
                        color = Color(0xFFF4E2E1),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .clickable {
                        when (currentIndex) {
                            1 -> {
                                squeezeCount = (2..4).random()
                                currentIndex++
                            }
                            2 -> {
                                if (squeezeCount == 0) currentIndex++
                                else squeezeCount--
                            }
                            3 -> currentIndex++
                            4 -> currentIndex = 1
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(imageResource),
                    modifier = Modifier.size(100.dp),
                    contentDescription = stringResource(contentDescriptionText)
                )
            }
            Spacer(modifier = Modifier.padding(16.dp))
            Text(
                text = stringResource(stepText),
                color = Color.Black,
                fontSize = 15.sp,
                fontFamily = FontFamily.Monospace,
            )
        }
    }
}



