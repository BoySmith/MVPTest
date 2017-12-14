package com.zyb.mvptest.presenter;

import com.zyb.mvptest.contract.IBookContract;
import com.zyb.mvptest.contract.IBookContract.IBookView;
import com.zyb.mvptest.model.BookModel;

import java.util.List;

/**
 * Created by zhangyb on 2017/3/8.
 */
public class BookPresenter implements IBookContract.IBookPresenter, BookModel.IGetBookResultListener {

    private IBookView bookView;
    private BookModel bookModel;

    public BookPresenter(IBookView view) {
        bookView = view;
        bookView.setPresenter(this);
        bookModel = BookModel.getInstance();
        bookModel.setGetBookResultListener(this);
    }

    @Override
    public void getBookList() {
        bookView.showLoadingDialog();
        bookModel.getBookList();
    }

    @Override
    public void getBookSuccess(List<String> bookList) {
        bookView.dismissLoadingDialog();
        bookView.showBookList(bookList);
    }
}
