package edu.brown.cs.student.main.stable_roommates;

import java.util.List;
import java.util.Objects;

/**
 * class to represent a person and their data.
 */
public class Person implements Comparable<Person> {
  private int id;
  private String name;
  private double ranking;
  private List<Person> preferences;
  private Person personWhoProposed;

  /**
   * @param id      - the person's unique id
   * @param name    - the person's name
   * @param ranking - the person's ranking relative to another person
   */
  public Person(int id, String name, double ranking) {
    this.id = id;
    this.name = name;
    this.ranking = ranking;
  }

  public Person(int id, String name) {
    this.id = id;
    this.name = name;
  }

  /**
   * @return - the person's name
   */
  public String getName() {
    return name;
  }

  /**
   * @return - the person's id
   */
  public int getId() {
    return id;
  }

  /**
   * @return - the person's ranking relative to another person
   */
  public double getRanking() {
    return ranking;
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
      toPropose.reject(currentProposer);
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
    return Objects.hash(this.id, this.name);
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
  public int compareTo(Person other) {
    return Double.compare(other.getRanking(), this.ranking);
  }
}
