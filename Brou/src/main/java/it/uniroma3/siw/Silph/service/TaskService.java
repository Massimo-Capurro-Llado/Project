package it.uniroma3.siw.Silph.service;

import it.uniroma3.siw.Silph.model.Tag;
import it.uniroma3.siw.Silph.model.Task;
import it.uniroma3.siw.Silph.model.User;
import it.uniroma3.siw.Silph.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TaskService {

	@Autowired
	protected TaskRepository taskRepository;

	/**
	 * This method retrieves a Task from the DB based on its ID.
	 * @param id the id of the Task to retrieve from the DB
	 * @return the retrieved Task, or null if no Task with the passed ID could be found in the DB
	 */
	@Transactional
	public Task getTask(long id) {
		Optional<Task> result = this.taskRepository.findById(id);
		return result.orElse(null);
	}

	/**
	 * This method saves a Task in the DB.
	 * @param task the Task to save into the DB
	 * @return the saved Task
	 */
	@Transactional
	public Task saveTask(Task task) {
		return this.taskRepository.save(task);
	}

	/**
	 * This method assignes a Task in the DB to an User.
	 * @param task the Task to assign
	 * @param user the user that has this task now assigned
	 * @return the task assigned
	 */
	@Transactional
	public Task saveAssignedUser(User user, Task task) {
		task.assignTaskToUser(user);  
		return this.taskRepository.save(task);
	}


	/**
	 * This method deletes a Task from the DB.
	 * @param task the Task to delete from the DB
	 */
	@Transactional
	public void deleteTask(Task task) {
		this.taskRepository.delete(task);
	}

	/**
	 * This method saves a new comment for the Task.
	 * @param comment the String to save
	 * @param task the Task which the comment refers to
	 * @return the Task, with the new comment 
	 */
	@Transactional
	public Task saveComment(String comment, Task task) {
		task.newComment(comment);
		return this.taskRepository.save(task);
	}

	/**
	 * This method saves a new Tag for the Task.
	 * @param tag the Tag to save for the Task
	 * @param task the Task that has this new Tag
	 * @return the Task, with the new Tag 
	 */
	@Transactional
	public Task saveNewTag(Tag tag, Task task) {
		task.newTag(tag);
		return this.taskRepository.save(task);
	}

}
