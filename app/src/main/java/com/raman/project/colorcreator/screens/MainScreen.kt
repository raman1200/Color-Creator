package com.raman.project.colorcreator.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.raman.project.colorcreator.R
import com.raman.project.colorcreator.viewmodels.ColorViewModel

@Preview(showBackground = true)
@Composable
fun PreviewTopBar() {
    TopBar()
//    AddColorButton()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(
                text = "Color App",
                fontSize = 24.sp,
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF5659A4)),
        actions = {
            ButtonWithTextAndImage()
        },
        modifier = Modifier.fillMaxWidth()
    )

}

@Composable
fun ButtonWithTextAndImage() {
    var isLoading by remember { mutableStateOf(false) }
    val colorViewModel : ColorViewModel = viewModel()
    val unsyncedValue = colorViewModel.unsyncedValue.collectAsState()
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_animation))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isLoading // Control animation playback with isLoading
    )

    Box(
        modifier = Modifier
            .padding(10.dp)
            .background(Color(0xFFB6B9FF), shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .width(70.dp)
                .clickable {
                    colorViewModel.syncData()
                    isLoading = true
                }
                .padding(horizontal = 4.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = unsyncedValue.value.toString(),
                color = Color.White,
                fontSize = 20.sp
            )

            if(isLoading && unsyncedValue.value > 0){
                LottieAnimation(
                    composition,
                    progress,
                    modifier = Modifier.size(30.dp) // Adjust size as needed
                )
            }
            else{
                isLoading = false
                Icon(
                painter = painterResource(id = R.drawable.icon),
                contentDescription = null,
                tint = Color(0xFF5659A4),
                modifier = Modifier.padding(start = 4.dp)
            )
            }

        }
    }
}

@Composable
fun AddColorButton() {
    val colorViewModel : ColorViewModel = viewModel()

    Box(

        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .padding(20.dp),
        contentAlignment = Alignment.BottomEnd

    ){
        Row(
            modifier = Modifier
                .background(Color(0xFFB6B9FF), shape = CircleShape)
                .width(130.dp)
                .clickable { colorViewModel.insertColor() }
                .padding(top = 5.dp, bottom = 5.dp, start= 8.dp, end = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,


            ) {
            Text(
                text = "Add Color",
                color = Color(0xFF5659A4),

                )
            Icon(
                painter = painterResource(id = R.drawable.plus_round_icon),
                tint = Color(0xFF5659A4),
                contentDescription = null
            )
        }

    }
}