package edu.brown.cs.student.main.survey;

public class Answer {
  private int id;
  private String text;
  private boolean selected;

  public Answer(String text) {
    this.text = text;
  }

  public int getId() {
    return id;
  }

  @Override
  public String toString() {
    return text;
  }
}
