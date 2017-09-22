package llc.taurusgroup.exampleapp.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface IShortError  extends MvpView {
    @StateStrategyType(value = SkipStrategy.class)
    public void errorSnack(String error);
}
