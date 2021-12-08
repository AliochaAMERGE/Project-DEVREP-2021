package fr.su.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.su.bankapp.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select c from Client c where c.firstName = :firstName and c.lastName = :lastName and c.email = :email")
    Client findByIdentity(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("email") String email);
    // Optional<Client> findByName(String firstname, String lastname, String email);
}
