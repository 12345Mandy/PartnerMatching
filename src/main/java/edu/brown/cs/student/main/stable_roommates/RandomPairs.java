package edu.brown.cs.student.main.stable_roommates;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomPairs {
  private final List<Person> people;

  public RandomPairs(List<Person> people) {
    this.people = people;
  }

  public Map<Person, Person> generateRandomPairs() {
    assert people.size() % 2 == 0;
    Map<Person, Person> toReturn = new HashMap<>();
    while (!people.isEmpty()) {
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
