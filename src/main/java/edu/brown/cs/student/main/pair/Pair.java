package edu.brown.cs.student.main.pair;

import java.util.Objects;

/**
 * @param <A> - the first thing in the pair
 * @param <B> - the second thing in the pair
 */
public class Pair<A, B> {
  private A first;
  private B second;

  /**
   * @param first  - the first thing in the pair
   * @param second - the second thing in the pair
   */
  public Pair(A first, B second) {
    this.first = first;
    this.second = second;
  }

  /**
   * @return - the first thing in the pair
   */
  public A getFirst() {
    return first;
  }

  /**
   * @return - the second thing in the pair
   */
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

