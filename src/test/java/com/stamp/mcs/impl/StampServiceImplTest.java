package com.stamp.mcs.impl;

import com.stamp.mcs.StampMcsApplication;
import com.stamp.mcs.entities.Stamp;
import com.stamp.mcs.entities.Transaction;
import com.stamp.mcs.repositories.StampRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by kenneth on 8/4/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StampMcsApplication.class)
public class StampServiceImplTest {

    @Autowired
    StampRepository stampRepository;

    public Stamp getMockStamp() {
        Stamp stamp = new Stamp();

        return stamp;
    }

    @Test
    public void testSave() {

    }

}