package llc.taurusgroup.exampleapp.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface IVMBindView<T> extends MvpView {
    @StateStrategyType(value = AddToEndSingleStrategy.class)
    public void bindView(T model);
}
