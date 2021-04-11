package edu.brown.cs.student.main.stable_roommates;

import java.util.List;
import java.util.Map;

public abstract class PairGenerator {
  /**
   * This maps a person to a set of their preferences, sorted from highest preference to
   * lowest preference.
   * <p>
   * The value should refer to the same list in memory as the key's preferences list.
   */
  @SuppressWarnings("checkstyle:VisibilityModifier")
  protected final Map<Person, List<Person>> personToPreferences;

  public PairGenerator(Map<Person, List<Person>> personToPreferences) {
    this.personToPreferences = personToPreferences;
  }

  public abstract Map<Person, Person> getPairs();

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
      if (!map.get(map.get(currPerson)).equals(currPerson) || map.get(currPerson).equals(currPerson)) {
        return false;
      }
    }

    return true;
  }
}
