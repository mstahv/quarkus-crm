package com.example.application.data;

import com.example.application.data.entity.Company;
import com.example.application.data.entity.Contact;
import com.example.application.data.entity.Status;
import com.example.application.data.repository.CompanyRepository;
import com.example.application.data.repository.ContactRepository;
import com.example.application.data.repository.StatusRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class CrmService {

    @Inject
    ContactRepository contactRepository;

    @Inject
    CompanyRepository companyRepository;

    @Inject
    StatusRepository statusRepository;

    @Inject
    DemoDataGenerator demoDataGenerator;

    @Inject
    EntityManager em;

    @PostConstruct
    void init() {
        demoDataGenerator.ensureDemoData(contactRepository, companyRepository, statusRepository);
    }

    public List<Contact> listAll() {
        return contactRepository.listAll();
    }

    public List<Company> listAllCompanies() {
        return companyRepository.listAll();
    }

    public List<Status> listAllStatuses() {
        return statusRepository.listAll();
    }

    @Transactional
    public void saveContact(Contact contact) {
        em.merge(contact);
    }

    @Transactional
    public void deleteContact(Contact contact) {
        em.remove(em.getReference(Contact.class, contact.id));
    }

    public List<Contact> findAllContacts(String value) {
        return contactRepository.search(value);
    }

    public long countContacts() {
        return contactRepository.count();
    }
}
