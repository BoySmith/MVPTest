package com.zyb.mvptest.model;

import android.os.Handler;
import android.os.Message;

/**
 * Created by zhangyb on 2017/3/8.
 */
public class LoginModel {

    private static final int LOGIN_SUCCESS = 1;
    private static final int LOGIN_FAILED = 2;

    private String rightUserName = "111";
    private String rightPassword = "222";

    private static LoginModel instance;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == LOGIN_SUCCESS) {
                loginResultListener.loginSuccess();
            } else if (msg.what == LOGIN_FAILED) {
                loginResultListener.loginFailed();
            }
        }
    };

    private ILoginResultListener loginResultListener;

    public static LoginModel getInstance() {
        if (instance == null) {
            instance = new LoginModel();
        }
        return instance;
    }

    public void Login(final String userName, final String password) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(2000); //模拟和服务器交互过程
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (userName.equals(rightUserName) && password.equals(rightPassword)) {
                    handler.sendEmptyMessage(LOGIN_SUCCESS); //转到UI线程，不需要在外面再转换了
                } else {
                    handler.sendEmptyMessage(LOGIN_FAILED);
                }
            }
        }).start();
    }

    public interface ILoginResultListener {
        void loginSuccess();

        void loginFailed();
    }

    public void setLoginListener(ILoginResultListener listener) {
        loginResultListener = listener;
    }
}
