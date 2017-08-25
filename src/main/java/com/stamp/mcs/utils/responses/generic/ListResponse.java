package com.stamp.mcs.utils.responses.generic;

import com.stamp.mcs.utils.responses.DefaultResponse;

import java.util.List;

/**
 * Created by kenneth on 7/19/17.
 */
public class ListResponse<T> extends DefaultResponse {

    private List<T> content;

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
