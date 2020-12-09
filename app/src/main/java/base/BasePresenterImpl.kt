package base

open class BasePresenterImpl<V : BaseView> : BasePresenter<V> {
    var view: V? = null

    override val isAttached = view != null

    override fun attach(view: V) {
        this.view = view
    }

    override fun detach() {
        view = null
    }
}