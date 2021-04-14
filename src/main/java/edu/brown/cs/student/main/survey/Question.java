package edu.brown.cs.student.main.survey;

import java.util.ArrayList;
import java.util.List;

/**
 * a class to represent a question.
 */
public class Question {
  private String text;
  private List<Answer> answers;
  private double score;

  /**
   * @param text  - the text of the question
   * @param score - the significance of this question
   */
  public Question(String text, double score) {
    this.text = text;
    this.score = score;
  }

  /**
   * @return - a list of the answer choicees for this question
   */
  public List<Answer> getAnswers() {
    return answers;
  }

  /**
   * @param answers - a list of the answer choices for this question
   */
  public void setAnswers(List<Answer> answers) {
    this.answers = new ArrayList<>(answers); // defensive copy
  }


  /**
   * @return - the significance of this question
   */
  public double getScore() {
    return score;
  }

  @Override
  public String toString() {
    return this.text;
  }
}
