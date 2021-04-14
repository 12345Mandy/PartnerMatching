package edu.brown.cs.student.main.stable_roommates;

import java.util.List;
import java.util.Objects;

/**
 * class to represent a person and their data.
 */
public class Person {
  private String id;
  private String email;
  private List<Person> preferences;
  private Person personWhoProposed;

  /**
   * @param id    - the person's unique id
   * @param email - the email of the person
   */
  public Person(String id, String email) {
    this.id = id;
    this.email = email;
  }

  /**
   * @return - the person's id
   */
  public String getId() {
    return id;
  }


  /**
   * @return - the person who proposed to this person
   */
  public Person getPersonWhoProposed() {
    return personWhoProposed;
  }

  /**
   * @param personWhoProposed - the person who wants to propose to this person
   */
  public void setPersonWhoProposed(Person personWhoProposed) {
    this.personWhoProposed = personWhoProposed;
  }

  /**
   * @return - a list of this persons preferences in sorted from most preferred to least preferred
   */
  public List<Person> getPreferences() {
    return preferences;
  }

  /**
   * @param preferences - a list of this persons preferences in sorted from most preferred
   *                    to least preferred
   */
  public void setPreferences(List<Person> preferences) {
    this.preferences = preferences;
  }

  /**
   * @param toPropose - the person who wants to propose to this person
   * @return - whoever got rejected as a result of this proposal, or null if no one got rejected
   */
  public Person propose(Person toPropose) {
    Person currentProposer = toPropose.getPersonWhoProposed();

    // if the person to propose to did not have anyone else propose to them yet
    if (currentProposer == null || !toPropose.getPreferences().contains(currentProposer)) {
      toPropose.setPersonWhoProposed(this);
      return null;
    }

    // if this person is higher ranked on their list
    if (toPropose.getPreferences().indexOf(this)
        < toPropose.getPreferences().indexOf(currentProposer)) {

      toPropose.setPersonWhoProposed(this);

      return currentProposer;
    }

    toPropose.reject(this);
    return this;
  }

  /**
   * @param toReject - the person to reject
   */
  public void reject(Person toReject) {
    this.preferences.remove(toReject);
    toReject.getPreferences().remove(this);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof Person)) {
      return false;
    }

    Person other = (Person) obj;

    return this.id.equals(other.id);
  }

  @Override
  public String toString() {
    return this.id;
  }
}
