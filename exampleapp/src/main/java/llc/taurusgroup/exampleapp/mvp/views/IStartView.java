package llc.taurusgroup.exampleapp.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface IStartView extends MvpView, IVMBindView, IProgressView, IShortError {
    @StateStrategyType(value = SkipStrategy.class)
    public void showResult(String s);
}
