package it.uniroma3.siw.Silph.model;


import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(updatable = false, nullable = false)
	private LocalDateTime creationTimeStamp;

	/* Owner of this Project  */

	@ManyToOne(fetch = FetchType.EAGER)   //if you get the project get its owner too
	private User owner;

	/*Members names for this Project*/

	@ManyToMany(fetch = FetchType.LAZY)	   //not of interest to get every member for the project                              
	private List<User> members;

	/* Tasks that this project contains */
	@OneToMany(fetch = FetchType.EAGER,        // whenever a Project is retrieved, always retrieve its tasks too
			cascade = CascadeType.REMOVE)   // if a Project is deleted, all its tasks must be deleted too
	@JoinColumn(name="project_id")
	private List<Task> tasks;

	/* Tags associated to this project */
	@OneToMany(fetch = FetchType.EAGER,        // whenever a Project is retrieved, always retrieve its tags too
			cascade = CascadeType.REMOVE)   // if a Project is deleted, all its tags must be deleted too
	@JoinColumn(name="project_id")
	private List<Tag> tags;


	//CONSTRUCTORS

	public Project() {
		this.members = new ArrayList<>();
		this.tasks = new ArrayList<>();
		this.tags = new ArrayList<>();
	}

	public Project(String name, String description) {
		this();
		this.name = name;
	}



	//GETTERS AND SETTERS

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

	public LocalDateTime getCreationTimeStamp() {
		return creationTimeStamp;
	}

	public void setCreationTimeStamp(LocalDateTime creationTimeStamp) {
		this.creationTimeStamp = creationTimeStamp;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}



	public void addMember(User user) {
		if (!this.members.contains(user))
			this.members.add(user);
	}


	public void addTag(Tag tag) {
		if(!this.tags.contains(tag))
			this.tags.add(tag);
	}


	//HASHCODE, EQUALS AND TOSTRING

	@Override
	public String toString() {

		return "Project {" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + '\'' +
				", tasks=" + tasks +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Project project = (Project) o;
		return Objects.equals(name, project.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	
}
