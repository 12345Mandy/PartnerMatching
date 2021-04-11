package edu.brown.cs.student.main.stable_roommates;

import java.util.Objects;

public class PeoplePair {
  private Person firstPerson;
  private Person secondPerson;

  public PeoplePair(Person firstPerson, Person secondPerson) {
    this.firstPerson = firstPerson;
    this.secondPerson = secondPerson;
  }

  public Person getFirstPerson() {
    return firstPerson;
  }

  public Person getSecondPerson() {
    return secondPerson;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.firstPerson, this.secondPerson);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof PeoplePair)) {
      return false;
    }

    PeoplePair other = (PeoplePair) obj;

    return this.firstPerson.equals(other.getFirstPerson())
        && this.secondPerson.equals(other.getSecondPerson());
  }

  @Override
  public String toString() {
    return "(" + this.firstPerson + ", " + this.secondPerson + ")";
  }
}