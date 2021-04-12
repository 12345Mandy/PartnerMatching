package edu.brown.cs.student.main.stable_roommates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomPairs extends PairGenerator {
  public RandomPairs(Map<Person, List<Person>> personToPreferences) {
    super(personToPreferences);
  }

  public Map<Person, Person> getPairs() {
    List<Person> people = new ArrayList<>(personToPreferences.keySet());

    assert people.size() % 2 == 0;
    Map<Person, Person> toReturn = new HashMap<>();
    while (people.size() != 0) {
      Random rand = new Random();

      int indexOne = rand.nextInt(people.size());
      int indexTwo = rand.nextInt(people.size());

      if (indexOne == indexTwo) {
        continue;
      }

      Person first = people.get(indexOne);
      Person second = people.get(indexTwo);

      people.remove(first);
      people.remove(second);

      toReturn.put(first, second);
      toReturn.put(second, first);
    }

    return toReturn;
  }
}
