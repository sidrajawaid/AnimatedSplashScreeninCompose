package com.example.animatedsplashscreentutorial

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.animatedsplashscreentutorial.ViewModel.MainviewModel
import com.example.animatedsplashscreentutorial.ui.theme.AnimatedSplashScreenTutorialTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    private val mainVieModel by viewModels<MainviewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !mainVieModel.isReady.value
            }

            setOnExitAnimationListener { screen ->
                val zoomx = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_X,
                    0.4f, 0.0f
                )
                zoomx.interpolator = OvershootInterpolator()
                zoomx.duration = 800
                zoomx.doOnEnd {
                    screen.remove()

                }

                val zoomy = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_Y,
                    0.4f, 0.0f
                )
                zoomy.interpolator = OvershootInterpolator()
                zoomy.duration = 800
                zoomy.doOnEnd {
                    screen.remove()

                }

                zoomx.start()
                zoomy.start()
            }
            setContent {
                AnimatedSplashScreenTutorialTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Greeting(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
        setContent {
            AnimatedSplashScreenTutorialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        AnimatedSplashScreenTutorialTheme {
            Greeting("Android")
        }
    }
}