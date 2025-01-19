@file:OptIn(ExperimentalLayoutApi::class)

package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                CalculatorUI()
            }
        }
    }
}

@Composable
fun CalculatorUI() {
    var displayValue by remember { mutableStateOf("0") }
    val calculator = Calculator()
    var lastValue: Double? = null
    var currentOperator: String? = null
    fun appendToDisplay(value: String, current: String): String {
        return if (current == "0") value else current + value
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.DarkGray),
    ) {
        // Display
        Text(
            text = displayValue, // You'll want to replace this with a dynamic value
            style = TextStyle(
                color = Color.White,
                fontSize = 74.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        )
       //C Button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.End){
            Button(
                onClick = { displayValue = "0" },
                modifier = Modifier
                    .padding(4.dp)
                    .size(80.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.Black
                )

            ) {
                Text(
                    text = "C",
                    style = TextStyle(
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

        }
        // Buttons Layout using FlowRow
        FlowRow(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalArrangement =  Arrangement.spacedBy(8.dp),
            4,
        ) {
            val buttons = listOf(
                "7", "8", "9", "+",
                "4", "5", "6", "-",
                "1", "2", "3", "*",
                "0", ".", "=", "/", "C"
            )

            buttons.forEach { label ->
                Button(
                    onClick = {
                        when (label) {
                            "C" -> {
                                displayValue = "0"
                                lastValue = null
                                currentOperator = null
                            }
                            "=" -> {
                                val secondValue = displayValue.toDouble()
                                displayValue = when (currentOperator) {
                                    "+" -> calculator.add(lastValue!!, secondValue).toString()
                                    "-" -> calculator.subtract(lastValue!!, secondValue).toString()
                                    "*" -> calculator.multiply(lastValue!!, secondValue).toString()
                                    "/" -> calculator.divide(lastValue!!, secondValue).toString()
                                    else -> "Error"
                                }
                                lastValue = null
                                currentOperator = null
                            }
                            "+", "-", "*", "/" -> {
                                lastValue = displayValue.toDouble()
                                currentOperator = label
                                displayValue = "0"
                            }
                            else -> {
                                displayValue = if (displayValue == "0") label else displayValue + label
                            }
                        }
                    },
                    modifier = Modifier
                        .size(88.dp)
                        .padding(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray,
                        contentColor = Color.Black
                    )
                ) {
                    Text(
                        text = label,
                        style = TextStyle(
                            fontSize = 34.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
}

fun evaluateExpression(displayValue: String): String {
    return TODO("Provide the return value")
}


@Composable
fun Button(text: String, onClick: () -> Unit = {}) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(4.dp)
            .size(64.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.LightGray,
            contentColor = Color.Black
        )
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}}


