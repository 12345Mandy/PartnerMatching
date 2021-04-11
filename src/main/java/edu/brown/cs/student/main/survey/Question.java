package edu.brown.cs.student.main.survey;

import java.util.ArrayList;
import java.util.List;

public class Question {
  private String text;
  private List<Answer> answers;
  private double score;

  public Question(String text, double score) {
    this.text = text;
    this.score = score;
  }

  public List<Answer> getAnswers() {
    return answers;
  }

  public void setAnswers(List<Answer> answers) {
    this.answers = new ArrayList<>(answers); // defensive copy
  }


  public double getScore() {
    return score;
  }

  @Override
  public String toString() {
    return this.text;
  }
}
