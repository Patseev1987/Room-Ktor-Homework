package ru.bogdan.application

import android.app.Application
import ru.bogdan.application.di.AppComponent
import ru.bogdan.application.di.DaggerAppComponent

class HomeApp : Application() {
    val appComponent: AppComponent by lazy { DaggerAppComponent.factory().create(this) }
}
