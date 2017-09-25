package tech.chezclif.poleaxe;

import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tech.chezclif.poleaxe.annotations.BindModel;
import tech.chezclif.poleaxe.annotations.ModelField;
import tech.chezclif.poleaxe.rules.ViewRule;

public class PoleAxe<T> {
    private Object mController;
    private Class<T> modelClass;
    private List<Field> annotatedControllerFields;
    private List<Field> annotatedModelFields;
    private HashMap<Class<?>, ViewRule> allRules;
    private HashMap<Integer, ViewRule> specialRules;

    public PoleAxe(Object mController, Class<T> modelClass) {
        this.mController = mController;
        this.modelClass = modelClass;
        collectControllerFields();
        collectModelFields();
        createDefaultViewRules();
    }

    private void collectControllerFields() {
        annotatedControllerFields = new ArrayList<>();
        Class<?> klass = mController.getClass();
        for (Field fieldModel : klass.getDeclaredFields()) {
            fieldModel.setAccessible(true);
            if (fieldModel.isAnnotationPresent(BindModel.class)) {
                annotatedControllerFields.add(fieldModel);
            }
        }
    }

    private void collectModelFields() {
        annotatedModelFields = new ArrayList<>();
        for (Field fieldContr : modelClass.getDeclaredFields()) {
            fieldContr.setAccessible(true);
            if (fieldContr.isAnnotationPresent(ModelField.class)) {
                annotatedModelFields.add(fieldContr);
            }
        }
    }

    private void createDefaultViewRules() {
        allRules = new HashMap<>();
        allRules.put(TextInputLayout.class, new ViewRule<TextInputLayout, String, String>() {
            @Override
            public String getData(TextInputLayout view) {
                return view.getEditText().getText().toString();
            }

            @Override
            public void setData(TextInputLayout view, String s) {
                view.getEditText().setText(s);
            }
        });
        allRules.put(EditText.class, new ViewRule<EditText, String, String>() {
            @Override
            public String getData(EditText view) {
                return view.getText().toString();
            }

            @Override
            public void setData(EditText view, String s) {
                view.setText(s);
            }
        });
        allRules.put(CheckBox.class, new ViewRule<CheckBox, Boolean, Boolean>() {
            @Override
            public Boolean getData(CheckBox checkBox) {
                return checkBox.isChecked();
            }

            @Override
            public void setData(CheckBox checkBox, Boolean aBoolean) {
                checkBox.setChecked(aBoolean);
            }
        });
    }

    public void addCustomRule(Class<?> aClass, ViewRule viewRule) {
        if (allRules.containsKey(aClass)) {
            allRules.remove(aClass);
        }
        allRules.put(aClass, viewRule);
    }

    public void addSpecialRule(View view, ViewRule viewRule) {
        int id = view.getId();
        if (specialRules == null) {
            specialRules = new HashMap<>();
        }
        if (specialRules.containsKey(id)) {
            specialRules.remove(id);
        }
        specialRules.put(id, viewRule);
    }

    private boolean isHaveSpecialRule(View view) {
        if (specialRules == null) {
            return false;
        }
        int id = view.getId();
        return specialRules.containsKey(id);
    }

    private void setModelField(T mod, String fieldName, Object value) throws IllegalAccessException {
        for (Field field : mod.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(ModelField.class)) {
                ModelField fieldName1 = field.getAnnotation(ModelField.class);
                if (fieldName.equals(fieldName1.value())) {
                    if (field.getType().isAssignableFrom(String.class)) {
                        field.set(mod, value);
                    } else if (field.getType().isAssignableFrom(Integer.class)) {
                        field.set(mod, value);
                    } else if (field.getType().isAssignableFrom(int.class)) {
                        field.setInt(mod, (Integer) value);
                    } else if (field.getType().isAssignableFrom(Long.class)) {
                        field.set(mod, value);
                    } else if (field.getType().isAssignableFrom(long.class)) {
                        field.setLong(mod, (Long) value);
                    } else if (field.getType().isAssignableFrom(Float.class)) {
                        field.set(mod, value);
                    } else if (field.getType().isAssignableFrom(float.class)) {
                        field.setFloat(mod, (Float) value);
                    } else if (field.getType().isAssignableFrom(Double.class)) {
                        field.set(mod, value);
                    } else if (field.getType().isAssignableFrom(double.class)) {
                        field.setDouble(mod, (Double) value);
                    } else if (field.getType().isAssignableFrom(Boolean.class)) {
                        field.set(mod, value);
                    } else if (field.getType().isAssignableFrom(boolean.class)) {
                        field.setBoolean(mod, (Boolean) value);
                    } else if (field.getType().isAssignableFrom(Byte.class)) {
                        field.set(mod, value);
                    } else if (field.getType().isAssignableFrom(byte.class)) {
                        field.setByte(mod, (Byte) value);
                    } else {
                        field.set(mod, value);
                    }
                }
            }
        }
    }

    /**
     * Get model value by key
     *
     * @param modelKey
     * @param mod
     * @return
     */
    private Object getValue(String modelKey, T mod) {
        Object o = new Object();
        for (Field field : mod.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(ModelField.class)) {
                ModelField annotModel = field.getAnnotation(ModelField.class);
                if (modelKey.equals(annotModel.value())) {
                    try {
                        o = field.get(mod);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return o;
    }

    /**
     * Collect model from views
     *
     * @return
     */
    public T updateModel(@Nullable T model) {
        if (model == null) {
            try {
                model = this.modelClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        for (Field field : annotatedControllerFields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(BindModel.class)) {
                View view = getView(field);
                BindModel bindModel = field.getAnnotation(BindModel.class);
                ViewRule viewRule = null;
                if (isHaveSpecialRule(view)) {
                    viewRule = specialRules.get(view.getId());
                } else {
                    viewRule = allRules.get(view.getClass());
                }
                if (viewRule != null) {
                    try {
                        setModelField(model, bindModel.value(), viewRule.getData(view));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return model;
    }

    /**
     * Bind the model params to views
     *
     * @param newModel
     */
    public void bindView(T newModel) {
        for (Field fieldContr : annotatedControllerFields) {
            fieldContr.setAccessible(true);
            if (fieldContr.isAnnotationPresent(BindModel.class)) {
                View view = getView(fieldContr);
                BindModel bindModel = fieldContr.getAnnotation(BindModel.class);
                ViewRule viewRule = null;
                if (isHaveSpecialRule(view)) {
                    viewRule = specialRules.get(view.getId());
                } else {
                    viewRule = allRules.get(view.getClass());
                }
                if (viewRule != null) {
                    viewRule.setData(view, getValue(bindModel.value(), newModel));
                }
            }
        }
    }

    /**
     * Get View object from reflection
     *
     * @param field
     * @return
     */
    private View getView(final Field field) {
        View view = null;
        try {
            field.setAccessible(true);
            view = (View) field.get(mController);
            if (view == null) {
                String message = String.format("'%s %s' is null.",
                        field.getType().getSimpleName(), field.getName());
                throw new IllegalStateException(message);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return view;
    }
}
