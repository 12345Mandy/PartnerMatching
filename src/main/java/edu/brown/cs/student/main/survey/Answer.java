package edu.brown.cs.student.main.survey;

public class Answer {
  private int id;
  private String text;
  private Question parent;
  private boolean selected;

  public Answer(int id, Question parent) {
    this.id = id;
    this.parent = parent;
  }

  public int getId() {
    return id;
  }

  public Question getParentQuestion() {
    return parent;
  }

  @Override
  public String toString() {
    return "ID: " + this.id + ", Parent: " + this.parent.toString();
  }
}
