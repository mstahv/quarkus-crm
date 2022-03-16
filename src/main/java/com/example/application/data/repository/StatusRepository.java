package com.example.application.data.repository;

import com.example.application.data.entity.Status;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StatusRepository implements PanacheRepository<Status> {

}
