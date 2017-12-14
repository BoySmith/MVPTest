package com.zyb.mvptest.presenter;

import com.zyb.mvptest.contract.ILoginContract.ILoginPresenter;
import com.zyb.mvptest.contract.ILoginContract.ILoginView;
import com.zyb.mvptest.model.LoginModel;
import com.zyb.mvptest.model.LoginModel.ILoginResultListener;

/**
 * Created by zhangyb on 2017/3/8.
 */
public class LoginPresenter implements ILoginPresenter, ILoginResultListener {

    private ILoginView loginView;

    private LoginModel loginModel;

    public LoginPresenter(ILoginView view) {
        loginView = view;
        loginView.setPresenter(this);
        loginModel = LoginModel.getInstance();
        loginModel.setLoginListener(this);
    }

    @Override
    public void login() {
        String userName = loginView.getUserName();
        String password = loginView.getUserPassword();

        loginModel.Login(userName, password);
        loginView.showLoadingDialog();
    }

    @Override
    public void loginSuccess() {
        loginView.dismissLoadingDialog();
        loginView.ToMainActivity();
    }

    @Override
    public void loginFailed() {
        loginView.dismissLoadingDialog();
        loginView.showFailedToast();
    }
}
