package llc.taurusgroup.exampleapp.ui.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import llc.taurusgroup.exampleapp.R;
import llc.taurusgroup.exampleapp.mvp.models.collect.StartCollector;
import llc.taurusgroup.exampleapp.mvp.presenters.StartPresenter;
import llc.taurusgroup.exampleapp.mvp.views.IStartView;
import llc.taurusgroup.exampleapp.ui.base.BaseActivity;
import tech.chezclif.poleaxe.PoleAxe;
import tech.chezclif.poleaxe.annotations.BindModel;
import tech.chezclif.poleaxe.rules.ViewRule;

public class StartActivity extends BaseActivity implements IStartView {
    @InjectPresenter
    StartPresenter mStartPresenter;

    @ProvidePresenter
    StartPresenter provideStartPresenter() {
        return new StartPresenter(StartActivity.this);
    }

    @BindModel("firstString")
    @BindView(R.id.textInputOne)
    TextInputLayout textInputOne;

    @BindModel("secondString")
    @BindView(R.id.textInputTwo)
    TextInputLayout textInputTwo;

    @BindModel("isCheck")
    @BindView(R.id.switchTest)
    Switch switchTest;

    @BindView(R.id.buttonProceed)
    Button buttonProceed;

    private PoleAxe<StartCollector> poleAxe;
    private StartCollector startCollector;
    private MaterialDialog materialDialogProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        ButterKnife.bind(this);
        initPoleAxe();
        getMvpDelegate().onAttach();
    }

    private void initPoleAxe() {
        poleAxe = new PoleAxe<>(this, StartCollector.class);
        poleAxe.addSpecialRule(switchTest, new ViewRule<Switch, Boolean, Boolean>() {
            @Override
            public Boolean getData(Switch view) {
                return view.isChecked();
            }

            @Override
            public void setData(Switch view, Boolean aBoolean) {
                view.setChecked(aBoolean);
            }
        });
        poleAxe.addCustomRule(TextInputLayout.class, new ViewRule<TextInputLayout, String, String>() {
            @Override
            public String getData(TextInputLayout view) {
                return view.getEditText().getText().toString();
            }

            @Override
            public void setData(TextInputLayout view, String s) {
                view.getEditText().setText(s);
            }
        });
    }

    @Override
    public void bindView(Object model) {
        if (model != null && model instanceof StartCollector) {
            startCollector = (StartCollector) model;
            poleAxe.bindView(startCollector);
        }
    }

    @OnClick(R.id.buttonProceed)
    public void onViewClicked() {
        mStartPresenter.collectTo(poleAxe.updateModel(startCollector));
    }

    public void doProgressDialog(boolean isCheked) {
        materialDialogProgress = new MaterialDialog.Builder(this)
                .title(R.string.dialog_progress)
                .cancelable(false)
                .theme(Theme.LIGHT)
                .widgetColor(ContextCompat.getColor(StartActivity.this, isCheked ? R.color.colorAccent : R.color.colorPrimaryDark))
                .progress(true, 0)
                .show();
    }

    @Override
    public void dismissProgressDialog() {
        if (materialDialogProgress != null) {
            materialDialogProgress.dismiss();
        }
    }

    @Override
    public void errorSnack(String error) {
        Snackbar snackbar = Snackbar.make(buttonProceed, error, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    @Override
    public void showResult(String s) {
        Toast.makeText(StartActivity.this, s, Toast.LENGTH_LONG).show();
    }
}
