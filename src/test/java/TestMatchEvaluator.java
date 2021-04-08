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
  public void pbtTest() {
    final int NUM_ITERATIONS = 20000;

    int MAX_NUM_ANSWERS = 10;
    int MAX_NUM_QUESTIONS = 20;
    int MAX_NUM_PEOPLE = 100;
    int MAX_QUESTION_VALUE = 10000;

//    Set<Integer> foundAns = new HashSet<>();
//    Set<Integer> foundQues = new HashSet<>();
//    Set<Integer> foundPeop = new HashSet<>();
//    Set<Integer> foundMaxQues = new HashSet<>();

    int numOfStableMatches = 0;
    for (int currIter = 0; currIter < NUM_ITERATIONS; currIter++) {
      // RANDOMIZED MOCK DATA!
      int NUM_ANSWERS = (int) Math.floor(Math.random() * MAX_NUM_ANSWERS) + 1;
      int NUM_QUESTIONS = (int) Math.floor(Math.random() * MAX_NUM_QUESTIONS) + 1;
      int NUM_PEOPLE = (int) (Math.floor(Math.random() * MAX_NUM_PEOPLE) + 1) * 2; // should be even
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
              new Question(i, QUESTION_VALUE);
          List<Answer> answers = new ArrayList<>();

          // makes 4 answers for each question
          for (int j = 0; j < NUM_ANSWERS; j++) {
            Answer currAnswer = new Answer(j, currQuestion);
            answers.add(currAnswer);
          }

          currQuestion.setAnswers(answers);
          questions.add(currQuestion);
        }

        // selected a random answer for each person
        for (int i = 0; i < NUM_PEOPLE; i++) {
          Person currPerson = new Person(i, Integer.toString(i));
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

        Assert.assertTrue(sr.isStable(pairs));

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

//    System.out.println("Num unique answers is " + foundAns.size());
//    System.out.println("Num unique questions is " + foundQues.size());
//    System.out.println("Num unique people is " + foundPeop.size());
//    System.out.println("Num unique max value is " + foundMaxQues.size());
  }
}
