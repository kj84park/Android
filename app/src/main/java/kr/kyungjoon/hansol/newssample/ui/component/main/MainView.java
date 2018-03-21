package kr.kyungjoon.hansol.newssample.ui.component.main;

import android.widget.Spinner;

import java.util.List;

import kr.kyungjoon.hansol.newssample.network.dto.Articles;

/**
 * Created by HANSOL on 2018-03-13.
 */

public interface MainView {

    void setProgressBarVisibility(int visibility);
    void initRecyclerView(List<Articles> articles);

    Spinner getSpinnerCountry();
    Spinner getSpinnerCategory();
}
