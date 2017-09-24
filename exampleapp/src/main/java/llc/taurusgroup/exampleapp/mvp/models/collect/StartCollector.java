package llc.taurusgroup.exampleapp.mvp.models.collect;

import java.util.ArrayList;
import java.util.List;

import llc.taurusgroup.exampleapp.mvp.models.rest.GuruRequest;
import tech.chezclif.poleaxe.annotations.ModelField;

public class StartCollector {
    @ModelField("firstString")
    private String firstString;
    @ModelField("secondString")
    private String secondString;
    @ModelField("isCheck")
    private Boolean isCheck;

    public StartCollector(String firstString, String secondString, Boolean isCheck) {
        this.firstString = firstString;
        this.secondString = secondString;
        this.isCheck = isCheck;
    }

    public String getFirstString() {
        return firstString;
    }

    public void setFirstString(String firstString) {
        this.firstString = firstString;
    }

    public String getSecondString() {
        return secondString;
    }

    public void setSecondString(String secondString) {
        this.secondString = secondString;
    }

    public Boolean getCheck() {
        return isCheck;
    }

    public void setCheck(Boolean check) {
        isCheck = check;
    }

    public GuruRequest convert() {
        GuruRequest guruRequest = new GuruRequest();
        List<String> stringList = new ArrayList<>();
        stringList.add(String.format("First string is %s , second string is %s", firstString, secondString));
        guruRequest.setId(123);
        guruRequest.setMethod("guru.test");
        guruRequest.setParams(stringList);
        return guruRequest;
    }
}
