import edu.brown.cs.student.main.stable_roommates.PeoplePair;
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
  public void twelvePeopleOneTest() {
    Person A = new Person(1, "A");
    Person B = new Person(2, "B");
    Person C = new Person(3, "C");
    Person D = new Person(4, "D");
    Person E = new Person(5, "E");
    Person F = new Person(6, "F");
    Person G = new Person(6, "G");
    Person H = new Person(6, "H");
    Person I = new Person(1, "I");
    Person J = new Person(2, "J");
    Person K = new Person(3, "K");
    Person L = new Person(4, "L");


    List<Person> aPref =
        new ArrayList<>(Arrays.asList(J, B, I, E, C, K, G, F, L, H, D));
    List<Person> bPref =
        new ArrayList<>(Arrays.asList(D, E, I, G, J, A, F, L, C, H, K));
    List<Person> cPref =
        new ArrayList<>(Arrays.asList(D, G, J, F, L, I, A, K, B, E, H));
    List<Person> dPref =
        new ArrayList<>(Arrays.asList(B, L, A, J, H, F, K, E, G, C, I));
    List<Person> ePref =
        new ArrayList<>(Arrays.asList(G, A, H, D, K, I, C, F, L, B, J));
    List<Person> fPref =
        new ArrayList<>(Arrays.asList(E, J, B, I, D, A, G, L, H, K, C));
    List<Person> gPref =
        new ArrayList<>(Arrays.asList(I, A, L, B, D, J, E, F, H, C, K));
    List<Person> hPref =
        new ArrayList<>(Arrays.asList(A, L, G, C, F, B, I, D, E, J, K));
    List<Person> iPref =
        new ArrayList<>(Arrays.asList(E, A, K, L, D, H, F, C, G, J, B));
    List<Person> jPref =
        new ArrayList<>(Arrays.asList(I, H, B, K, E, L, F, C, A, D, G));
    List<Person> kPref =
        new ArrayList<>(Arrays.asList(A, E, F, L, B, C, G, J, I, H, D));
    List<Person> lPref =
        new ArrayList<>(Arrays.asList(G, E, K, A, I, B, C, F, D, J, H));

    A.setPreferences(aPref);
    B.setPreferences(bPref);
    C.setPreferences(cPref);
    D.setPreferences(dPref);
    E.setPreferences(ePref);
    F.setPreferences(fPref);
    G.setPreferences(gPref);
    H.setPreferences(hPref);
    I.setPreferences(iPref);
    J.setPreferences(jPref);
    K.setPreferences(kPref);
    L.setPreferences(lPref);


    Map<Person, List<Person>> prefs = new HashMap<>();
    prefs.put(A, aPref);
    prefs.put(B, bPref);
    prefs.put(C, cPref);
    prefs.put(D, dPref);
    prefs.put(E, ePref);
    prefs.put(F, fPref);
    prefs.put(G, gPref);
    prefs.put(H, hPref);
    prefs.put(I, iPref);
    prefs.put(J, jPref);
    prefs.put(K, kPref);
    prefs.put(L, lPref);

    StableRoommates sr = new StableRoommates(prefs);
    Map<Person, Person> pairings = sr.getPairs();

    Assert.assertTrue(isStable(pairings));
  }

  @Test
  public void twelvePeopleTwoTest() {
    Person A = new Person(1, "A");
    Person B = new Person(2, "B");
    Person C = new Person(3, "C");
    Person D = new Person(4, "D");
    Person E = new Person(5, "E");
    Person F = new Person(6, "F");
    Person G = new Person(6, "G");
    Person H = new Person(6, "H");
    Person I = new Person(1, "I");
    Person J = new Person(2, "J");
    Person K = new Person(3, "K");
    Person L = new Person(4, "L");


    List<Person> aPref =
        new ArrayList<>(Arrays.asList(K, I, D, G, H, L, J, B, F, C, E));
    List<Person> bPref =
        new ArrayList<>(Arrays.asList(D, A, I, H, J, F, C, E, G, K, L));
    List<Person> cPref =
        new ArrayList<>(Arrays.asList(D, J, B, E, F, K, H, A, I, L, G));
    List<Person> dPref =
        new ArrayList<>(Arrays.asList(C, J, H, B, K, I, L, A, F, E, G));
    List<Person> ePref =
        new ArrayList<>(Arrays.asList(H, D, J, C, B, L, A, F, G, I, K));
    List<Person> fPref =
        new ArrayList<>(Arrays.asList(D, E, C, J, I, G, H, B, A, L, K));
    List<Person> gPref =
        new ArrayList<>(Arrays.asList(B, A, J, K, C, E, L, H, F, I, D));
    List<Person> hPref =
        new ArrayList<>(Arrays.asList(A, D, F, J, G, K, I, B, C, L, E));
    List<Person> iPref =
        new ArrayList<>(Arrays.asList(K, C, A, H, D, G, L, J, F, E, B));
    List<Person> jPref =
        new ArrayList<>(Arrays.asList(G, A, I, B, D, C, E, H, L, F, K));
    List<Person> kPref =
        new ArrayList<>(Arrays.asList(J, F, D, L, E, C, H, G, A, B, I));
    List<Person> lPref =
        new ArrayList<>(Arrays.asList(D, G, H, I, J, B, F, A, C, E, K));

    A.setPreferences(aPref);
    B.setPreferences(bPref);
    C.setPreferences(cPref);
    D.setPreferences(dPref);
    E.setPreferences(ePref);
    F.setPreferences(fPref);
    G.setPreferences(gPref);
    H.setPreferences(hPref);
    I.setPreferences(iPref);
    J.setPreferences(jPref);
    K.setPreferences(kPref);
    L.setPreferences(lPref);


    Map<Person, List<Person>> prefs = new HashMap<>();
    prefs.put(A, aPref);
    prefs.put(B, bPref);
    prefs.put(C, cPref);
    prefs.put(D, dPref);
    prefs.put(E, ePref);
    prefs.put(F, fPref);
    prefs.put(G, gPref);
    prefs.put(H, hPref);
    prefs.put(I, iPref);
    prefs.put(J, jPref);
    prefs.put(K, kPref);
    prefs.put(L, lPref);

    StableRoommates sr = new StableRoommates(prefs);
    Map<Person, Person> pairings = sr.getPairs();

    Assert.assertTrue(isStable(pairings));
  }

  @Test
  public void eightPeopleTest() {
    Person A = new Person(1, "A");
    Person B = new Person(2, "B");
    Person C = new Person(3, "C");
    Person D = new Person(4, "D");
    Person E = new Person(5, "E");
    Person F = new Person(6, "F");
    Person G = new Person(6, "G");
    Person H = new Person(6, "H");

    List<Person> aPref = new ArrayList<>(Arrays.asList(B, E, F, D, H, G, C));
    List<Person> bPref = new ArrayList<>(Arrays.asList(G, D, F, E, H, A, C));
    List<Person> cPref = new ArrayList<>(Arrays.asList(D, B, G, A, F, E, H));
    List<Person> dPref = new ArrayList<>(Arrays.asList(G, F, B, E, H, C, A));
    List<Person> ePref = new ArrayList<>(Arrays.asList(B, G, D, C, A, F, H));
    List<Person> fPref = new ArrayList<>(Arrays.asList(H, G, E, B, A, D, C));
    List<Person> gPref = new ArrayList<>(Arrays.asList(B, A, D, C, H, F, E));
    List<Person> hPref = new ArrayList<>(Arrays.asList(A, G, C, F, D, E, B));

    A.setPreferences(aPref);
    B.setPreferences(bPref);
    C.setPreferences(cPref);
    D.setPreferences(dPref);
    E.setPreferences(ePref);
    F.setPreferences(fPref);
    G.setPreferences(gPref);
    H.setPreferences(hPref);


    Map<Person, List<Person>> prefs = new HashMap<>();
    prefs.put(A, aPref);
    prefs.put(B, bPref);
    prefs.put(C, cPref);
    prefs.put(D, dPref);
    prefs.put(E, ePref);
    prefs.put(F, fPref);
    prefs.put(G, gPref);
    prefs.put(H, hPref);

    StableRoommates sr = new StableRoommates(prefs);
    Map<Person, Person> pairings = sr.getPairs();

    Assert.assertTrue(isStable(pairings));
  }

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
    Assert.assertTrue(isStable(pairings));
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
    Assert.assertTrue(isStable(pairings));
  }

  @Test
  public void exampleTwoTest() {
    Person A = new Person(1, "A");
    Person B = new Person(2, "B");
    Person C = new Person(3, "C");
    Person D = new Person(4, "D");
    Person E = new Person(5, "E");
    Person F = new Person(6, "F");

    List<Person> aPref = new ArrayList<>(Arrays.asList(B, D, F, C, E));
    List<Person> bPref = new ArrayList<>(Arrays.asList(D, E, F, A, C));
    List<Person> cPref = new ArrayList<>(Arrays.asList(D, E, F, A, B));
    List<Person> dPref = new ArrayList<>(Arrays.asList(F, C, A, E, B));
    List<Person> ePref = new ArrayList<>(Arrays.asList(F, C, D, B, A));
    List<Person> fPref = new ArrayList<>(Arrays.asList(A, B, D, C, E));

    A.setPreferences(aPref);
    B.setPreferences(bPref);
    C.setPreferences(cPref);
    D.setPreferences(dPref);
    E.setPreferences(ePref);
    F.setPreferences(fPref);

    Map<Person, List<Person>> prefs = new LinkedHashMap<>();
    prefs.put(A, aPref);
    prefs.put(B, bPref);
    prefs.put(C, cPref);
    prefs.put(D, dPref);
    prefs.put(E, ePref);
    prefs.put(F, fPref);

    StableRoommates sr = new StableRoommates(prefs);
    Map<Person, Person> pairings = sr.getPairs();
    Map<Person, Person> expectedPairs =
        Map.of(
            D, C,
            A, F,
            E, B,
            C, D,
            F, A,
            B, E);
    Assert.assertEquals(pairings, expectedPairs);
    Assert.assertTrue(isStable(pairings));
  }

  @Test
  public void tenPeopleTestOne() {
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
    Assert.assertTrue(isStable(pairings));
  }

  @Test
  public void tenPeopleTestTwo() {
    Person one = new Person(1, "1");
    Person two = new Person(2, "2");
    Person three = new Person(3, "3");
    Person four = new Person(4, "4");
    Person five = new Person(5, "5");
    Person six = new Person(6, "6");
    Person seven = new Person(7, "7");
    Person eight = new Person(8, "8");

    List<Person> onePref =
        new ArrayList<>(Arrays.asList(two, five, four, six, seven, eight, three));
    List<Person> twoPref =
        new ArrayList<>(Arrays.asList(three, six, one, seven, eight, five, four));
    List<Person> threePref =
        new ArrayList<>(Arrays.asList(four, seven, two, eight, five, six, one));
    List<Person> fourPref =
        new ArrayList<>(Arrays.asList(one, eight, three, five, six, seven, two));
    List<Person> fivePref =
        new ArrayList<>(Arrays.asList(six, one, eight, two, three, four, seven));
    List<Person> sixPref =
        new ArrayList<>(Arrays.asList(seven, two, five, three, four, one, eight));
    List<Person> sevenPref =
        new ArrayList<>(Arrays.asList(eight, three, six, four, one, two, five));
    List<Person> eightPref =
        new ArrayList<>(Arrays.asList(five, four, seven, one, two, three, six));

    one.setPreferences(onePref);
    two.setPreferences(twoPref);
    three.setPreferences(threePref);
    four.setPreferences(fourPref);
    five.setPreferences(fivePref);
    six.setPreferences(sixPref);
    seven.setPreferences(sevenPref);
    eight.setPreferences(eightPref);

    Map<Person, List<Person>> prefs = new LinkedHashMap<>();
    prefs.put(one, onePref);
    prefs.put(two, twoPref);
    prefs.put(three, threePref);
    prefs.put(four, fourPref);
    prefs.put(five, fivePref);
    prefs.put(six, sixPref);
    prefs.put(seven, sevenPref);
    prefs.put(eight, eightPref);


    StableRoommates sr = new StableRoommates(prefs);
    Map<Person, Person> pairings = sr.getPairs();
    Map<Person, Person> expectedPairs =
        Map.of(one, four, two, three, three, two, four, one, five, six, six, five, seven, eight,
            eight, seven);
    Assert.assertEquals(pairings, expectedPairs);
    Assert.assertTrue(isStable(pairings));
  }

  @Test
  public void twentyPeopleUnstableTest() {
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
    Person eleven = new Person(11, "11");
    Person twelve = new Person(12, "12");
    Person thirteen = new Person(13, "13");
    Person fourteen = new Person(14, "14");
    Person fifteen = new Person(15, "15");
    Person sixteen = new Person(16, "16");
    Person seventeen = new Person(17, "17");
    Person eighteen = new Person(18, "18");
    Person nineteen = new Person(19, "19");
    Person twenty = new Person(20, "20");

    List<Person> onePref =
        new ArrayList<>(Arrays
            .asList(eleven, seventeen, ten, six, sixteen, twelve, nine, fourteen, fifteen, seven,
                four, nineteen, three, eighteen, thirteen, eight, two, twenty, five));

    List<Person> twoPref =
        new ArrayList<>(Arrays
            .asList(eighteen, twenty, seven, one, eleven, three, fourteen, fifteen, thirteen,
                sixteen, eight, nine, nineteen, seventeen, five, ten, twelve, six, four));

    List<Person> threePref =
        new ArrayList<>(Arrays
            .asList(five, eight, fifteen, four, two, ten, twenty, nine, nineteen, twelve, fourteen,
                eighteen, seven, seventeen, six, eleven, sixteen, thirteen, one));

    List<Person> fourPref =
        new ArrayList<>(Arrays
            .asList(nineteen, six, sixteen, eleven, fourteen, twelve, thirteen, two, one, nine,
                ten, three, eight, seven, five, seventeen, eighteen, twenty, fifteen));

    List<Person> fivePref =
        new ArrayList<>(Arrays.asList(fourteen, sixteen, seven, nine, twenty,
            eleven, three, two, one, eighteen, six, seventeen, eight, thirteen, nineteen, fifteen,
            ten, twelve, four));

    List<Person> sixPref =
        new ArrayList<>(Arrays.asList(eight, five, seventeen, nineteen,
            twelve, four, thirteen, sixteen, eleven, one, fifteen, fourteen, nine, two, eighteen,
            twenty, seven, ten, three));

    List<Person> sevenPref =
        new ArrayList<>(Arrays.asList(one, nineteen, five, three, eight, six, ten,
            thirteen, nine, seventeen, sixteen, twenty, two, twelve, fourteen, fifteen, eighteen,
            eleven, four));

    List<Person> eightPref =
        new ArrayList<>(Arrays.asList(thirteen, seventeen, eleven, four, ten,
            eighteen, one, twelve, fifteen, sixteen, three, six, twenty, five, two, nineteen, nine,
            fourteen, seven));

    List<Person> ninePref =
        new ArrayList<>(Arrays.asList(two, eight, sixteen, one, six, five, ten, four,
            eighteen, eleven, seven, three, thirteen, fifteen, fourteen, seventeen, twenty,
            nineteen, twelve));

    List<Person> tenPref =
        new ArrayList<>(Arrays.asList(twelve, thirteen, sixteen, six, seventeen, fourteen,
            one, two, eighteen, eight, nine, seven, three, five, twenty, eleven, nineteen, four,
            fifteen));

    List<Person> elevenPref =
        new ArrayList<>(Arrays.asList(ten, fifteen, fourteen, twelve, five,
            six, thirteen, one, eight, two, eighteen, nineteen, sixteen, three, seventeen, four,
            nine, seven, twenty));

    List<Person> twelvePref =
        new ArrayList<>(Arrays.asList(eighteen, seventeen, fifteen, three,
            nine, one, ten, eight, seven, eleven, five, two, fourteen, nineteen, four,
            six, twenty, thirteen, sixteen));

    List<Person> thirteenPref =
        new ArrayList<>(Arrays.asList(eight, six, five, four, eighteen,
            three, fourteen, nine, ten, eleven, two, sixteen, nineteen, seventeen, fifteen, twelve,
            seven, one, twenty));

    List<Person> fourteenPref =
        new ArrayList<>(Arrays
            .asList(twelve, one, ten, eleven, eight, eighteen, three, nine, thirteen, six, five,
                nineteen, seven, fifteen, twenty, sixteen, two, seventeen, four));

    List<Person> fifteenPref =
        new ArrayList<>(Arrays.asList(eighteen, two, sixteen, five, six, twenty,
            nine, seven, one, three, eight, thirteen, eleven, four, ten, seventeen, twelve,
            fourteen, nineteen));

    List<Person> sixteenPref =
        new ArrayList<>(Arrays.asList(twenty, fifteen, thirteen, two, ten,
            three, one, seventeen, twelve, four, five, eighteen, six, nine, fourteen, seven,
            nineteen, eleven, eight));

    List<Person> seventeenPref =
        new ArrayList<>(Arrays.asList(three, nine, seven, four, six, twelve, two, fourteen,
            five, fifteen, ten, one, thirteen, nineteen, eight, eighteen, twenty, sixteen, eleven));

    List<Person> eighteenPref =
        new ArrayList<>(Arrays.asList(ten, five, one, eight, sixteen, twenty, three,
            six, four, twelve, seventeen, nine, thirteen, nineteen, two, eleven, seven, fifteen,
            fourteen));

    List<Person> nineteenPref =
        new ArrayList<>(Arrays.asList(six, ten, eleven, one, seventeen, two,
            twenty, nine, four, three, seven, sixteen, fifteen, twelve, eight, thirteen,
            five, fourteen, eighteen));

    List<Person> twentyPref =
        new ArrayList<>(Arrays.asList(seventeen, fifteen, four,
            fourteen, eighteen, five, nine, eleven, eight,
            nineteen, two, seven, twelve, thirteen, three, six, one, sixteen, ten));

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

    eleven.setPreferences(elevenPref);
    twelve.setPreferences(twelvePref);
    thirteen.setPreferences(thirteenPref);
    fourteen.setPreferences(fourteenPref);
    fifteen.setPreferences(fifteenPref);
    sixteen.setPreferences(sixteenPref);
    seventeen.setPreferences(seventeenPref);
    eighteen.setPreferences(eighteenPref);
    nineteen.setPreferences(nineteenPref);
    twenty.setPreferences(twentyPref);

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

    prefs.put(eleven, elevenPref);
    prefs.put(twelve, twelvePref);
    prefs.put(thirteen, thirteenPref);
    prefs.put(fourteen, fourteenPref);
    prefs.put(fifteen, fifteenPref);
    prefs.put(sixteen, sixteenPref);
    prefs.put(seventeen, seventeenPref);
    prefs.put(eighteen, eighteenPref);
    prefs.put(nineteen, nineteenPref);
    prefs.put(twenty, twentyPref);


    StableRoommates sr = new StableRoommates(prefs);
    Map<Person, Person> pairings = sr.getPairs();
    Assert.assertFalse(isStable(pairings));
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
    Assert.assertFalse(isStable(pairings));
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
    Assert.assertFalse(isStable(pairings));
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
    Assert.assertFalse(isStable(pairings));
  }

  private boolean isStable(Map<Person, Person> map) {
    // if someone doesn't have a partner
    for (Person currPerson : map.keySet()) {
      if (map.get(currPerson) == null) {
        return false;
      }
    }

    // someone's partner's partner should be themselves
    for (Person currPerson : map.keySet()) {
      if (!map.get(map.get(currPerson)).equals(currPerson)) {
        return false;
      }
    }

    return true;
  }
}
