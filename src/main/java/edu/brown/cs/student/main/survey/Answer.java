package edu.brown.cs.student.main.survey;

/**
 * class to represent an answer.
 */
public class Answer {
  private String text;

  /**
   * @param text - the text of the answer
   */
  public Answer(String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return text;
  }
}
