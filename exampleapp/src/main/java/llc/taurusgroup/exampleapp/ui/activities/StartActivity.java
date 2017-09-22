package llc.taurusgroup.exampleapp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import llc.taurusgroup.exampleapp.R;
import llc.taurusgroup.exampleapp.mvp.views.IStartView;
import llc.taurusgroup.exampleapp.mvp.presenters.StartPresenter;
import llc.taurusgroup.exampleapp.ui.base.BaseActivity;

import com.arellomobile.mvp.presenter.InjectPresenter;

public class StartActivity extends BaseActivity implements IStartView {
    public static final String TAG = "StartActivity";
    @InjectPresenter
    StartPresenter mStartPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
    }
}
