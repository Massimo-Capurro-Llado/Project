package it.uniroma3.siw.Silph.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.Silph.model.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
	
	

}
