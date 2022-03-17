package com.example.application.data;

import com.example.application.data.entity.Company;
import com.example.application.data.entity.Contact;
import com.example.application.data.entity.Status;
import com.example.application.data.repository.CompanyRepository;
import com.example.application.data.repository.ContactRepository;
import com.example.application.data.repository.StatusRepository;
import com.vaadin.exampledata.DataType;
import com.vaadin.exampledata.ExampleDataGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
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
        ExampleDataGenerator<Company> companyGenerator = new ExampleDataGenerator<>(Company.class,
                LocalDateTime.now());
        companyGenerator.setData(Company::setName, DataType.COMPANY_NAME);
        companyGenerator.create(5, seed).stream().forEach(company -> company.persist());
        List<Company> companies = companyRepository.listAll();

        Stream.of("Imported lead", "Not contacted", "Contacted", "Customer", "Closed (lost)")
                .map(Status::new).forEach(s -> s.persist());
        List<Status> statuses = statusRepository.listAll();

        logger.info("... generating 50 Contact entities...");

        ExampleDataGenerator<Contact> contactGenerator = new ExampleDataGenerator<>(Contact.class,
                LocalDateTime.now());
        contactGenerator.setData(Contact::setFirstName, DataType.FIRST_NAME);
        contactGenerator.setData(Contact::setLastName, DataType.LAST_NAME);
        contactGenerator.setData(Contact::setEmail, DataType.EMAIL);

        Random r = new Random(seed);
        List<Contact> contacts = contactGenerator.create(50, seed).stream().peek(contact -> {
            contact.setCompany(companies.get(r.nextInt(companies.size())));
            contact.setStatus(statuses.get(r.nextInt(statuses.size())));
        }).collect(Collectors.toList());

        contacts.stream().forEach(contact -> contact.persist());

        logger.info("Generated demo data");

    }
}
