package com.zyb.mvptest.model;

import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyb on 2017/3/8.
 */
public class BookModel {

    private static BookModel instance;

    private IGetBookResultListener getBookResultListener;

    public static BookModel getInstance() {
        if (instance == null) {
            instance = new BookModel();
        }
        return instance;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            getBookResultListener.getBookSuccess(initBooks());
        }
    };

    public void getBookList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000); //模拟和服务器交互过程
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.sendEmptyMessage(1);
            }
        }).start();
    }

    private List<String> initBooks() {
        List<String> data = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            data.add("book" + i);
        }

        return data;
    }

    public interface IGetBookResultListener {
        void getBookSuccess(List<String> bookList);
    }

    public void setGetBookResultListener(IGetBookResultListener listener) {
        getBookResultListener = listener;
    }
}
