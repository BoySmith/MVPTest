package com.zyb.mvptest.contract;

import com.zyb.mvptest.base.IBaseView;

/**
 * Created by zhangyb on 2017/3/8.
 * function:关联 LoginPresenter 和 LoginActivity(也就是view) 的契约接口类，定义了两个类需要实现的接口。
 */
public interface ILoginContract {

    /*
    * LoginPresenter 实现此接口
    * */
    interface ILoginPresenter {
        void login();
    }

    /*
    * LoginView 实现此接口
    * */
    interface ILoginView extends IBaseView<ILoginPresenter> {
        String getUserName();

        String getUserPassword();

        void showLoadingDialog();

        void dismissLoadingDialog();

        void ToMainActivity();

        void showFailedToast();
    }
}
