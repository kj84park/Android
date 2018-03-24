package kr.kyungjoon.hansol.newssample.ui.component.main;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import javax.inject.Inject;

import kr.kyungjoon.hansol.newssample.di.MainComponent;
import kr.kyungjoon.hansol.newssample.network.RetroClient;
import kr.kyungjoon.hansol.newssample.network.dto.GetResponse;
import kr.kyungjoon.hansol.newssample.network.listener.newsApiCallback;

/**
 * Created by HANSOL on 2018-03-13.
 */

public class MainPresenter {

    private final MainView view;

    @Inject
    public RetroClient retro;

    private String country;
    private String category;

    MainPresenter(MainView view, MainComponent mainComponent) {
        this.view = view;
        mainComponent.inject(this);
        init();
    }

    private void init() {

        country = "kr";
        category = null;

        view.getSpinnerCountry().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                country = (String) parent.getItemAtPosition(position);
                getNews();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                country = "kr";
            }
        });

        view.getSpinnerCategory().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = (String) parent.getItemAtPosition(position);
                if ("Headline".equals(category)) {
                    category = null;
                }
                getNews();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                category = null;
            }
        });
        getNews();
    }

    private void getNews() {

        if(retro == null){
            Log.d("###","retro  is null");
        }
        retro.getResponse(country, category, MainActivity.API_KEY, new newsApiCallback() {
            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onSuccess(Object receivedData) {
                GetResponse response = (GetResponse) receivedData;
                if(response != null) {
                    view.initRecyclerView(response.getArticles());
                    view.setProgressBarVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(int code) {

            }
        });
    }
}
