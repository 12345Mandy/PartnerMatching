package edu.brown.cs.student.main.match_evaluator;


import edu.brown.cs.student.main.pair.Pair;
import edu.brown.cs.student.main.stable_roommates.Person;
import edu.brown.cs.student.main.survey.Answer;
import edu.brown.cs.student.main.survey.Question;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * class for converting answer choices into preferences.
 */
public class MatchEvaluator {
  private List<Question> questions; // a list of the questions
  private Map<Person, List<Answer>> personToAnswers; // a map from person to their answers

  /**
   * @param questions       - the list of the questions that were asked
   * @param personToAnswers - a map from a person to their answer choices
   */
  public MatchEvaluator(List<Question> questions, Map<Person, List<Answer>> personToAnswers) {
    this.questions = questions;
    this.personToAnswers = personToAnswers;

  }

  /**
   * Gets all the info -- answers from each user who took survey -- evaluates similarity.
   *
   * @return a hashmap mapping ids to a list of their matches sorted
   */
  public Map<Person, List<Person>> evaluateMatches() {
    Map<Person, List<Person>> finalMatches = new HashMap<>();
    List<Question> questionInfo = this.questions;

    // starts through every person
    for (Person currPerson : this.personToAnswers.keySet()) {
      // stores list of ids that represent the similarity
      List<Pair<Person, Double>> idScorePairs = new ArrayList<>();
      List<Answer> currPersonsAnswers = this.personToAnswers.get(currPerson);

      // goes through each person who took the survey
      // if not the same person, calculate score
      // score will be [id, score] and put into similarity array
      for (Person potMatch : this.personToAnswers.keySet()) {
        if (!currPerson.equals(potMatch)) {
          List<Answer> pot = this.personToAnswers.get(potMatch);
          double potScore = getScore(currPersonsAnswers, pot, questionInfo);

          Pair<Person, Double> pair = new Pair<Person, Double>(potMatch, potScore);
          idScorePairs.add(pair);
        }
      }

      // sort the array of [id, score] pairs based off score
      // when sorted --> go through each element and add to list
      // so this will give us a list of IDs based on order of score
      idScorePairs.sort((a, b) -> Double.compare(b.getSecond(), a.getSecond()));
      List<Person> sortedIDs = new ArrayList<>();
      for (Pair<Person, Double> pairs : idScorePairs) {
        sortedIDs.add(pairs.getFirst());
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
   * @param person1answers - list of answers one user had
   * @param person2answers - list of answers another user had
   * @param questionList   - a list of questions; score of each question -- add to total score
   *                       if answers are the same
   * @return total score
   */
  public double getScore(List<Answer> person1answers, List<Answer> person2answers,
                         List<Question> questionList) {
    int totalScore = 0;
    for (int i = 0; i < person1answers.size(); i++) {
      if (person1answers.get(i).equals(person2answers.get(i))) {
        totalScore += questionList.get(i).getScore();
      }
    }

    return totalScore;
  }
}
