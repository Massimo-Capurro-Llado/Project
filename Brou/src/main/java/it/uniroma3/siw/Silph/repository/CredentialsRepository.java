package it.uniroma3.siw.Silph.repository;
import it.uniroma3.siw.Silph.model.Credentials;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface CredentialsRepository extends CrudRepository<Credentials, Long> {

    /**
     * Retrieve Credentials by its username
     * @param username the username of the Credentials to retrieve
     * @return an Optional for the Credentials with the passed username
     */
    public Optional<Credentials> findByUsername(String username);
}