package edu.brown.cs.student.main.stable_roommates;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GreedyPairs extends PairGenerator {
  public GreedyPairs(Map<Person, List<Person>> personToPreferences) {
    super(personToPreferences);
  }

  @Override
  public Map<Person, Person> getPairs() {
    Map<Person, Person> toReturn = new HashMap<>();
    Set<Person> people = this.personToPreferences.keySet();

    while (!people.isEmpty()) {
      Map<Double, PeoplePair> rankToPair = new HashMap<>();
      for (Person first : people) {
        for (Person second : people) {
          if (first.equals(second)) {
            continue;
          }

          int firstRank = second.getPreferences().indexOf(first);
          int secondRank = first.getPreferences().indexOf(second);

          double avgRank = (firstRank + secondRank) / 2.0;
          rankToPair.put(avgRank, new PeoplePair(first, second));
        }
      }

      double minRank = Collections.min(rankToPair.keySet());
      PeoplePair minPair = rankToPair.get(minRank);

      toReturn.put(minPair.getFirstPerson(), minPair.getSecondPerson());
      toReturn.put(minPair.getSecondPerson(), minPair.getFirstPerson());

      people.remove(minPair.getFirstPerson());
      people.remove(minPair.getSecondPerson());
    }

    return toReturn;
  }
}
