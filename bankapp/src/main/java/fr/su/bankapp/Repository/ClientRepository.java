package fr.su.bankapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.su.bankapp.data.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}