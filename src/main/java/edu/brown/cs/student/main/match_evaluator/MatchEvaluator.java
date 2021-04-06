package edu.brown.cs.student.main.match_evaluator;


import edu.brown.cs.student.main.stable_roommates.Person;
import edu.brown.cs.student.main.survey.Answer;
import edu.brown.cs.student.main.survey.Question;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MatchEvaluator {
  private List<Question> questions;
  private Map<Person, List<Answer>> personToAnswers;

  // ONLY FOR TESTING
  private final int NUM_ANSWERS = 4;
  private final int NUM_QUESTIONS = 10;
  private final int NUM_PEOPLE = 10;

  // DATA IS IN FORM:
  // first element is "ID" of person, next are question answers.
  // -1,q1,q2,q3,...
  // 0 a1,a2,a3,...
  // 1 a1,a2,a3...
  public MatchEvaluator() {
    // RANDOMIZED MOCK DATA!
    // SUBJECT TO CHANGE!
    this.questions = new ArrayList<>();
    this.personToAnswers = new HashMap<>();

    // adds questions with random values
    for (int i = 0; i < NUM_QUESTIONS; i++) {
      // make a question with a random value
      Question currQuestion = new Question(i, Math.floor(Math.random() * 4));
      List<Answer> answers = new ArrayList<>();

      // makes 4 answers for each question
      for (int j = 0; j < NUM_ANSWERS; j++) {
        Answer currAnswer = new Answer(j, currQuestion);
        answers.add(currAnswer);
      }

      currQuestion.setAnswers(answers);
      this.questions.add(currQuestion);
    }

    // selected a random answer for each person
    for (int i = 0; i < NUM_PEOPLE; i++) {
      Person currPerson = new Person(i, Integer.toString(i));
      List<Answer> currPersonsAnswers = new ArrayList<>();

      // get a random answer for each question
      for (Question currQuestion : this.questions) {
        int randIndex = new Random().nextInt(NUM_ANSWERS);
        Answer randAnswer = currQuestion.getAnswers().get(randIndex);

        currPersonsAnswers.add(randAnswer);
      }


      this.personToAnswers.put(currPerson, currPersonsAnswers);
    }

//    List<List<Double>> mockInfo = new ArrayList<>();
//    for (double i = -1; i < 10; i++) {
//      List<Double> mockAnswers = new ArrayList<>();
//      mockAnswers.add(i); // this is mock id
//      for (int mock = 1; mock < 10; mock++) {
//        mockAnswers.add(Math.floor(Math.random() * 4));
//      }
//      mockInfo.add(mockAnswers);
//    }
//    evaluateMatches(mockInfo);
  }

  /**
   * Gets all the info -- answers from each user who took survey --> evaluates similarity.
   * @return a hashmap mapping ids to a list of their matches sorted
   */
  public Map<Person, List<Person>> evaluateMatches() {
    Map<Person, List<Person>> finalMatches = new HashMap<>();
    List<Question> questionInfo = this.questions;

    // starts iterating from the second row(first row is reserved for questions)
    for (Person currPerson : this.personToAnswers.keySet()) {
      // stores list of ids that represent the similarity
      List<PersonToScore> idScorePairs = new ArrayList<>();
      List<Answer> currPersonsAnswers = this.personToAnswers.get(currPerson);

      // goes through each person who took the survey
      // if not the same person, calculate score
      // score will be [id, score] and put into similarity array
      for (Person potMatch : this.personToAnswers.keySet()) {
        if (!currPerson.equals(potMatch)) {
          List<Answer> pot = this.personToAnswers.get(potMatch);
          double id = potMatch.getId();
          double potScore = getScore(currPersonsAnswers, pot, questionInfo);

          PersonToScore pair = new PersonToScore(potMatch, potScore);
          idScorePairs.add(pair);
        }
      }

      // sort the array of [id, score] pairs based off score
      // when sorted --> go through each element and add to list
      // so this will give us a list of IDs based on order of score
      idScorePairs.sort((a, b) -> Double.compare(b.getScore(), a.getScore()));
      List<Person> sortedIDs = new ArrayList<>();
      for (PersonToScore pairs : idScorePairs) {
        sortedIDs.add(pairs.getPerson());
      }

      // add to hashmap
      // will have ID --> List of sorted IDs
      // the first id being most similar
      finalMatches.put(currPerson, sortedIDs);
    }
    return finalMatches;
  }

  /**
   * Calculates similarity score based off two people and the points for each question.
   *
   * @param person1      - list of answers one user had
   * @param person2      - list of answers another user had
   * @param questionList - a list of questions; score of each question --> add to total score if answers are the same
   * @return total score
   */
  public double getScore(List<Answer> person1, List<Answer> person2, List<Question> questionList) {
    int totalScore = 0;
    for (int i = 1; i < person1.size(); i++) {
      if (person1.get(i).equals(person2.get(i))) {
        totalScore += questionList.get(i).getScore();
      }
    }

    return totalScore;
  }
}
