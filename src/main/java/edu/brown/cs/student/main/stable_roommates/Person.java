package edu.brown.cs.student.main.stable_roommates;

import java.util.List;
import java.util.Objects;

/**
 * class to represent a person and their data.
 */
public class Person {
  private int id;
  private List<Person> preferences;
  private Person personWhoProposed;

  public Person(int id) {
    this.id = id;
  }

  /**
   * @return - the person's id
   */
  public int getId() {
    return id;
  }


  public Person getPersonWhoProposed() {
    return personWhoProposed;
  }

  public void setPersonWhoProposed(Person personWhoProposed) {
    this.personWhoProposed = personWhoProposed;
  }

  public List<Person> getPreferences() {
    return preferences;
  }

  public void setPreferences(List<Person> preferences) {
    this.preferences = preferences;
  }

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

    return this.id == other.id;
  }

  @Override
  public String toString() {
    return Integer.toString(this.id);
  }
}