package com.kamran.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.kamran.newsapp.presentation.navgraph.NavGraph
import com.kamran.newsapp.screens.HomeScreen
import com.kamran.newsapp.screens.LoginScreen
import com.kamran.newsapp.screens.RegisterScreen
import com.kamran.newsapp.ui.theme.NewsAppTheme
import com.kamran.newsapp.utils.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition { viewModel.splashCondition }
        enableEdgeToEdge()

        setContent {
            NewsAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                  //  App(modifier = Modifier.padding(innerPadding))
                    val padding = innerPadding

                    val startDestination = viewModel.startDestination
                    NavGraph(startDestination = startDestination)

                }
            }
        }
    }
}


@Composable
fun App(modifier: Modifier = Modifier) {


    var currentScreen by rememberSaveable(stateSaver = screenSaver) { mutableStateOf(Screen.Login) }

    when (currentScreen) {
        Screen.Login -> LoginScreen(modifier = modifier, {
            currentScreen = Screen.Register
        }, {
            currentScreen = Screen.Home
        })

        Screen.Register -> RegisterScreen(modifier = modifier, {
            currentScreen = Screen.Login
        }, {
            currentScreen = Screen.Home
        })

        Screen.Home -> {
            HomeScreen(modifier = modifier) {
                currentScreen = Screen.Login
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AppPreview() {
    App()
}


val screenSaver = Saver<Screen, String>(
    save = {
        when (it) {
            Screen.Login -> "Login"
            Screen.Register -> "Register"
            Screen.Home -> "Home"
        }
    },
    restore = {
        when (it) {
            "Login" -> Screen.Login
            "Register" -> Screen.Register
            "Home" -> Screen.Home
            else -> throw IllegalArgumentException("Unknown screen: $it")
        }
    }
)
