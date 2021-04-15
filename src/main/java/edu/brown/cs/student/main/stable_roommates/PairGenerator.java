package edu.brown.cs.student.main.stable_roommates;

import java.util.List;
import java.util.Map;

/**
 * general class for making pairs.
 */
public abstract class PairGenerator {
  /**
   * This maps a person to a set of their preferences, sorted from highest preference to
   * lowest preference.
   * <p>
   * The value should refer to the same list in memory as the key's preferences list.
   */
  protected final Map<Person, List<Person>> personToPreferences;

  /**
   * @param personToPreferences - a map of persons to their preferences
   */
  public PairGenerator(Map<Person, List<Person>> personToPreferences) {
    this.personToPreferences = personToPreferences;
  }

  /**
   * @return - a map that represents pairs
   */
  public abstract Map<Person, Person> getPairs();

  /**
   * @param map - a map that represents pairs
   * @return - true if the pairs are stable, false otherwise
   */
  public boolean isStable(Map<Person, Person> map) {
    if (map == null) {
      return false;
    }

    // if someone doesn't have a partner
    for (Person currPerson : map.keySet()) {
      if (map.get(currPerson) == null) {
        return false;
      }
    }

    // someone's partner's partner should be themselves and their partner should not be themselves
    for (Person currPerson : map.keySet()) {
      if (!map.get(map.get(currPerson)).equals(currPerson)
          || map.get(currPerson).equals(currPerson)) {
        return false;
      }
    }

    return true;
  }
}
