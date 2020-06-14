package it.uniroma3.siw.Silph.repository;

import it.uniroma3.siw.Silph.model.Project;
import it.uniroma3.siw.Silph.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	  /**
     * Retrieve Users by Username
     * @param username of the requested User
     * @return the User with that username (if exists)
     */
    public Optional<User> findByUsername(String username);

    /**
     * Retrieve all Users that have visibility over the passed project
     * @param project The Project to find the members of
     * @return the List of Users that have visibility over the passed Project
     */
    public List<User> findByVisibleProjects(Project project);
}
