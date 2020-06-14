package it.uniroma3.siw.Silph.model;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Credentials model the account credentials for a user of the application.
 * It contains data such as the username, the (secured) password and the role of a user.
 */
@Entity
public class Credentials {

    public static final String DEFAULT_ROLE = "DEFAULT";
    public static final String ADMIN_ROLE = "ADMIN";

  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

   
    @Column(unique = true, nullable = false, length = 100)
    private String username;

  
    @Column(nullable = false, length = 100)
    private String password;

    /**
     * The List of Projects owned by this User
     */
    @OneToOne(cascade = CascadeType.ALL)        // when the account is created...
                                                   //when the account is removed
    private User user;

    /**
     * The date that this User was created/loaded into the DB
     */
    @Column(updatable = false, nullable = false)
    private LocalDateTime creationTimestamp;

    
    @Column(nullable = false, length = 10)
    private String role;

    public Credentials() {
    }

    public Credentials(String username, String password) {
        this();
        this.username = username;
        this.password = password;
    }

    // GETTERS AND SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public LocalDateTime getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(LocalDateTime creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // EQUALS AND HASHCODE

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Credentials)) return false;
        Credentials user = (Credentials) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(role, user.role) &&
                Objects.equals(creationTimestamp, user.creationTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, role);
    }

    @Override
    public String toString() {
        return "Credentials{" +
                "id=" + id +
                ", userName='" + username + '\'' +
                ", role='" + role + '\'' +
                ", creationTimestamp=" + creationTimestamp +
                ", lastUpdateTimestamp=" + 
                '}';
    }
}
