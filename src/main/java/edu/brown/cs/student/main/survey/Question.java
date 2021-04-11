
package edu.brown.cs.student.main.survey;

import java.util.ArrayList;
import java.util.List;

public class Question {
  private int id;
  private String text;
  private List<Answer> answers;
  private double score;

  public Question(int id, double score) {
    this.id = id;
    this.score = score;
  }

  public List<Answer> getAnswers() {
    return answers;
  }

  public void setAnswers(List<Answer> answers) {
    this.answers = new ArrayList<>(answers); // defensive copy
  }

  public int getId() {
    return id;
  }

  public double getScore() {
    return score;
  }

  @Override
  public String toString() {
    return "(ID: " + this.id + ", Value: " + this.score + ")";
  }
}