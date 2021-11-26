package fr.su.bankapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.su.bankapp.data.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}