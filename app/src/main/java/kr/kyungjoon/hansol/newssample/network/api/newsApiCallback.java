package kr.kyungjoon.hansol.newssample.network.api;

/**
 * Created by HANSOL on 2018-03-18.
 */

public interface newsApiCallback<T> {

    void onError(Throwable t);

    void onSuccess(int code, T receivedData);

    void onFailure(int code);
}
