package edu.brown.cs.student.main.match_evaluator;

import edu.brown.cs.student.main.stable_roommates.Person;

public class PersonToScore {
  private Person person;
  private double score;

  public PersonToScore(Person person, double score) {
    this.person = person;
    this.score = score;
  }

  public Person getPerson() {
    return person;
  }

  public double getScore() {
    return score;
  }
}