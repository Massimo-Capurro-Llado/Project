package it.uniroma3.siw.Silph.service;

import it.uniroma3.siw.Silph.model.Project;
import it.uniroma3.siw.Silph.model.Tag;
import it.uniroma3.siw.Silph.model.User;
import it.uniroma3.siw.Silph.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
public class ProjectService {

	@Autowired
	protected ProjectRepository projectRepository;

	/**
	 * This method retrieves a Project from the DB based on its ID.
	 * @param id the id of the Project to retrieve from the DB
	 * @return the retrieved Project, or null if no Project with the passed ID could be found in the DB
	 */
	@Transactional
	public Project getProject(long id) {
		Optional<Project> result = this.projectRepository.findById(id);
		return result.orElse(null);
	}

	/**
	 * This method saves a Project in the DB.
	 * @param project the Project to save into the DB
	 * @return the saved Project
	 */
	@Transactional
	public Project saveProject(Project project) {
		return this.projectRepository.save(project);
	}

	/**
	 * This method deletes a Project from the DB.
	 * @param project the Project to delete from the DB
	 */
	@Transactional
	public void deleteProject(Project project) {
		this.projectRepository.delete(project);
	}

	/**
	 * This method saves a Project among the ones shared with a specific User.
	 * @param project the Project to share with the User
	 * @param user the User with to share the Project to
	 * @return the shared Project
	 */
	@Transactional
	public Project shareProjectWithUser(Project project, User user) {
		project.addMember(user);
		return this.projectRepository.save(project);
	}

	/**
	 * This method saves a new assigned Tag to a Project.
	 * @param tag the Tag assigned to the Project
	 * @param project the Project that has this new Tag assigned
	 * @return the Project itself
	 */
	@Transactional
	public Project saveAssignedTag(Project project, Tag tag) {
		project.addTag(tag);
		return this.projectRepository.save(project);
	}
}
