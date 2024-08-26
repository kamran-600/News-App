package com.kamran.newsapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kamran.newsapp.R

@Composable
fun LoginScreen(
    modifier: Modifier,
    onNavigateToRegister: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        //Spacer(modifier = Modifier.fillMaxSize(0.1f))

        Text(
            text = "Login",
            style = TextStyle(
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        )

        // Email
        var email by rememberSaveable { mutableStateOf("") }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(0.85f),
            value = email,
            onValueChange = {
                email = it
            },
            textStyle = TextStyle(fontSize = 16.sp),
            label = {
                Text(text = "Email", style = TextStyle(fontSize = 16.sp))
            },
            singleLine = true,
            placeholder = {
                Text(text = "Enter email", style = TextStyle(fontSize = 16.sp))
            },
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Email, contentDescription = "Email")
            },
            trailingIcon = {
                if (email.isNotEmpty()) {
                    IconButton(onClick = { email = "" }) {
                        Icon(imageVector = Icons.Rounded.Clear, contentDescription = "clear")
                    }
                }

            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )

        var password by rememberSaveable { mutableStateOf("") }
        var passWordIconVisibility by rememberSaveable { mutableStateOf(false) }
        val icon =
            if (passWordIconVisibility) painterResource(id = R.drawable.visibility_off) else painterResource(
                id = R.drawable.visibility
            )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(0.85f),
            value = password,
            onValueChange = {
                password = it
            },
            textStyle = TextStyle(fontSize = 16.sp),
            label = {
                Text(text = "Password", style = TextStyle(fontSize = 16.sp))
            },
            singleLine = true,
            placeholder = {
                Text(text = "Enter Password", style = TextStyle(fontSize = 16.sp))
            },
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Lock, contentDescription = "Email")
            },
            trailingIcon = {
                IconButton(onClick = { passWordIconVisibility = !passWordIconVisibility }) {
                    Icon(painter = icon, contentDescription = "Password Visibility Toggle")
                }
            },
            visualTransformation = if (passWordIconVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )

        Text(text = "Forgot Password?", style = TextStyle(fontSize = 16.sp))


        Button(
            onClick = { onNavigateToHome() },
            contentPadding = PaddingValues(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                colorResource(id = R.color.purple_200),
                                colorResource(id = R.color.teal_700)
                            )
                        )
                    )
                    .padding(30.dp, 12.dp)
            )
            {
                Text(text = "Login", style = TextStyle(fontSize = 18.sp))
            }

        }

        Spacer(modifier = Modifier.weight(1f))


        val dontHaveAccountText = buildAnnotatedString {
            append("Don't have an account? ")

            withStyle(
                style = SpanStyle(
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline
                )
            ) {
                append("Register")
            }

        }

        Text(
            text = dontHaveAccountText,
            modifier = Modifier
                .padding(30.dp)
                .align(Alignment.CenterHorizontally)
                .clickable {
                    onNavigateToRegister()
                }
        )
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun LoginPreview() {
    LoginScreen(Modifier, {}) {
    }
}