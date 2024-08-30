package com.raman.project.colorcreator.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raman.project.colorcreator.models.ColorData
import com.raman.project.colorcreator.viewmodels.ColorViewModel
import java.text.SimpleDateFormat

@Preview(showBackground = true)
@Composable
fun ColorScreenPreview(){
    ColorScreen()
}

@Composable
fun ColorScreen() {
    val colorViewModel: ColorViewModel = viewModel()
    val list = colorViewModel.colorData.collectAsState(emptyList())

    Log.d("TAG", list.value.toString())
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        items(list.value){
            ColorItem(color = it)
        }
    }
}



@Composable
fun ColorItem(color: ColorData) {
    Box(
        modifier = Modifier
            .padding(10.dp, 5.dp)
            .background(color = colorFromHex(color.value), shape = RoundedCornerShape(8.dp))
            .padding(6.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = color.value,
                fontSize = 18.sp,
                color = Color.White,
            )


            HorizontalDivider(
                color = Color.White,
                thickness = 1.dp,
                modifier = Modifier.width((color.value.length * 10).dp + 20.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))


            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End,
            ) {
                Text(
                    text = "Created at",
                    fontSize = 14.sp,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = convertLongToTime(color.time),
                    fontSize = 14.sp,
                    color = Color.White,
                )
            }
        }
    }
}


fun colorFromHex(hex: String): Color {
    return Color(android.graphics.Color.parseColor(hex))
}

fun convertLongToTime(time: Long): String {
    val format = SimpleDateFormat("dd/MM/yyyy")
    return format.format(time)
}




