package it.uniroma3.siw.Silph.repository;

import it.uniroma3.siw.Silph.model.Project;
import it.uniroma3.siw.Silph.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * This interface is a CrudRepository for repository operations on Projects.
 *
 * @see Project
 */
public interface ProjectRepository extends CrudRepository<Project, Long> {

    /**
     * Retrieve all Projects that are visible by the passed user
     * @param member the User to retrieve the visible projects 
     * @return List of projects visible by the passed user
     */
    public List<Project> findByMembers(User member);

    /**
     * Retrieve all Projects that are owned by the passed user
     * @param owner the User to retrieve the Projects of
     * @return List of projects owned by the passed user
     */
    public List<Project> findByOwner(User owner);
}
