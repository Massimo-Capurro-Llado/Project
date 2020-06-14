package it.uniroma3.siw.Silph.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
public class Tag {

	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 100)
    private String color;
    
    @Column(nullable = false, length = 100)
    private String name;
    
    @Column
    private String description;


    
    /*The List of Tasks visible to this Tag	*/
	@ManyToMany(mappedBy = "Tasks")
	private List<Task> associatedTasks;

    
    //CONSTRUCTORS
    
    public Tag() {
    	this.associatedTasks = new ArrayList<>();
    }

    public Tag(String color,
    			String name,
                String description) {
        this.name = name;
        this.description = description;
        this.color = color;
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


    //HASHCODE, EQUALS 

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(name, tag.name) &&
                Objects.equals(color, tag.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,  color);
    }
    

    @Override
    public String toString() {

        return "Tag {" +
                "id=" + id +
                ", color=" + color +
                ", name='" + name + '\'' +
                ", description='" +
                '}';
    }
}