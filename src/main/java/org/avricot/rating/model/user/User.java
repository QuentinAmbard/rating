package org.avricot.rating.model.user;

import java.io.Serializable;
import java.security.Principal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.avricot.rating.model.DefaultObject;
import org.avricot.rating.model.Role;

/**
 * Represents a registered user of myprocurement.
 * <p>
 * A user is a {@link Person} with added information about the connection, the
 * subscription, etc.
 * </p>
 */
@Entity
@Table(name = "USER")
public class User extends DefaultObject implements Serializable, Principal {
    private static final long serialVersionUID = 1L;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "LASTNAME")
    private String lastname;

    @Column(name = "ENABLED")
    private boolean enabled;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "ACCOUNT_NON_EXPIRED")
    private boolean accountNonExpired = true;

    @Column(name = "ACCOUNT_NON_LOCKED")
    private boolean accountNonLocked = true;

    @Column(name = "CREDENTIALS_NON_EXPIRED")
    private boolean credentialsNonExpired = true;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "ROLES", joinColumns = { @JoinColumn(name = "USER_ID", nullable = false) })
    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", nullable = false)
    private Set<Role> roles = new HashSet<Role>();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_CONNECTION_DATE", nullable = false)
    private Date lastConnectionDate = new Date();

    @Column(name = "CREATION_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate = new Date();

    @Column(name = "MODIFICATION_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationDate = new Date();

    /**
     * Default {@link User} constructor.
     */
    public User() {
    }

    /**
     * {@link User} constructor uses to create a new user with a given email.
     */
    public User(final String email) {
        this.email = email;
    }

    @Override
    public String getName() {
        return email;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(final boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(final boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(final boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(final Set<Role> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Date getLastConnectionDate() {
        return lastConnectionDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(final Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    /**
     * True if the acconut has been activated (first connection + cgu accepted)
     */
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean activated) {
        this.enabled = activated;
    }

    public void setLastConnectionDate(final Date lastConnectionDate) {
        this.lastConnectionDate = lastConnectionDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(final String lastname) {
        this.lastname = lastname;
    }
}
