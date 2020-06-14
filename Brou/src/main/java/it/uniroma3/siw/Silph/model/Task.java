package it.uniroma3.siw.Silph.model;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



@Entity
public class Task {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;


	@Column(nullable = false, length = 100)
	private String name;


	@Column
	private String description;


	@Column
	private List<String> commentsList;

	@Column(updatable = false, nullable = false)
	private LocalDateTime creationTimestamp;

	/*User that has this Task assigned*/
	@ManyToOne(fetch = FetchType.EAGER)   //if you get the Task get its assigned User too
	private User assigned;

	/*The List of Tags associated to this User	*/
	@ManyToMany(mappedBy = "Tags")
	private List<Tag> associatedTags;



	//CONSTRUCTORS

	public Task() {
		this.associatedTags = new ArrayList<>();
	}

	public Task(String name,
			String description/*,
                boolean completed*/) {
		this.name = name;
		this.description = description;
		//this.completed = completed;
	}


	// GETTERS AND SETTERS

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 * public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
	 */

	public LocalDateTime getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(LocalDateTime creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

	public User assignTaskToUser(User user) {
		if(this.assigned != null)
			this.assigned= user;
		return this.assigned;
	}

	public void newComment(String comment) {
		this.commentsList.add(comment);
	}

	public void newTag(Tag tag) {
		if(!this.associatedTags.contains(tag))
			this.associatedTags.add(tag);
	}

	/*
    public User getAssigned() {
        return assigned;
    }

    public void setAssigned(User assigned) {
        this.assigned = assigned;
    }
	 */

	//HASHCODE, EQUALS 

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Task task = (Task) o;
		return Objects.equals(name, task.name) &&
				Objects.equals(creationTimestamp, task.creationTimestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name,  creationTimestamp);
	}


	@Override
	public String toString() {

		return "Task {" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" +
				'}';
	}
}
