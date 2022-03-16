package com.example.application.data.repository;

import com.example.application.data.entity.Contact;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ContactRepository implements PanacheRepository<Contact> {

/*    @Query("select c from Contact c " +
        "where lower(c.firstName) like lower(concat('%', :searchTerm, '%')) " +
        "or lower(c.lastName) like lower(concat('%', :searchTerm, '%'))")*/
    public List<Contact> search( String searchTerm) {
        // TODO
        return listAll();
    }
}
