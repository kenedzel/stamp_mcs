package com.stamp.mcs.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by kenneth on 7/19/17.
 */
@Document(collection = "stamp")
public class Stamp {

    @Id
    private String id;
    private String name;
    private Long stampIn;
    private Long stampOut;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getStampIn() {
        return stampIn;
    }

    public Long getStampOut() {
        return stampOut;
    }

    public Stamp setId(String id) {
        this.id = id;
        return this;
    }

    public Stamp setName(String name) {
        this.name = name;
        return this;
    }

    public Stamp setStampIn(Long stampIn) {
        this.stampIn = stampIn;
        return this;
    }

    public Stamp setStampOut(Long stampOut) {
        this.stampOut = stampOut;
        return this;
    }
}
