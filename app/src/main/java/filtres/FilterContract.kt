package filtres

import base.BasePresenter
import base.BaseView
import model.RssObject

interface FilterContract {
    interface Presenter : BasePresenter<View> {
    }

    interface View : BaseView {
    }
}