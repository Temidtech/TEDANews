package com.swiftsynq.tedanews.Event;


import com.swiftsynq.tedanews.Model.News;

/**
 * Created by TEMIDJOY on 8/15/2017.
 */

public class ServerEvent {
    private News serverResponse;

    public ServerEvent(News serverResponse) {
        this.serverResponse = serverResponse;
    }

    public News getServerResponse() {
        return serverResponse;
    }

    public void setServerResponse(News serverResponse) {
        this.serverResponse = serverResponse;
    }
}