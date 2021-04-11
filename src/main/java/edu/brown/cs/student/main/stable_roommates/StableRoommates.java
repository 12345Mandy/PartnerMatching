package edu.brown.cs.student.main.stable_roommates;

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
    if (!stablize()) {
      return false;
    }

    if (foundStableMatching()) {
      return true;
    }

    while (true) {
      List<PeoplePair> currRotation = findRotation();
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
    Person initialP = currP;
    PeoplePair repeated;

    while (true) {
      Person currQ = personToPreferences.get(currP).get(1);
      currP = personToPreferences.get(currQ).get(currQ.getPreferences().size() - 1);

      PeoplePair currPair = new PeoplePair(currP, currQ);
      pairsToReturn.addLast(currPair);

      if (foundPeople.contains(currP)) {
        repeated = currPair;
        break;
      }

      foundPeople.add(currP);
    }

    if (repeated.getFirstPerson().equals(initialP)) {
      // removes pairing that was first seen and adds it to front of list
      PeoplePair p = pairsToReturn.removeLast();
      pairsToReturn.addFirst(p);

      return pairsToReturn;
    } else {
      int indexOfRepeated = pairsToReturn.indexOf(repeated);
      List<PeoplePair> toReturn = pairsToReturn.subList(indexOfRepeated, pairsToReturn.size() - 1);
      return toReturn;
    }


  }

  private boolean stablize() {
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

      List<Person> peopleAfterP =
          new ArrayList<>(qPref.subList(qPref.indexOf(p) + 1, qPref.size()));
      for (Person after : peopleAfterP) {
        q.reject(after);
      }
    }

    return true;
  }

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
  private void removeRotation(List<PeoplePair> rotation) {
    int k = rotation.size();
    for (int i = 0; i < k; i++) {
      PeoplePair pair = rotation.get(i % k);
      PeoplePair nextPair = rotation.get((i + 1) % k);

      Person currY = pair.getSecondPerson();
      Person currX = pair.getFirstPerson();

      Person nextY = nextPair.getSecondPerson();

      currY.reject(currX);
      currX.propose(nextY);
    }

    for (int i = 0; i < k; i++) {
      PeoplePair pair = rotation.get(i % k);
      PeoplePair prevPair = rotation.get(Math.floorMod((i - 1), k));

      Person currY = pair.getSecondPerson();
      Person prevX = prevPair.getFirstPerson();

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