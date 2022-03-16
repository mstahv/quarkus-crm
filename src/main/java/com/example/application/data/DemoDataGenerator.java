package com.example.application.data;

import com.example.application.data.entity.Company;
import com.example.application.data.entity.Contact;
import com.example.application.data.entity.Status;
import com.example.application.data.repository.CompanyRepository;
import com.example.application.data.repository.ContactRepository;
import com.example.application.data.repository.StatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Stream;

@ApplicationScoped
public class DemoDataGenerator {

    @Transactional
    public void ensureDemoData(ContactRepository contactRepository, CompanyRepository companyRepository,
                               StatusRepository statusRepository) {

        Logger logger = LoggerFactory.getLogger(getClass());
        if (contactRepository.count() != 0L) {
            logger.info("Using existing database");
            return;
        }
        int seed = 123;

        logger.info("Generating demo data");
        Company company = new Company();
        company.setName("RedHat");
        company.persist();
        company = new Company();
        company.setName("Vaadin");
        company.persist();

        Stream.of("Imported lead", "Not contacted", "Contacted", "Customer", "Closed (lost)")
                .map(Status::new).forEach(s -> s.persist());
        List<Status> statuses = statusRepository.listAll();

        logger.info("... generating 50 Contact entities...");

        Contact contact = new Contact();
        contact.setFirstName("Joonas");
        contact.setLastName("Lehtinen");
        contact.setEmail("joonas@vaadin.com");
        contact.setCompany(company);
        contact.setStatus(statuses.get(statuses.size()-1));
        contact.persist();

        logger.info("Generated demo data");

    }
}
