package it.uniroma3.siw.Silph.model;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
//Modifica
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;


	@Column(nullable = false, length = 100)
	private String firstName;


	@Column(nullable = false, length = 100)
	private String lastName;

	@Column(updatable = false, nullable = false)
	private LocalDateTime SignUpTimeStamp;


	/**
	 * The List of Projects owned by this User
	 */
	@OneToMany(cascade = CascadeType.REMOVE,      // remove every owned project if the user is deleted
			mappedBy = "Owner")
	private List<Project> ownedProjects;

	/**
	 * The List of Projects visible to this User
	 */
	@ManyToMany(mappedBy = "Members")
	private List<Project> visibleProjects;

	/**
	 * The List of Projects owned by this User
	 */
	@OneToMany(cascade = CascadeType.REMOVE,      // remove every assigned task if the user is deleted
			mappedBy = "Assigned to")
	private List<Task> assignedTasks;

	//CONSTRUCTORS

	public User() {
		this.ownedProjects = new ArrayList<>();
		this.visibleProjects = new ArrayList<>();
		this.assignedTasks = new ArrayList<>();
	}

	public User(String firstName, String lastName) {
		this();
		this.firstName = firstName;
		this.lastName = lastName;
	}


	//GETTERS AND SETTERS

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDateTime getSignUpTimeStamp() {
		return SignUpTimeStamp;
	}

	public void setSignUpTimeStamp(LocalDateTime SignUpTimeStamp) {
		this.SignUpTimeStamp = SignUpTimeStamp;
	}

	public List<Project> getOwnedProjects() {
		return ownedProjects;
	}

	public void setOwnedProjects(List<Project> ownedProjects) {
		this.ownedProjects = ownedProjects;
	}

	public List<Project> getVisibleProjects() {
		return visibleProjects;
	}

	public void setVisibleProjects(List<Project> visibleProjects) {
		this.visibleProjects = visibleProjects;
	}

	public List<Task> getAssignedTasks() {
		return assignedTasks;
	}

	public void setAssignedTasks(List<Task> assignedTasks) {
		this.assignedTasks = assignedTasks;
	}



	// HASHCODE, EQUALS AND TOSTRING


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;
		User user = (User) o;
		return Objects.equals(firstName, user.firstName) &&
				Objects.equals(lastName, user.lastName) &&
				Objects.equals(SignUpTimeStamp, user.SignUpTimeStamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName);
	}

	@Override
	public String toString() {
		return "User {" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", creationTimestamp=" + SignUpTimeStamp +
				", lastUpdateTimestamp=" +
				'}';
	}
}

