package com.zyb.mvptest.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zyb.mvptest.R;
import com.zyb.mvptest.contract.IBookContract.IBookPresenter;
import com.zyb.mvptest.contract.IBookContract.IBookView;
import com.zyb.mvptest.presenter.BookPresenter;

import java.util.List;

/**
 * Created by zhangyb on 2017/12/14.
 */

public class BookActivity extends FragmentActivity implements IBookView {

    private ProgressDialog loadingDialog;
    private ListView bookListView;

    private IBookPresenter bookPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        bookListView = (ListView) findViewById(R.id.book_list_view);
        loadingDialog = new ProgressDialog(this);
        loadingDialog.setMessage("请稍后...");

        new BookPresenter(this);

        bookPresenter.getBookList();
    }

    @Override
    public void setPresenter(IBookPresenter iBookPresenter) {
        bookPresenter = iBookPresenter;
    }

    @Override
    public void showLoadingDialog() {
        loadingDialog.show();
    }

    @Override
    public void dismissLoadingDialog() {
        loadingDialog.dismiss();
    }

    @Override
    public void showBookList(List<String> books) {
        bookListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, books));
    }
}
