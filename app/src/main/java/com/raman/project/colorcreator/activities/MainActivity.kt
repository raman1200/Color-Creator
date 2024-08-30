package com.raman.project.colorcreator.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import com.raman.project.colorcreator.screens.AddColorButton
import com.raman.project.colorcreator.screens.ColorScreen
import com.raman.project.colorcreator.screens.TopBar
import com.raman.project.colorcreator.ui.theme.ColorCreatorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ColorCreatorTheme {
                Box {
                    Column {
                        TopBar()
                        ColorScreen()
                    }
                    AddColorButton()
                }



            }
        }
    }
}
