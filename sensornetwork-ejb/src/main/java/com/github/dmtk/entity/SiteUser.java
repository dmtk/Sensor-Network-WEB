package com.github.dmtk.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
    @NamedQuery(name = "SiteUser.findAll", query = "SELECT s FROM SiteUser s"),
    @NamedQuery(name = "SiteUser.findByEmail", query = "SELECT s FROM SiteUser s WHERE s.email = :email"),
    @NamedQuery(name = "SiteUser.findByPassword", query = "SELECT s FROM SiteUser s WHERE s.password = :password"),
    @NamedQuery(name = "SiteUser.findByUsername", query = "SELECT s FROM SiteUser s WHERE s.username = :username")})
public class SiteUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Wrong email")//if the field contains email address consider using this annotation to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String email;
    @Size(max = 100)
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    private String username;
    @Column(name = "siterole")
    @Min(value = 0)
    private Integer siterole;

    public SiteUser() {
    }

    public SiteUser(String email) {
        this.email = email;
    }

    public SiteUser(String email, String username) {
        this.email = email;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getRole() {
        return siterole;
    }

    public void setRole(Integer role) {
        this.siterole = role;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SiteUser)) {
            return false;
        }
        SiteUser other = (SiteUser) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.github.dmtk.entity.User[ email=" + email + " ]";
    }

}
