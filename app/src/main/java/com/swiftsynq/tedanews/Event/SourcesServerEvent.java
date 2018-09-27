package com.swiftsynq.tedanews.Event;


import com.swiftsynq.tedanews.Model.News;
import com.swiftsynq.tedanews.Model.NewsSources;

/**
 * Created by TEMIDJOY on 09/19/2018.
 */

public class SourcesServerEvent {
    private NewsSources serverResponse;

    public SourcesServerEvent(NewsSources serverResponse) {
        this.serverResponse = serverResponse;
    }

    public NewsSources getServerResponse() {
        return serverResponse;
    }

    public void setServerResponse(NewsSources serverResponse) {
        this.serverResponse = serverResponse;
    }
}