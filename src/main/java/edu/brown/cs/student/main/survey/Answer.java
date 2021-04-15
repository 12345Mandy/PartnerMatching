package edu.brown.cs.student.main.survey;

import java.util.Objects;

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

  /**
   * @return - the text of the answer
   */
  public String getText() {
    return text;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof Answer)) {
      return false;
    }

    Answer other = (Answer) obj;

    return this.text.equals(other.getText());
  }

  @Override
  public int hashCode() {
    return Objects.hash(text);
  }

  @Override
  public String toString() {
    return text;
  }
}
