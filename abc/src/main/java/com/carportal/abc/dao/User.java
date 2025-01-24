package com.carportal.abc.dao;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column
	private String email;
	@Column
	private String password;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="users_roles",
				joinColumns=@JoinColumn(name="user_id"),
				inverseJoinColumns=@JoinColumn(name="role_id"))
	private Set<Role> roles = new HashSet<>();

	   public User() {

	    }

	    public User(String email, String password) {
	        this.email = email;
	        this.password = password;
	    }

	    public User(String email, String password, Set<Role> roles) {
	        this.email = email;
	        this.password = password;
	        this.roles = roles;
	    }


	    public User(String firstName, String password, String email, Set<Role> roles) {
			// TODO Auto-generated constructor stub
	    	 this.firstName=firstName;
	         this.password = password;
	         this.email = email;
	         this.roles = roles;
		}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	  @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        User user = (User) o;
	        return email.equals(user.email) &&
	                password.equals(user.password);
	    }
	
	@Override
	public int hashCode() {
		return Objects.hash(email,password);
	}
	
}
