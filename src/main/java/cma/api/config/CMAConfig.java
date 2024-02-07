package cma.api.config;

import cma.api.mapper.CMAMapper;
import cma.api.model.Contact;
import cma.api.repository.ContactManagementAppRepository;
import cma.api.service.CMAService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Configuration
public class CMAConfig {

    @Primary
    @Bean
    CMAService getMyService(ContactManagementAppRepository repository, CMAMapper mapper) {
        return new CMAService(mapper,repository);
    }
    @Bean
    CMAMapper getCMAMapper(){
        return new CMAMapper();
    }
    @Bean
    ContactManagementAppRepository getContactManagementRepository(){
        return new ContactManagementAppRepository() {
            @Override
            public void flush() {
            }
            @Override
            public <S extends Contact> S saveAndFlush(S entity) {
                return null;
            }
            @Override
            public <S extends Contact> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }
            @Override
            public void deleteAllInBatch(Iterable<Contact> entities) {
            }
            @Override
            public void deleteAllByIdInBatch(Iterable<Integer> integers) {
            }
            @Override
            public void deleteAllInBatch() {
            }
            @Override
            public Contact getOne(Integer integer) {
                return null;
            }
            @Override
            public Contact getById(Integer integer) {
                return null;
            }
            @Override
            public Contact getReferenceById(Integer integer) {
                return null;
            }
            @Override
            public <S extends Contact> List<S> findAll(Example<S> example) {
                return null;
            }
            @Override
            public <S extends Contact> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }
            @Override
            public <S extends Contact> List<S> saveAll(Iterable<S> entities) {
                return null;
            }
            @Override
            public List<Contact> findAll() {
                return null;
            }
            @Override
            public List<Contact> findAllById(Iterable<Integer> integers) {
                return null;
            }
            @Override
            public <S extends Contact> S save(S entity) {
                return null;
            }
            @Override
            public Optional<Contact> findById(Integer integer) {
                return Optional.empty();
            }
            @Override
            public boolean existsById(Integer integer) {
                return false;
            }
            @Override
            public long count() {
                return 0;
            }
            @Override
            public void deleteById(Integer integer) {
            }
            @Override
            public void delete(Contact entity) {
            }
            @Override
            public void deleteAllById(Iterable<? extends Integer> integers) {
            }
            @Override
            public void deleteAll(Iterable<? extends Contact> entities) {
            }
            @Override
            public void deleteAll() {
            }
            @Override
            public List<Contact> findAll(Sort sort) {
                return null;
            }
            @Override
            public Page<Contact> findAll(Pageable pageable) {
                return null;
            }
            @Override
            public <S extends Contact> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }
            @Override
            public <S extends Contact> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }
            @Override
            public <S extends Contact> long count(Example<S> example) {
                return 0;
            }
            @Override
            public <S extends Contact> boolean exists(Example<S> example) {
                return false;
            }
            @Override
            public <S extends Contact, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }
        };
    }
}
