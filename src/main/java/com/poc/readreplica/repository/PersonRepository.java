package com.poc.readreplica.repository;

import com.poc.readreplica.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
