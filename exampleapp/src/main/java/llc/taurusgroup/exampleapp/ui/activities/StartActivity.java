package llc.taurusgroup.exampleapp.ui.activities;

import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import llc.taurusgroup.exampleapp.R;
import llc.taurusgroup.exampleapp.mvp.presenters.StartPresenter;
import llc.taurusgroup.exampleapp.mvp.views.IStartView;
import llc.taurusgroup.exampleapp.ui.base.BaseActivity;

public class StartActivity extends BaseActivity implements IStartView {

    @InjectPresenter
    StartPresenter mStartPresenter;

    @ProvidePresenter
    StartPresenter provideStartPresenter(){
        return new StartPresenter(StartActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
    }

    @Override
    public void errorSnack(String error) {
    }

    @Override
    public void bindView(Object model) {
    }
}
