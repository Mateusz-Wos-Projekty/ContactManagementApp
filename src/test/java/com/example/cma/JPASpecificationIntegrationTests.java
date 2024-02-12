package com.example.cma;

import cma.api.config.CMAConfig;
import cma.api.model.Contact;
import cma.api.repository.ContactManagementAppRepository;
import cma.filter.SearchCriteria;
import cma.filter.UserSpecification;
import jakarta.transaction.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isIn;

@RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(classes = { CMAConfig.class })
    @Transactional
public class JPASpecificationIntegrationTests {

        @Autowired
        private ContactManagementAppRepository repository;

        private Contact userJohn;
        private Contact userTom;

        @Before
        public void init() {
            userJohn = new Contact();
            userJohn.setFirstName("John");
            userJohn.setLastName("Doe");
            userJohn.setDateOfBirth(LocalDate.of(2018,9,10));
            userJohn.setMobileNumber(111222333);
            userJohn.setAddress("Janasa 15");
            repository.save(userJohn);

            userTom = new Contact();
            userTom.setFirstName("Tom");
            userTom.setLastName("Doe");
            userTom.setDateOfBirth(LocalDate.of(2018,9,10));
            userTom.setAddress("Janasa 20");
            repository.save(userTom);
        }

    @Test
    public void givenLast_whenGettingListOfUsers_thenCorrect() {
        UserSpecification spec =
                new UserSpecification(new SearchCriteria("lastName", ":", "doe"));

        List<Contact> results = repository.findAll(spec);

        assertThat(userJohn, isIn(results));
        assertThat(userTom, isIn(results));
    }



    }
