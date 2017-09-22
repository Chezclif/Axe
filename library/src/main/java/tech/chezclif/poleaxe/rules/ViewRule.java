package tech.chezclif.poleaxe.rules;

import android.view.View;

/**
 * Правило, по которому осуществляется взятие и посылка данных во View
 * @param <T> Класс View, который будет обработан
 * @param <I> Формат данных, передаваемый во View
 * @param <O> Формат данных, забираемый с View
 */
public interface ViewRule<T extends View,I,O> {
    public O getData(T view);
    public void setData(T view, I i);
}
