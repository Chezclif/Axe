package llc.taurusgroup.exampleapp.mvp.presenters;


import android.support.v7.app.AppCompatActivity;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import llc.taurusgroup.exampleapp.R;
import llc.taurusgroup.exampleapp.data.rest.RestManager;
import llc.taurusgroup.exampleapp.mvp.models.collect.StartCollector;
import llc.taurusgroup.exampleapp.mvp.views.IStartView;


@InjectViewState
public class StartPresenter extends MvpPresenter<IStartView> {
    private AppCompatActivity appCompatActivity;
    private RestManager restManager;

    public StartPresenter(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        restManager = new RestManager();
        getViewState().bindView(getTestEntity());
    }

    public void collectTo(StartCollector startCollector) {
        getViewState().doProgressDialog(startCollector.getCheck());
        restManager.getRestService()
                .getGuruResponse(startCollector.convert())
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(guruResponse -> {
                    getViewState().dismissProgressDialog();
                    getViewState().showResult(guruResponse.getResult());
                }, throwable -> {
                    getViewState().dismissProgressDialog();
                    getViewState().errorSnack(appCompatActivity.getString(R.string.error_message));
                });
    }

    private StartCollector getTestEntity(){
        return new StartCollector("First","Second",true);
    }
}
