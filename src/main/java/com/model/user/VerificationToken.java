package com.model.user;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class VerificationToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String verificationToken ;
	
	@OneToOne( targetEntity = User.class , fetch = FetchType.EAGER)
	@JoinColumn(nullable = false , name = "user_id")
	private User user;
	
	private Date expiryDate;
	
	private Date expirationTime (int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, minutes);
        return new Date(cal.getTime().getTime());
    }

	public VerificationToken(String token, User user) {
		this.user = user;
		verificationToken=token;
		expiryDate = expirationTime(60*24);
		
	}

	public VerificationToken(long id, String verificationToken, User user, Date expiryDate) {
		super();
		this.id = id;
		this.verificationToken = verificationToken;
		this.user = user;
		this.expiryDate = expiryDate;
	}

	public VerificationToken() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVerificationToken() {
		return verificationToken;
	}

	public void setVerificationToken(String verificationToken) {
		this.verificationToken = verificationToken;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	
}
