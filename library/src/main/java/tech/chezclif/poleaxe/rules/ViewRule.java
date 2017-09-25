package tech.chezclif.poleaxe.rules;

import android.view.View;

/**
 * Rule for collect and bind data
 * @param <T> View class for work
 * @param <I> Data type for bind into View
 * @param <O> Data type extracted from View
 */
public interface ViewRule<T extends View,I,O> {
    public O getData(T view);
    public void setData(T view, I i);
}
