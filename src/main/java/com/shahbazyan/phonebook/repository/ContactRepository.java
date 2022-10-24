package com.shahbazyan.phonebook.repository;

import com.shahbazyan.phonebook.models.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends JpaRepository<Contacts, Long> {

}
