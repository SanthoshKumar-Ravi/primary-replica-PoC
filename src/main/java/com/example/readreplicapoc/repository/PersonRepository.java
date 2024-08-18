package com.example.readreplicapoc.repository;

import com.example.readreplicapoc.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
