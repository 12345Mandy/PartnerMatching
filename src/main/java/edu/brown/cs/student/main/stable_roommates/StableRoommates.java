package edu.brown.cs.student.main.stable_roommates;

import edu.brown.cs.student.main.pair.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * This class implements the stable roommates algorithm.
 */
public class StableRoommates extends PairGenerator {

  /**
   * @param personToPreferences - a map of persons to their preferences
   */
  public StableRoommates(Map<Person, List<Person>> personToPreferences) {
    super(personToPreferences);
  }

  /**
   * @return - a mapping from person to person that represents pairs
   */
  @Override
  public Map<Person, Person> getPairs() {
    assert isValidInput();
    if (generatePairs()) {
      Map<Person, Person> toReturn = new HashMap<>();
      for (Person currPerson : personToPreferences.keySet()) {
        toReturn.put(currPerson, personToPreferences.get(currPerson).get(0));
      }

      return toReturn;
    }

    Map<Person, Person> toReturn = new HashMap<>();
    for (Person currPerson : personToPreferences.keySet()) {
      if (personToPreferences.get(currPerson).isEmpty()) {
        toReturn.put(currPerson, null);
      } else {
        toReturn.put(currPerson, personToPreferences.get(currPerson).get(0));
      }
    }

    return toReturn;
  }

  /**
   * This method contains the stable roommates logic.
   * <p>
   * The field should result in a mapping from one person to a one element list
   *
   * @return - true if a stable match was found, false otherwise
   */
  private boolean generatePairs() {
    if (!stabilize()) {
      return false;
    }

    if (foundStableMatching()) {
      return true;
    }

    // continue iterating until a matching is found or isn't possible
    while (true) {
      List<Pair<Person, Person>> currRotation = findRotation();
      removeRotation(currRotation);


      for (Person currPerson : personToPreferences.keySet()) {
        // no stable matching exists
        if (personToPreferences.get(currPerson).isEmpty()) {
          return false;
        }
      }

      if (foundStableMatching()) {
        return true;
      }
    }
  }

  /**
   * @return - a list of pairs that comprise a rotation
   */
  private List<Pair<Person, Person>> findRotation() {
    // follows algorithm from wiki article
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

    LinkedList<Pair<Person, Person>> pairsToReturn = new LinkedList<>();
    Person initialP = currP;
    Pair<Person, Person> repeated;

    while (true) {
      Person currQ = personToPreferences.get(currP).get(1);
      currP = personToPreferences.get(currQ).get(currQ.getPreferences().size() - 1);

      Pair<Person, Person> currPair = new Pair<Person, Person>(currP, currQ);
      pairsToReturn.addLast(currPair);

      if (foundPeople.contains(currP)) {
        repeated = currPair;
        break;
      }

      foundPeople.add(currP);
    }

    if (repeated.getFirst().equals(initialP)) {
      // removes pairing that was first seen and adds it to front of list
      Pair<Person, Person> p = pairsToReturn.removeLast();
      pairsToReturn.addFirst(p);

      return pairsToReturn;
    } else {
      int indexOfRepeated = pairsToReturn.indexOf(repeated);
      return pairsToReturn.subList(indexOfRepeated, pairsToReturn.size() - 1);
    }


  }

  /**
   * @return - true if the stabilization was successful, false if it failed
   */
  private boolean stabilize() {
    // follows algorithm listed in wiki article
    for (Person currPerson : personToPreferences.keySet()) {
      Person rejected = currPerson.propose(currPerson.getPreferences().get(0));
      while (rejected != null) {
        if (rejected.getPreferences().isEmpty()) {
          return false;
        }
        rejected = rejected.propose(rejected.getPreferences().get(0));
      }
    }

    if (!checkIfStableMatchingIsPossible()) {
      return false;
    }

    for (Person q : personToPreferences.keySet()) {
      Person p = q.getPersonWhoProposed();
      List<Person> qPref = q.getPreferences();

      // every person that is less preferable to p from q's perspective
      List<Person> peopleAfterP =
          new ArrayList<>(qPref.subList(qPref.indexOf(p) + 1, qPref.size()));
      for (Person after : peopleAfterP) {
        q.reject(after);
      }
    }

    return true;
  }

  /**
   * @return - true if a stable matching exists, false otherwise
   */
  private boolean checkIfStableMatchingIsPossible() {
    for (Person currPerson : personToPreferences.keySet()) {
      if (currPerson.getPersonWhoProposed() == null) {
        return false;
      }
    }

    return true;
  }

  /**
   * @param rotation - the rotation to remove from the map
   */
  private void removeRotation(List<Pair<Person, Person>> rotation) {
    // follows algorithm listed in wiki article
    int k = rotation.size();
    for (int i = 0; i < k; i++) {
      Pair<Person, Person> pair = rotation.get(i % k);
      Pair<Person, Person> nextPair = rotation.get((i + 1) % k);

      Person currY = pair.getSecond();
      Person currX = pair.getFirst();

      Person nextY = nextPair.getSecond();

      currY.reject(currX);
      currX.propose(nextY);
    }

    for (int i = 0; i < k; i++) {
      Pair<Person, Person> pair = rotation.get(i % k);
      Pair<Person, Person> prevPair = rotation.get(Math.floorMod((i - 1), k));

      Person currY = pair.getSecond();
      Person prevX = prevPair.getFirst();

      List<Person> yPrefs = currY.getPreferences();

      // every person that is less preferable to x from y's perspective
      List<Person> successorsOfPrevX =
          new LinkedList<>(yPrefs.subList(yPrefs.indexOf(prevX) + 1, yPrefs.size()));

      for (Person successor : successorsOfPrevX) {
        currY.reject(successor);
      }
    }
  }

  /**
   * @return - true if each person has one other person in the map
   */
  private boolean foundStableMatching() {
    for (Person currPerson : this.personToPreferences.keySet()) {
      if (this.personToPreferences.get(currPerson).size() != 1) {
        return false;
      }
    }

    return true;
  }

  /**
   * @return - true if the input is valid, false otherwise
   */
  private boolean isValidInput() {
    int numPeople = this.personToPreferences.size();

    // go through every possible person
    for (Person currPerson : this.personToPreferences.keySet()) {
      // each person should have everybody else ranked
      if (currPerson.getPreferences().size() != numPeople - 1) {
        return false;
      }

      Set<Person> peopleInPrefList = new HashSet<>();
      // each person should only be in the preferences list once and a person shouldn't ranked
      // themselves
      for (Person pref : currPerson.getPreferences()) {
        if (peopleInPrefList.contains(pref) || pref.equals(currPerson)) {
          return false;
        }

        peopleInPrefList.add(pref);
      }
    }

    return true;
  }
}
