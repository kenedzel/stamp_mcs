package com.stamp.mcs.utils.responses.generic;

import com.stamp.mcs.utils.responses.DefaultResponse;

/**
 * Created by kenneth on 7/24/17.
 */
public class ObjectResponse<T> extends DefaultResponse {

    private T content;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
