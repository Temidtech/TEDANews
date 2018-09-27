package com.swiftsynq.tedanews.Event;


import com.swiftsynq.tedanews.Model.News;

/**
 * Created by TEMIDJOY on 09/19/2018.
 */

public class HeadlinesServerEvent {
    private News serverResponse;

    public HeadlinesServerEvent(News serverResponse) {
        this.serverResponse = serverResponse;
    }

    public News getServerResponse() {
        return serverResponse;
    }

    public void setServerResponse(News serverResponse) {
        this.serverResponse = serverResponse;
    }
}