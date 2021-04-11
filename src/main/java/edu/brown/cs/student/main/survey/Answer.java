package edu.brown.cs.student.main.survey;

public class Answer {
  private int id;
  private String text;
  private boolean selected;

  public Answer(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }


  @Override
  public String toString() {
    return "ID: " + this.id;
  }
}
