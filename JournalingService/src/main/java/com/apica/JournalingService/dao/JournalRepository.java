package com.apica.JournalingService.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.apica.JournalingService.model.JournalEntry;

@Repository
public interface JournalRepository extends CrudRepository<JournalEntry, String> {
}
