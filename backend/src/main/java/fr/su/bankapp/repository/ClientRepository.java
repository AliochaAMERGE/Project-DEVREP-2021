package fr.su.bankapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.su.bankapp.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    // @Query("select c from Client c where c.firstname = :firstname and c.lastname = :lastname and c.email = :email")
    // Optional<Client> findByName(@Param("firstname") String firstname, @Param("lastname") String lastname, @Param("email") String email);
    // // Optional<Client> findByName(String firstname, String lastname, String email);

}
