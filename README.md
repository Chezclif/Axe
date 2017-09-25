# Axe

Небольшое решение для обмена данными между View и конкретной сущностью в Android.

## Установка
build.gradle project
```xml
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
    ...
        maven { url 'https://dl.bintray.com/chezclif/PoleAxe' }
    }
}
```
build.gradle app
```xml
dependencies {
    ...
    compile 'com.github.chezclif:Axe:0.0.5'
}
```

## Использование
Создаем класс, помечаем нужные поля аннотациями, указывая уникальные текстовые ключи:
```java
public class StartCollector {
    @ModelField("firstString")
    private String firstString;
    @ModelField("secondString")
    private String secondString;
```
В activty/fragment, нужные нам view так же помечаются ключами:

```java
    @BindModel("firstString")
    TextInputLayout textInputOne;
    @BindModel("secondString")
    TextInputLayout textInputTwo;
```
Настраиваем библиотеку для работы:
```java
PoleAxe<StartCollector> poleAxe=new PoleAxe<>(this, StartCollector.class);
```

Взаимодействие может происходить только, если указаны правильные ключи для view и полей сущности.

Собрать всю информацию с объектов view с последующим созданием или обновлением сущности:
```java
StartCollector startCollector = poleAxe.updateModel();
...
startCollector = poleAxe.updateModel(startCollector)
```

Информацию с полей сущности переносим на объекты view
```java
 poleAxe.bindView(startCollector);
```

## Дополнительно
Можно настраивать поведение Axe для классов или отдельных view обьектов:
```java
    @BindModel("isCheck")
    Switch switchTest;
    ...
            poleAxe.addSpecialRule(switchTest
            , new ViewRule<Switch, Boolean, Boolean>() {
            @Override
            public Boolean getData(Switch view) {
                return view.isChecked();
            }

            @Override
            public void setData(Switch view, Boolean aBoolean) {
                view.setChecked(aBoolean);
            }
        });
```

```java
        poleAxe.addCustomRule(TextInputLayout.class
        , new ViewRule<TextInputLayout, String, String>() {
            @Override
            public String getData(TextInputLayout view) {
                return view.getEditText().getText().toString();
            }

            @Override
            public void setData(TextInputLayout view, String s) {
                view.getEditText().setText(s);
            }
        });
        
```
License
----

MIT


