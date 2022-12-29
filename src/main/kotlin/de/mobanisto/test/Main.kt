package de.mobanisto.test

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.singleWindowApplication
import com.halilibo.richtext.markdown.Markdown
import com.halilibo.richtext.ui.RichText

fun main() {
    Main().run()
}

class Main {

    fun run() {
        val text = Thread.currentThread().contextClassLoader.getResourceAsStream("start.md")
            ?.bufferedReader().use { it?.readText() } ?: "Error while loading `start.md`"

        singleWindowApplication(title = "Test Markdown")
        {
            val (value, setValue) = remember { mutableStateOf(text) }
            Row {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(16.dp)
                        .fillMaxWidth(0.5f)
                ) {
                    Text("Markdown Source", fontWeight = FontWeight.Bold)
                    TextField(value, setValue, modifier = Modifier.fillMaxSize())
                }
                Divider(color = Color.Black, modifier = Modifier.fillMaxHeight().width(1.dp))
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(16.dp)
                        .fillMaxWidth(1f)
                ) {
                    Text("Render", fontWeight = FontWeight.Bold)
                    RichText(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
                        Markdown(value)
                    }
                }
            }
        }
    }
}
