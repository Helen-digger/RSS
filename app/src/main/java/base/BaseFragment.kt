package base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import app.App
import injection.DaggerPresenterInjector
import injection.PresenterInjector
import injection.PresenterModule

abstract class BaseFragment : Fragment(), BaseView {

    @get:LayoutRes
    protected abstract val layoutResource: Int

    abstract fun inject(component: PresenterInjector)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inject(
            DaggerPresenterInjector.builder()
                .applicationInjector(App.component())
                .presenterModule(PresenterModule())
                .build())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutResource, container, false)
    }
}