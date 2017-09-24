package llc.taurusgroup.exampleapp.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface IProgressView extends MvpView {
    public void doProgressDialog(boolean isChecked);
    public void dismissProgressDialog();
}
