package edu.brown.cs.student.main.stable_roommates;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * This class implements the stable roommates algorithm.
 */
public class StableRoommates {
  /**
   * This maps a person to a set of their preferences, sorted from highest preference to
   * lowest preference.
   * <p>
   * The value should refer to the same list in memory as the key's preferences list.
   */
  private final LinkedHashMap<Person, List<Person>> personToPreferences;

  public StableRoommates(LinkedHashMap<Person, List<Person>> personToPreferences) {
    this.personToPreferences = personToPreferences;
  }

  /**
   * This method contains the stable roommates logic.
   * <p>
   * The field should result in a mapping from one person to a one element list
   *
   * @return - true if a stable match was found, false otherwise
   */
  public boolean generatePairs() {
//    if (!stablize()) {
//      return false;
//    }

    while (true) {
      List<PeoplePair> currRotation = findRotation();
      removeRotation(currRotation);

      for (Person currPerson : personToPreferences.keySet()) {
        // no stable matching exists
        if (personToPreferences.get(currPerson).isEmpty()) {
          return false;
        }
      }

      if (foundStableMatching(personToPreferences)) {
        return true;
      }
    }
  }

  /**
   * @return - a list of pairs that comprise a rotation
   */
  private List<PeoplePair> findRotation() {
    Set<Person> foundPeople = new HashSet<>();
    Person currP = null;

    for (Person currPerson : personToPreferences.keySet()) {
      if (personToPreferences.get(currPerson).size() >= 2) {
        currP = currPerson;
        break;
      }
    }

    assert currP != null;

    foundPeople.add(currP);

    LinkedList<PeoplePair> pairsToReturn = new LinkedList<>();
    while (true) {
      Person currQ = personToPreferences.get(currP).get(1);
      currP = personToPreferences.get(currQ).get(currQ.getPreferences().size() - 1);

      pairsToReturn.addLast(new PeoplePair(currP, currQ));

      if (foundPeople.contains(currP)) {
        break;
      }

      foundPeople.add(currP);
    }

    // removes pairing that was first seen and adds it to front of list
    PeoplePair p = pairsToReturn.removeLast();
    pairsToReturn.addFirst(p);

    return pairsToReturn;
  }

  private boolean stablize() {
    // TODO: implement stablization of map
    return false;
  }

  /**
   * @param rotation - the rotation to remove from the map
   */
  private void removeRotation(List<PeoplePair> rotation) {
    for (int i = 0; i < rotation.size() - 1; i++) {
      PeoplePair pair = rotation.get(i);
      PeoplePair nextPair = rotation.get(i + 1);

      Person currY = pair.getSecondPerson();
      Person currX = pair.getFirstPerson();

      Person nextY = nextPair.getSecondPerson();

      currY.reject(currX);
      currX.propose(nextY);
    }

    for (int i = 1; i < rotation.size(); i++) {
      PeoplePair pair = rotation.get(i);
      PeoplePair prevPair = rotation.get(i - 1);

      Person currY = pair.getSecondPerson();
      Person prevX = prevPair.getFirstPerson();

      List<Person> yPrefs = currY.getPreferences();

      // every person that is less preferable to x from y's perspective
      List<Person> successorsOfPrevX = yPrefs.subList(yPrefs.indexOf(prevX) + 1, yPrefs.size());

      for (Person successor: successorsOfPrevX) {
        currY.getPreferences().remove(successor);
        successor.getPreferences().remove(currY);
      }
    }
  }

  /**
   * @param preferences - the map of preferences
   * @return - true if each person has one other person in the map
   */
  private boolean foundStableMatching(Map<Person, List<Person>> preferences) {
    for (Person currPerson : preferences.keySet()) {
      if (preferences.get(currPerson).size() != 1) {
        return false;
      }
    }

    return true;
  }
}
