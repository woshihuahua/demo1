package com.example.community.service;

import com.example.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class AlphaService {

    @Autowired
    private AlphaDao alphaDao;

    public AlphaService(){
        System.out.println("construction");
    }
    @PostConstruct
    public void init()
    {
        System.out.println("Initialization");
    }
    @PreDestroy
    public void destroy()
    {
        System.out.println("DESTRUCTION");
    }

    public String find(){
        return alphaDao.select();
    }
}
