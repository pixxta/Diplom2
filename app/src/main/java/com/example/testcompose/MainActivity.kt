package com.example.testcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testcompose.ui.theme.TestComposeTheme
import java.math.RoundingMode
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    private val startText = "Введите число"
    private val bigNumText = "Введите число"

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fun calcNumButton(n: String, b: String, oper: Int): String {
            if (n == startText && b == "0") {
                return "0."
            }
            if (b == "0" && (n.substringAfter('*').isEmpty() || n.substringAfter('/')
                    .isEmpty() || n.substringAfter('+').isEmpty() || n.substringAfter('-')
                    .isEmpty()) && (n.substringBefore('*')
                    .isNotEmpty() || n.substringBefore('/').isNotEmpty() || n.substringBefore('+')
                    .isNotEmpty() || n.substringBefore('-').isNotEmpty())
            ) {
                return n + "0."
            }
            return if (n == startText || oper == -1) {
                b
            } else {
                n + b
            }
        }


        fun calcClearButton(n: String): String {
            return if (n == startText || n == "0." || n == bigNumText) {
                startText
            } else {
                if (n.count() == 1) {
                    startText
                } else {
                    n.dropLast(1)
                }
            }
        }



        setContent {
            TestComposeTheme {
                DraggableTextLowLevel(Color.Black)
                var num by remember {
                    mutableStateOf(startText)
                }
                var checkOperation by remember {
                    mutableStateOf(0)
                }
                Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.End) {
                    Column(

                    ) {
                        Row(
                            modifier = Modifier
                                .padding(top = 30.dp)
                                .width(360.dp)
                        ) {
                            var offsetX by remember { mutableStateOf(0f) }
                            var offsetY by remember { mutableStateOf(0f) }

                            TextField(value = num, onValueChange = {}, textStyle = TextStyle(fontSize=46.sp), readOnly = true, singleLine = true, modifier = Modifier
                                .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                                .pointerInput(Unit) {
                                    detectDragGestures { change, dragAmount ->
                                        change.consume()
                                        offsetX += dragAmount.x
                                        offsetY += dragAmount.y
                                    }

                                })

//                            TextField(
//                                value = num,
//                                onValueChange = {},
//                                textStyle = TextStyle(fontSize = 46.sp),
//                                readOnly = true,
//                                singleLine = true,
//                                )

                        }
                        Row(modifier = Modifier.padding(end = 30.dp)) {
                            Button(
                                onClick = {
                                    num = calcNumButton(num, "7", checkOperation)
                                    if (checkOperation == -1) checkOperation = 0
                                },
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(90.dp)
                            ) { Text(text = "7", fontSize = 50.sp) }
                            Button(
                                onClick = {
                                    num = calcNumButton(num, "8", checkOperation)
                                    if (checkOperation == -1) checkOperation = 0
                                },
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(90.dp)
                            ) { Text(text = "8", fontSize = 50.sp) }
                            Button(
                                onClick = {
                                    num = calcNumButton(num, "9", checkOperation)
                                    if (checkOperation == -1) checkOperation = 0
                                },
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(90.dp)
                            ) { Text(text = "9", fontSize = 50.sp) }
                            Button(
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(90.dp),
                                onClick = {
                                },

                                ) {
                                Text(text = "<-",
                                    fontSize = 50.sp,
                                    modifier = Modifier.combinedClickable(
                                        onClick = {
                                            num = calcClearButton(num)
                                            checkOperation = 0
                                        },
                                        onLongClick = {
                                            num = startText
                                            checkOperation = 0
                                        }
                                    ))
                            }


                        }

                        Row() {
                            Button(
                                onClick = {
                                    num = calcNumButton(num, "4", checkOperation)
                                    if (checkOperation == -1) checkOperation = 0
                                },
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(90.dp)
                            ) { Text(text = "4", fontSize = 50.sp) }
                            Button(
                                onClick = {
                                    num = calcNumButton(num, "5", checkOperation)
                                    if (checkOperation == -1) checkOperation = 0
                                },
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(90.dp)
                            ) { Text(text = "5", fontSize = 50.sp) }
                            Button(
                                onClick = {
                                    num = calcNumButton(num, "6", checkOperation)
                                    if (checkOperation == -1) checkOperation = 0
                                },
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(90.dp)
                            ) { Text(text = "6", fontSize = 50.sp) }
                            Button(
                                onClick = {
                                    if (num != startText && num != bigNumText && num != "0." && checkOperation == 0) {
                                        num += "+"
                                        checkOperation = 1
                                    }
                                    if (checkOperation == -1) {
                                        num += "+"
                                        checkOperation = 1
                                    }


                                }, modifier = Modifier
                                    .height(90.dp)
                                    .width(90.dp)
                            ) {
                                Text(
                                    text = "+",
                                    fontSize = 50.sp
                                )
                            }
                        }

                        Row() {
                            Button(
                                onClick = {
                                    num = calcNumButton(num, "1", checkOperation)
                                    if (checkOperation == -1) checkOperation = 0
                                },
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(90.dp)
                            ) { Text(text = "1", fontSize = 50.sp) }
                            Button(
                                onClick = {
                                    num = calcNumButton(num, "2", checkOperation)
                                    if (checkOperation == -1) checkOperation = 0
                                },
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(90.dp)
                            ) { Text(text = "2", fontSize = 50.sp) }
                            Button(
                                onClick = {
                                    num = calcNumButton(num, "3", checkOperation)
                                    if (checkOperation == -1) checkOperation = 0
                                },
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(90.dp)
                            ) { Text(text = "3", fontSize = 50.sp) }
                            Button(
                                onClick = {
                                    if (num != startText && num != bigNumText && num != "0." && checkOperation == 0) {
                                        num += "-"
                                        checkOperation = 2
                                    }

                                    if (checkOperation == -1) {
                                        num += "-"
                                        checkOperation = 2
                                    }
                                },
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(90.dp)
                            ) { Text(text = "-", fontSize = 50.sp) }
                        }

                        Row() {
                            Button(
                                onClick = { num = calcNumButton(num, "0", checkOperation) },
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(90.dp)
                            ) { Text(text = "0", fontSize = 50.sp) }
                            Button(
                                onClick = {
                                    if (checkOperation == 1 && num.substringAfter('+')
                                            .isNotEmpty()
                                    ) {
                                        num =
                                            (num.substringBefore('+')
                                                .toDouble() + num.substringAfter(
                                                '+'
                                            )
                                                .toDouble()).toBigDecimal()
                                                .setScale(4, RoundingMode.UP).toDouble().toString()
                                        checkOperation = -1
                                    }

                                    if (checkOperation == 2 && num.substringAfter('-')
                                            .isNotEmpty()
                                    ) {
                                        num =
                                            (num.substringBefore('-')
                                                .toDouble() - num.substringAfter('-')
                                                .toDouble()).toBigDecimal()
                                                .setScale(4, RoundingMode.UP).toDouble().toString()
                                        checkOperation = -1
                                    }

                                    if (checkOperation == 3 && num.substringAfter('*')
                                            .isNotEmpty()
                                    ) {
                                        num =
                                            (num.substringBefore('*')
                                                .toDouble() * num.substringAfter('*')
                                                .toDouble()).toBigDecimal()
                                                .setScale(4, RoundingMode.UP).toDouble().toString()
                                        checkOperation = -1
                                    }

                                    if (checkOperation == 4 && num.substringAfter('/')
                                            .isNotEmpty()
                                    ) {
                                        if(num.substringAfter('/') == "0"){
                                            checkOperation = -1
                                            num = startText

                                        }
                                        else{
                                        num =
                                            (num.substringBefore('/')
                                                .toDouble() / num.substringAfter('/')
                                                .toDouble()).toBigDecimal()
                                                .setScale(4, RoundingMode.UP).toDouble().toString()
                                        checkOperation = -1}
                                    }
                                }, modifier = Modifier
                                    .height(90.dp)
                                    .width(90.dp)
                            ) {
                                Text(
                                    text = "=",
                                    fontSize = 50.sp
                                )
                            }
                            Button(
                                onClick = {
                                    if (num != startText && num != bigNumText && num != "0." && checkOperation == 0) {
                                        num += "/"
                                        checkOperation = 4
                                    }

                                    if (checkOperation == -1) {
                                        num += "/"
                                        checkOperation = 4
                                    }
                                },
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(90.dp)
                            ) { Text(text = "/", fontSize = 50.sp) }
                            Button(
                                onClick = {
                                    if (num != startText && num != bigNumText && num != "0." && checkOperation == 0) {
                                        num += "*"
                                        checkOperation = 3
                                    }

                                    if (checkOperation == -1) {
                                        num += "*"
                                        checkOperation = 3
                                    }
                                },
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(90.dp)
                            ) { Text(text = "*", fontSize = 50.sp) }
                        }

                    }
                }

            }
        }
    }
}

@Composable
fun newTextField() {
    TextField(value = "Новое число", onValueChange = {})
}

@Composable
private fun DraggableTextLowLevel(col: Color) {
    Box(modifier = Modifier.fillMaxSize()) {
        var offsetX by remember { mutableStateOf(0f) }
        var offsetY by remember { mutableStateOf(0f) }

        Box(
            Modifier
                .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                .background(col)
                .size(100.dp)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        offsetX += dragAmount.x
                        offsetY += dragAmount.y
                    }
                }
                .onFocusChanged {  }
        ){
            val count = remember { mutableStateOf(0) }
            // content that you want to make clickable
            Text(
                fontSize=50.sp,
                textAlign = TextAlign.Center,
                text = count.value.toString(),
                color=Color.Red,
                modifier = Modifier.clickable { count.value += 1 }
            )
        }
    }
}