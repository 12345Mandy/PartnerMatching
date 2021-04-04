import edu.brown.cs.student.main.stable_roommates.Person;
import edu.brown.cs.student.main.stable_roommates.StableRoommates;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TestStableRoommates {
  @Test
  public void wikiTest() {
    Person one = new Person(1, "1");
    Person two = new Person(2, "2");
    Person three = new Person(3, "3");
    Person four = new Person(4, "4");
    Person five = new Person(5, "5");
    Person six = new Person(6, "6");

    List<Person> onePref = new ArrayList<>(Arrays.asList(three, four, two, six, five));
    List<Person> twoPref = new ArrayList<>(Arrays.asList(six, five, four, one, three));
    List<Person> threePref = new ArrayList<>(Arrays.asList(two, four, five, one, six));
    List<Person> fourPref = new ArrayList<>(Arrays.asList(five, two, three, six, one));
    List<Person> fivePref = new ArrayList<>(Arrays.asList(three, one, two, four, six));
    List<Person> sixPref = new ArrayList<>(Arrays.asList(five, one, three, four, two));

    one.setPreferences(onePref);
    two.setPreferences(twoPref);
    three.setPreferences(threePref);
    four.setPreferences(fourPref);
    five.setPreferences(fivePref);
    six.setPreferences(sixPref);

    Map<Person, List<Person>> prefs = new LinkedHashMap<>();
    prefs.put(one, onePref);
    prefs.put(two, twoPref);
    prefs.put(three, threePref);
    prefs.put(four, fourPref);
    prefs.put(five, fivePref);
    prefs.put(six, sixPref);

    StableRoommates sr = new StableRoommates(prefs);
    Map<Person, Person> pairings = sr.getPairs();
    Map<Person, Person> expectedPairs =
        Map.of(one, six, two, four, three, five, four, two, five, three, six, one);
    Assert.assertEquals(pairings, expectedPairs);
  }

  @Test
  public void exampleOneTest() {
    Person charlie = new Person(1, "Charlie");
    Person peter = new Person(2, "Peter");
    Person elise = new Person(3, "Elise");
    Person paul = new Person(4, "Paul");
    Person kelly = new Person(5, "Kelly");
    Person sam = new Person(6, "Sam");

    List<Person> charliePref = new ArrayList<>(Arrays.asList(peter, paul, sam, kelly, elise));
    List<Person> peterPref = new ArrayList<>(Arrays.asList(kelly, elise, sam, paul, charlie));
    List<Person> elisePref = new ArrayList<>(Arrays.asList(peter, sam, kelly, charlie, paul));
    List<Person> paulPref = new ArrayList<>(Arrays.asList(elise, charlie, sam, peter, kelly));
    List<Person> kellyPref = new ArrayList<>(Arrays.asList(peter, charlie, sam, elise, paul));
    List<Person> samPref = new ArrayList<>(Arrays.asList(charlie, paul, kelly, elise, peter));

    charlie.setPreferences(charliePref);
    peter.setPreferences(peterPref);
    elise.setPreferences(elisePref);
    paul.setPreferences(paulPref);
    kelly.setPreferences(kellyPref);
    sam.setPreferences(samPref);

    Map<Person, List<Person>> prefs = new LinkedHashMap<>();
    prefs.put(charlie, charliePref);
    prefs.put(peter, peterPref);
    prefs.put(elise, elisePref);
    prefs.put(paul, paulPref);
    prefs.put(kelly, kellyPref);
    prefs.put(sam, samPref);

    StableRoommates sr = new StableRoommates(prefs);
    Map<Person, Person> pairings = sr.getPairs();
    Map<Person, Person> expectedPairs =
        Map.of(
            paul, elise,
            charlie, sam,
            kelly, peter,
            elise, paul,
            sam, charlie,
            peter, kelly);
    Assert.assertEquals(pairings, expectedPairs);
  }

  @Test
  public void exampleTwoTest() {
    Person one = new Person(1, "one");
    Person two = new Person(2, "two");
    Person three = new Person(3, "three");
    Person D = new Person(4, "D");
    Person E = new Person(5, "E");
    Person F = new Person(6, "F");

    List<Person> aPref = new ArrayList<>(Arrays.asList(two, D, F, three, E));
    List<Person> bPref = new ArrayList<>(Arrays.asList(D, E, F, one, three));
    List<Person> cPref = new ArrayList<>(Arrays.asList(D, E, F, one, two));
    List<Person> dPref = new ArrayList<>(Arrays.asList(F, three, one, E, two));
    List<Person> ePref = new ArrayList<>(Arrays.asList(F, three, D, two, one));
    List<Person> fPref = new ArrayList<>(Arrays.asList(one, two, D, three, E));

    one.setPreferences(aPref);
    two.setPreferences(bPref);
    three.setPreferences(cPref);
    D.setPreferences(dPref);
    E.setPreferences(ePref);
    F.setPreferences(fPref);

    Map<Person, List<Person>> prefs = new LinkedHashMap<>();
    prefs.put(one, aPref);
    prefs.put(two, bPref);
    prefs.put(three, cPref);
    prefs.put(D, dPref);
    prefs.put(E, ePref);
    prefs.put(F, fPref);

    StableRoommates sr = new StableRoommates(prefs);
    Map<Person, Person> pairings = sr.getPairs();
    Map<Person, Person> expectedPairs =
        Map.of(
            D, three,
            one, F,
            E, two,
            three, D,
            F, one,
            two, E);
    Assert.assertEquals(pairings, expectedPairs);
  }

  @Test
  public void morePeopleTest() {
    Person one = new Person(1, "1");
    Person two = new Person(2, "2");
    Person three = new Person(3, "3");
    Person four = new Person(4, "4");
    Person five = new Person(5, "5");
    Person six = new Person(6, "6");
    Person seven = new Person(7, "7");
    Person eight = new Person(8, "8");
    Person nine = new Person(9, "9");
    Person ten = new Person(10, "10");

    List<Person> onePref =
        new ArrayList<>(Arrays.asList(eight, two, nine, three, six, four, five, seven, ten));
    List<Person> twoPref =
        new ArrayList<>(Arrays.asList(four, three, eight, nine, five, one, ten, six, seven));
    List<Person> threePref =
        new ArrayList<>(Arrays.asList(five, six, eight, two, one, seven, ten, four, nine));
    List<Person> fourPref =
        new ArrayList<>(Arrays.asList(ten, seven, nine, three, one, six, two, five, eight));
    List<Person> fivePref =
        new ArrayList<>(Arrays.asList(seven, four, ten, eight, two, six, three, one, nine));
    List<Person> sixPref =
        new ArrayList<>(Arrays.asList(two, eight, seven, three, four, ten, one, five, nine));
    List<Person> sevenPref =
        new ArrayList<>(Arrays.asList(two, one, eight, three, five, ten, four, six, nine));
    List<Person> eightPref =
        new ArrayList<>(Arrays.asList(ten, four, two, five, six, seven, one, three, nine));
    List<Person> ninePref =
        new ArrayList<>(Arrays.asList(six, seven, two, five, ten, three, four, eight, one));
    List<Person> tenPref =
        new ArrayList<>(Arrays.asList(three, one, six, five, two, nine, eight, four, seven));

    one.setPreferences(onePref);
    two.setPreferences(twoPref);
    three.setPreferences(threePref);
    four.setPreferences(fourPref);
    five.setPreferences(fivePref);
    six.setPreferences(sixPref);
    seven.setPreferences(sevenPref);
    eight.setPreferences(eightPref);
    nine.setPreferences(ninePref);
    ten.setPreferences(tenPref);

    Map<Person, List<Person>> prefs = new LinkedHashMap<>();
    prefs.put(one, onePref);
    prefs.put(two, twoPref);
    prefs.put(three, threePref);
    prefs.put(four, fourPref);
    prefs.put(five, fivePref);
    prefs.put(six, sixPref);
    prefs.put(seven, sevenPref);
    prefs.put(eight, eightPref);
    prefs.put(nine, ninePref);
    prefs.put(ten, tenPref);


    StableRoommates sr = new StableRoommates(prefs);
    Map<Person, Person> pairings = sr.getPairs();
    Map<Person, Person> expectedPairs =
        Map.of(one, seven, two, eight, three, six, four, nine, five, ten, six, three, seven, one,
            eight, two, nine, four, ten, five);
    Assert.assertEquals(pairings, expectedPairs);
  }

  @Test
  public void unStableOne() {
    Person A = new Person(1, "A");
    Person B = new Person(2, "B");
    Person C = new Person(3, "C");
    Person D = new Person(4, "D");

    List<Person> aPref = new ArrayList<>(Arrays.asList(B, C, D));
    List<Person> bPref = new ArrayList<>(Arrays.asList(C, A, D));
    List<Person> cPref = new ArrayList<>(Arrays.asList(A, B, D));
    List<Person> dPref = new ArrayList<>(Arrays.asList(A, B, C));

    A.setPreferences(aPref);
    B.setPreferences(bPref);
    C.setPreferences(cPref);
    D.setPreferences(dPref);

    Map<Person, List<Person>> prefs = new LinkedHashMap<>();
    prefs.put(A, aPref);
    prefs.put(B, bPref);
    prefs.put(C, cPref);
    prefs.put(D, dPref);

    StableRoommates sr = new StableRoommates(prefs);
    Map<Person, Person> pairings = sr.getPairs();
//    Assert.assertEquals(pairings, new HashMap<>());
  }

  @Test
  public void unStableTwo() {
    Person charlie = new Person(1, "Charlie");
    Person peter = new Person(2, "Peter");
    Person elise = new Person(3, "Elise");
    Person paul = new Person(4, "Paul");

    List<Person> charliePref = new ArrayList<>(Arrays.asList(peter, paul, elise));
    List<Person> peterPref = new ArrayList<>(Arrays.asList(paul, charlie, elise));
    List<Person> elisePref = new ArrayList<>(Arrays.asList(peter, charlie, paul));
    List<Person> paulPref = new ArrayList<>(Arrays.asList(charlie, peter, elise));

    charlie.setPreferences(charliePref);
    peter.setPreferences(peterPref);
    elise.setPreferences(elisePref);
    paul.setPreferences(paulPref);

    Map<Person, List<Person>> prefs = new LinkedHashMap<>();
    prefs.put(charlie, charliePref);
    prefs.put(peter, peterPref);
    prefs.put(elise, elisePref);
    prefs.put(paul, paulPref);

    StableRoommates sr = new StableRoommates(prefs);
    Map<Person, Person> pairings = sr.getPairs();
    int x = 0;
  }

  @Test
  public void unStableThree() {
    Person A = new Person(1, "A");
    Person B = new Person(2, "B");
    Person C = new Person(3, "C");
    Person M = new Person(4, "M");

    List<Person> aPref = new ArrayList<>(Arrays.asList(B, C, M));
    List<Person> bPref = new ArrayList<>(Arrays.asList(C, A, M));
    List<Person> cPref = new ArrayList<>(Arrays.asList(A, B, M));
    List<Person> mPref = new ArrayList<>(Arrays.asList(A, B, C));

    A.setPreferences(aPref);
    B.setPreferences(bPref);
    C.setPreferences(cPref);
    M.setPreferences(mPref);

    Map<Person, List<Person>> prefs = new LinkedHashMap<>();
    prefs.put(A, aPref);
    prefs.put(B, bPref);
    prefs.put(C, cPref);
    prefs.put(M, mPref);

    StableRoommates sr = new StableRoommates(prefs);
    Map<Person, Person> pairings = sr.getPairs();
    int x = 0;
//    Assert.assertEquals(pairings, new HashMap<>());
  }
}
