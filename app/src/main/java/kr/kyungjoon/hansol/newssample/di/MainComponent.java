package kr.kyungjoon.hansol.newssample.di;

import javax.inject.Singleton;

import dagger.Component;
import kr.kyungjoon.hansol.newssample.ui.component.main.MainView;

/**
 * Created by HANSOL on 2018-03-14.
 */
@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainView activity);
}
