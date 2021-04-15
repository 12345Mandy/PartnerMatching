package edu.brown.cs.student.main.stable_roommates;

import edu.brown.cs.student.main.pair.Pair;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * generates pairs through a greedy algorithm.
 */
public class GreedyPairs extends PairGenerator {
  /**
   * @param personToPreferences - a map of persons to their preferences
   */
  public GreedyPairs(Map<Person, List<Person>> personToPreferences) {
    super(personToPreferences);
  }

  @Override
  public Map<Person, Person> getPairs() {
    Map<Person, Person> toReturn = new HashMap<>();
    Set<Person> people = this.personToPreferences.keySet();

    while (!people.isEmpty()) {
      Map<Double, Pair<Person, Person>> rankToPair = new HashMap<>();
      for (Person first : people) {
        for (Person second : people) {
          if (first.equals(second)) {
            continue;
          }

          int firstRank = second.getPreferences().indexOf(first);
          int secondRank = first.getPreferences().indexOf(second);

          double avgRank = (firstRank + secondRank) / 2.0;
          rankToPair.put(avgRank, new Pair<Person, Person>(first, second));
        }
      }

      double minRank = Collections.min(rankToPair.keySet());
      Pair<Person, Person> minPair = rankToPair.get(minRank);

      toReturn.put(minPair.getFirst(), minPair.getSecond());
      toReturn.put(minPair.getSecond(), minPair.getFirst());

      people.remove(minPair.getFirst());
      people.remove(minPair.getSecond());
    }

    return toReturn;
  }
}
