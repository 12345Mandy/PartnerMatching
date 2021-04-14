import edu.brown.cs.student.main.match_evaluator.MatchEvaluator;
import edu.brown.cs.student.main.stable_roommates.Person;
import edu.brown.cs.student.main.stable_roommates.StableRoommates;
import edu.brown.cs.student.main.survey.Answer;
import edu.brown.cs.student.main.survey.Question;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class TestMatchEvaluator {
  @Test
  public void fuzzEvenNumPeopleTest() {
    final int NUM_ITERATIONS = 1000;

    int MAX_NUM_ANSWERS = 100;
    int MAX_NUM_QUESTIONS = 100;
    int MAX_NUM_PEOPLE = 100;
    int MAX_QUESTION_VALUE = 100;

//    Set<Integer> foundAns = new HashSet<>();
//    Set<Integer> foundQues = new HashSet<>();
//    Set<Integer> foundPeop = new HashSet<>();
//    Set<Integer> foundMaxQues = new HashSet<>();

    Set<Map<Person, Person>> uniqueMatches = new HashSet<>();

    int numOfStableMatches = 0;
    for (int currIter = 0; currIter < NUM_ITERATIONS; currIter++) {
      if (currIter % 20 == 0) {
        System.out.println((double) currIter / NUM_ITERATIONS + " percent done.");
      }
      // RANDOMIZED MOCK DATA!
      int NUM_ANSWERS = (int) Math.floor(Math.random() * MAX_NUM_ANSWERS) + 1;
      int NUM_QUESTIONS = (int) Math.floor(Math.random() * MAX_NUM_QUESTIONS) + 1;
      int NUM_PEOPLE = (int) 12; // should be even
      int QUESTION_VALUE = (int) Math.floor(Math.random() * MAX_QUESTION_VALUE) + 1;

//      foundAns.add(NUM_ANSWERS);
//      foundQues.add(NUM_QUESTIONS);
//      foundPeop.add(NUM_PEOPLE);
//      foundMaxQues.add(QUESTION_VALUE);

      try {
        List<Question> questions = new ArrayList<>();
        Map<Person, List<Answer>> personToAnswers = new HashMap<>();

        // adds questions with random values
        for (int i = 0; i < NUM_QUESTIONS; i++) {
          // make a question with a random value
          Question currQuestion =
              new Question("sample text " + i, QUESTION_VALUE);
          List<Answer> answers = new ArrayList<>();

          // makes answers for each question
          for (int j = 0; j < NUM_ANSWERS; j++) {
            Answer currAnswer = new Answer("sample ans " + j);
            answers.add(currAnswer);
          }

          currQuestion.setAnswers(answers);
          questions.add(currQuestion);
        }

        // selected a random answer for each person
        for (int i = 0; i < NUM_PEOPLE; i++) {
          Person currPerson = new Person(Integer.toString(i), "some@email.com");
          List<Answer> currPersonsAnswers = new ArrayList<>();

          // get a random answer for each question
          for (Question currQuestion : questions) {
            int randIndex = new Random().nextInt(NUM_ANSWERS);
            Answer randAnswer = currQuestion.getAnswers().get(randIndex);

            currPersonsAnswers.add(randAnswer);
          }


          personToAnswers.put(currPerson, currPersonsAnswers);
        }

        MatchEvaluator matchEvaluator = new MatchEvaluator(questions, personToAnswers);
        Map<Person, List<Person>> prefs = matchEvaluator.evaluateMatches();

        for (Person currPerson : prefs.keySet()) {
          currPerson.setPreferences(prefs.get(currPerson));
        }

        StableRoommates sr = new StableRoommates(prefs);
        Map<Person, Person> pairs = sr.getPairs();

        Assert.assertTrue(sr.isStable(pairs));
        uniqueMatches.add(pairs);

//        System.out.println(currIter);
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println();
        System.out.println("Broke with num answers " + NUM_ANSWERS);
        System.out.println("Broke with num questions " + NUM_QUESTIONS);
        System.out.println("Broke with num people " + NUM_PEOPLE);
        System.out.println("Broke with question value " + QUESTION_VALUE);
        System.out.println();
      }
    }

    System.out.println(uniqueMatches.size());

//    System.out.println("Num unique answers is " + foundAns.size());
//    System.out.println("Num unique questions is " + foundQues.size());
//    System.out.println("Num unique people is " + foundPeop.size());
//    System.out.println("Num unique max value is " + foundMaxQues.size());
  }

  @Test
  public void fuzzOddNumPeopleTest() {
    final int NUM_ITERATIONS = 1000;

    int MAX_NUM_ANSWERS = 100;
    int MAX_NUM_QUESTIONS = 100;
    int MAX_NUM_PEOPLE = 100;
    int MAX_QUESTION_VALUE = 100;

//    Set<Integer> foundAns = new HashSet<>();
//    Set<Integer> foundQues = new HashSet<>();
//    Set<Integer> foundPeop = new HashSet<>();
//    Set<Integer> foundMaxQues = new HashSet<>();

    Set<Map<Person, Person>> uniqueMatches = new HashSet<>();

    int numOfStableMatches = 0;
    for (int currIter = 0; currIter < NUM_ITERATIONS; currIter++) {
      if (currIter % 20 == 0) {
        System.out.println((double) currIter / NUM_ITERATIONS + " percent done.");
      }
      // RANDOMIZED MOCK DATA!
      int NUM_ANSWERS = (int) Math.floor(Math.random() * MAX_NUM_ANSWERS) + 1;
      int NUM_QUESTIONS = (int) Math.floor(Math.random() * MAX_NUM_QUESTIONS) + 1;
      int NUM_PEOPLE = (int) (Math.floor(Math.random() * MAX_NUM_PEOPLE) + 1) * 2 +
          1; // should be odd for this test case
      int QUESTION_VALUE = (int) Math.floor(Math.random() * MAX_QUESTION_VALUE) + 1;

//      foundAns.add(NUM_ANSWERS);
//      foundQues.add(NUM_QUESTIONS);
//      foundPeop.add(NUM_PEOPLE);
//      foundMaxQues.add(QUESTION_VALUE);

      try {
        List<Question> questions = new ArrayList<>();
        Map<Person, List<Answer>> personToAnswers = new HashMap<>();

        // adds questions with random values
        for (int i = 0; i < NUM_QUESTIONS; i++) {
          // make a question with a random value
          Question currQuestion =
              new Question("sample text " + i, QUESTION_VALUE);
          List<Answer> answers = new ArrayList<>();

          // makes 4 answers for each question
          for (int j = 0; j < NUM_ANSWERS; j++) {
            Answer currAnswer = new Answer("sample ans " + j);
            answers.add(currAnswer);
          }

          currQuestion.setAnswers(answers);
          questions.add(currQuestion);
        }

        // selected a random answer for each person
        for (int i = 0; i < NUM_PEOPLE; i++) {
          Person currPerson = new Person(Integer.toString(i), "some@email.com");
          List<Answer> currPersonsAnswers = new ArrayList<>();

          // get a random answer for each question
          for (Question currQuestion : questions) {
            int randIndex = new Random().nextInt(NUM_ANSWERS);
            Answer randAnswer = currQuestion.getAnswers().get(randIndex);

            currPersonsAnswers.add(randAnswer);
          }


          personToAnswers.put(currPerson, currPersonsAnswers);
        }

        MatchEvaluator matchEvaluator = new MatchEvaluator(questions, personToAnswers);
        Map<Person, List<Person>> prefs = matchEvaluator.evaluateMatches();

        for (Person currPerson : prefs.keySet()) {
          currPerson.setPreferences(prefs.get(currPerson));
        }

        StableRoommates sr = new StableRoommates(prefs);
        Map<Person, Person> pairs = sr.getPairs();

//        if (sr.isStable(pairs)) {
//          numOfStableMatches++;
//        }

        for (Map.Entry<Person, Person> kvPair : pairs.entrySet()) {
          if (pairs.get(kvPair.getKey()) == null) {
            pairs.remove(kvPair.getKey());
            break;
          }
        }

        Assert.assertTrue(sr.isStable(pairs));
        uniqueMatches.add(pairs);

//        System.out.println(currIter);
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println();
        System.out.println("Broke with num answers " + NUM_ANSWERS);
        System.out.println("Broke with num questions " + NUM_QUESTIONS);
        System.out.println("Broke with num people " + NUM_PEOPLE);
        System.out.println("Broke with question value " + QUESTION_VALUE);
        System.out.println();
      }
    }

    System.out.println(uniqueMatches.size());

//    System.out.println("Num unique answers is " + foundAns.size());
//    System.out.println("Num unique questions is " + foundQues.size());
//    System.out.println("Num unique people is " + foundPeop.size());
//    System.out.println("Num unique max value is " + foundMaxQues.size());
  }
}
