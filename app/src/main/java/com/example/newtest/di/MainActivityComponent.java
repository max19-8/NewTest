package com.example.newtest.di;
import com.example.newtest.presentation.view.MainActivity;
import dagger.BindsInstance;
import dagger.Subcomponent;

@Subcomponent(modules = {PresenterModule.class})
@ActivityScope
public interface MainActivityComponent {
    void inject(MainActivity activity);
    @Subcomponent.Factory
    interface Factory {
        MainActivityComponent create(@BindsInstance  MainActivity activity);
    }
}