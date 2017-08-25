package com.stamp.mcs.impl;

import com.stamp.mcs.entities.Stamp;
import com.stamp.mcs.repositories.StampRepository;
import com.stamp.mcs.services.StampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by kenneth on 7/19/17.
 */
@Primary
@Service
public class StampServiceImpl implements StampService {

    @Autowired
    StampRepository stampRepository;

    @Override
    public List<Stamp> findAll() {
        return stampRepository.findAll();
    }

    @Override
    public Stamp findOne(String id) {
        return stampRepository.findOne(id);
    }

    @Override
    public Stamp add(Stamp stamp) {
        stamp.setName("Kenneth Edzel");
        stamp.setStampIn(new Date().getTime());
        stamp.setStampOut(new Date().getTime());
        return stampRepository.save(stamp);
    }
}
