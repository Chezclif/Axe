package llc.taurusgroup.exampleapp.mvp.presenters;


import android.support.v7.app.AppCompatActivity;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import llc.taurusgroup.exampleapp.mvp.views.IStartView;

@InjectViewState
public class StartPresenter extends MvpPresenter<IStartView> {

    private AppCompatActivity appCompatActivity;

    public StartPresenter(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    public void uploadWords(){

    }
}
