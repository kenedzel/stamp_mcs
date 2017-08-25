package com.stamp.mcs.services;

import com.stamp.mcs.entities.Stamp;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kenneth on 7/19/17.
 */
@Service
public interface StampService {

    List<Stamp> findAll();
    Stamp add(Stamp stampDTO);
    Stamp findOne(String id);
}
