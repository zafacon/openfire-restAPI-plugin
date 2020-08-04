package org.jivesoftware.openfire.plugin.rest.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class VCardEntity.
 */
@XmlRootElement(name = "vCard", namespace="vcard-temp")
@XmlType(propOrder = {"fullName", "nickname", "role", "email"})
public class VCardEntity {

    /** The fullName. */
    private String fullName;

    /** The nickname. */
    private String nickname;

    /** The role. */
    private String role;

    /** The email. */
    private String email;

    /**
     * Instantiates a new vCard entity.
     */
    public VCardEntity() {

    }

    /**
     * Instantiates a new vCard entity.
     *
     * @param fullName
     *            the fullName
     * @param nickname
     *            the nickname
     * @param role
     *            the role
     * @param email
     *            the email
     */
    public VCardEntity(String fullName, String nickname, String role, String email) {
        this.fullName = fullName;
        this.nickname = nickname;
        this.role = role;
        this.email = email;
    }

    /**
     * Gets the fullName.
     *
     * @return the fullName
     */
    @XmlElement(name="fullName")
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the fullName.
     *
     * @param fullName
     *            the new fullName
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Gets the nickname.
     *
     * @return the nickname
     */
    @XmlElement(name="nickname")
    public String getNickname() {
        return nickname;
    }

    /**
     * Sets the nickname.
     *
     * @param nickname
     *            the new nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Gets the role.
     *
     * @return the role
     */
    @XmlElement(name="role")
    public String getRole() {
        return role;
    }

    /**
     * Sets the role.
     *
     * @param role
     *            the new role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    @XmlElement(name="email")
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email
     *            the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
