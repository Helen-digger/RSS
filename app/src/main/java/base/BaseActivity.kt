package base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import app.App
import injection.DaggerPresenterInjector
import injection.PresenterInjector
import injection.PresenterModule

abstract class BaseActivity : AppCompatActivity(), BaseView {

    @get:LayoutRes
    protected abstract val layoutResource: Int

    abstract fun inject(component: PresenterInjector)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)
        inject(
            DaggerPresenterInjector.builder()
                .applicationInjector(App.component())
                .presenterModule(PresenterModule())
                .build())
        init(savedInstanceState)
    }

    protected abstract fun init(state: Bundle?)
}