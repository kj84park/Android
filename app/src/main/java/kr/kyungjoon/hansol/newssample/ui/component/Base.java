package kr.kyungjoon.hansol.newssample.ui.component;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import kr.kyungjoon.hansol.newssample.di.DaggerMainComponent;
import kr.kyungjoon.hansol.newssample.di.MainComponent;
import kr.kyungjoon.hansol.newssample.di.MainModule;

public class Base extends AppCompatActivity {

    MainComponent mainComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainComponent = DaggerMainComponent.builder().mainModule(new MainModule()).build();
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }
}
