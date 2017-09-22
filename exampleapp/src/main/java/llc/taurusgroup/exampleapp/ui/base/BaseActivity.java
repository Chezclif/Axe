package llc.taurusgroup.exampleapp.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.arellomobile.mvp.MvpDelegate;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;


public class BaseActivity extends RxAppCompatActivity {
    private AppCompatActivity activity;
    private MvpDelegate<? extends BaseActivity> mMvpDelegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getMvpDelegate().onCreate(savedInstanceState);
        activity = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.getMvpDelegate().onDetach();
        if (this.isFinishing()) {
            this.getMvpDelegate().onDestroy();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.getMvpDelegate().onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.getMvpDelegate().onAttach();
    }

    public MvpDelegate getMvpDelegate() {
        if (this.mMvpDelegate == null) {
            this.mMvpDelegate = new MvpDelegate(this);
        }
        return this.mMvpDelegate;
    }
}
