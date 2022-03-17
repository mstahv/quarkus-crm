package com.example.application.data.repository;

import com.example.application.data.entity.Contact;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ContactRepository implements PanacheRepository<Contact> {

    public List<Contact> search( String searchTerm) {
        return list("lower(firstName) like lower(concat('%', ?1, '%')) " +
        "or lower(lastName) like lower(concat('%', ?1, '%'))", searchTerm);
    }
}
