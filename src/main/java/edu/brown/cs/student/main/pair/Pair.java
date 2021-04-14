package edu.brown.cs.student.main.pair;

import java.util.Objects;

public class Pair<A, B> {
  private A first;
  private B second;

  public Pair(A first, B second) {
    this.first = first;
    this.second = second;
  }

  public A getFirst() {
    return first;
  }

  public B getSecond() {
    return second;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.first, this.second);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof Pair)) {
      return false;
    }

    Pair<A, B> other = (Pair) obj;

    return this.first.equals(other.getFirst())
        && this.second.equals(other.getSecond());
  }

  @Override
  public String toString() {
    return "(" + this.first + ", " + this.second + ")";
  }
}

