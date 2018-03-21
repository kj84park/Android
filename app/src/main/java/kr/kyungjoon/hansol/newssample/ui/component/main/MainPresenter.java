package kr.kyungjoon.hansol.newssample.ui.component.main;

import android.view.View;
import android.widget.AdapterView;

import kr.kyungjoon.hansol.newssample.network.api.RetroClient;
import kr.kyungjoon.hansol.newssample.network.dto.GetResponse;
import kr.kyungjoon.hansol.newssample.network.listener.newsApiCallback;

/**
 * Created by HANSOL on 2018-03-13.
 */

class MainPresenter {

    private final MainView view;
    private RetroClient retro;

    private String country;
    private String category;

    MainPresenter(MainView view) {
        this.view = view;
        init();
    }

    private void init() {
        retro = RetroClient.getInstance().createBaseApi();
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
        retro.getResponse(country, category, MainActivity.API_KEY, new newsApiCallback() {
            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onSuccess(int code, Object receivedData) {
                GetResponse response = (GetResponse) receivedData;
                view.initRecyclerView(response.getArticles());
                view.setProgressBarVisibility(View.GONE);

            }

            @Override
            public void onFailure(int code) {

            }
        });
    }
}
