package com.stamp.mcs.utils.responses.generic;

import com.stamp.mcs.utils.responses.DefaultResponse;

/**
 * Created by kenneth on 7/19/17.
 */
public class AddResponse<T> extends DefaultResponse {

    private T newItem;

    public T getNewItem() {
        return newItem;
    }

    public void setNewItem(T newItem) {
        this.newItem = newItem;
    }
}
