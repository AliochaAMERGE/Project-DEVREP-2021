package fr.su.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.su.bankapp.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
