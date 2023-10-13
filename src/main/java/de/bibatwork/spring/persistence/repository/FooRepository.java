package de.bibatwork.spring.persistence.repository;

import de.bibatwork.spring.domain.entity.Foo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FooRepository extends CrudRepository<Foo, Long> {

}
