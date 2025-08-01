package ru.bogdan.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ru.bogdan.application.presentor.ui.MainApplicationScreen
import ru.bogdan.application.presentor.ui.theme.RoomNetworkHomeworkTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appComponent = (this.application as HomeApp).appComponent

        appComponent.inject(this)

        enableEdgeToEdge()
        setContent {
            RoomNetworkHomeworkTheme {
                MainApplicationScreen(
                    factory = factory,
                )
            }
        }
    }
}
