package it.uniroma3.siw.Silph.repository;
import java.util.List;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.Silph.model.Tag;

@Repository
public interface TagRepository extends CrudRepository<Tag ,Long>{

	
	
	public List<Tag> findTop15ByOrderByIdDesc();
}
