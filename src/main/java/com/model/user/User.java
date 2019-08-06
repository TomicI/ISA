package com.model.user;

import com.model.RentACar;
import com.model.aviokompanija.Ocena;
import com.model.aviokompanija.Putnik;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.model.RentACar;
import com.model.RezervacijaRentACar;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "enabled")
	private boolean enabled;

	@Column(name = "reset")
	private boolean reset;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Set<Role> roles;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "rent_id", unique = true)
	private RentACar rentACar;

	/*@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "avio_id", unique = true)
	private Aviokompanija aviokompanija;*/

	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
	private Set<RezervacijaRentACar> rezervacije = new HashSet<RezervacijaRentACar>();




	@OneToMany(mappedBy = "user")
	private Set<Ocena> ocene;

	@OneToOne
	private Putnik putnik;

	public User() {
		super();
		ocene = new HashSet<>();
	}

	public User(String username, String password, String firstName, String lastName, String email) {

		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.enabled = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the rentACar
	 */
	public RentACar getRentACar() {
		return rentACar;
	}

	/**
	 * @param rentACar the rentACar to set
	 */
	public void setRentACar(RentACar rentACar) {
		this.rentACar = rentACar;
	}

	/**
	 * @return the reset
	 */
	public boolean isReset() {
		return reset;
	}

	/**
	 * @param reset the reset to set
	 */
	public void setReset(boolean reset) {
		this.reset = reset;
	}

	/**
	 * @return the aviokompanija
	 */
	/*public Aviokompanija getAviokompanija() {
		return aviokompanija;
	}

	/**
	 * @param aviokompanija the aviokompanija to set
	 */
/*	public void setAviokompanija(Aviokompanija aviokompanija) {
		this.aviokompanija = aviokompanija;
	}*/

	/**
	 * @return the rezervacije
	 */
	public Set<RezervacijaRentACar> getRezervacije() {
		return rezervacije;
	}

	/**
	 * @param rezervacije the rezervacije to set
	 */
	public void setRezervacije(Set<RezervacijaRentACar> rezervacije) {
		this.rezervacije = rezervacije;
	}

}
