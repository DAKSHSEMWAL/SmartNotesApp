package com.dakshsemwal.smartnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dakshsemwal.smartnotes.ui.SummariseNoteScreen
import com.dakshsemwal.smartnotes.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                SummariseNoteScreen()
            }
        }
    }
}