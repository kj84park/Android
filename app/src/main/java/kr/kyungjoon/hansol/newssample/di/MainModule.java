package kr.kyungjoon.hansol.newssample.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import kr.kyungjoon.hansol.newssample.network.RetroClient;

/**
 * Created by HANSOL on 2018-03-14.
 */

@Module
public class MainModule {

    @Provides
    @Singleton
    public RetroClient getRetroClient() {
        return new RetroClient();
    }
}
