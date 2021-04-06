package edu.brown.cs.student.main.match_evaluator;


import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchEvaluator {

  // DATA IS IN FORM:
  // first element is "ID" of person, next are question answers.
  // -1,q1,q2,q3,...
  // 0 a1,a2,a3,...
  // 1 a1,a2,a3...
  public MatchEvaluator() {
    // RANDOMIZED MOCK DATA!
    // SUBJECT TO CHANGE!
    List<List<Double>> mockInfo = new ArrayList<>();
    for (double i = -1; i < 10; i++) {
      List<Double> mockAnswers = new ArrayList<>();
      mockAnswers.add(i); // this is mock id
      for (int mock = 1; mock < 10; mock++) {
        mockAnswers.add(Math.floor(Math.random() * 4));
      }
      mockInfo.add(mockAnswers);
    }
    evaluateMatches(mockInfo);
  }

  /**
   * Gets all the info -- answers from each user who took survey --> evaluates similarity.
   *
   * @param surveyTakers - a list of list of answers to the survey + question points
   * @return a hashmap mapping ids to a list of their matches sorted
   */
  public Map<Double, List<Double>> evaluateMatches(List<List<Double>> surveyTakers) {
    Map<Double, List<Double>> finalMatches = new HashMap<>();
    List<Double> questionInfo = surveyTakers.get(0);

    // starts iterating from the second row(first row is reserved for questions)
    for (int currPerson = 1; currPerson < surveyTakers.size(); currPerson++) {
      // stores list of ids that represent the similarity
      List<Double[]> idScorePairs = new ArrayList<>();
      List<Double> curr = surveyTakers.get(currPerson);

      // goes through each person who took the survey
      // if not the same person, calculate score
      // score will be [id, score] and put into similarity array
      for (int potMatch = 1; potMatch < surveyTakers.size(); potMatch++) {
        if (currPerson != potMatch) {
          List<Double> pot = surveyTakers.get(potMatch);
          double id = surveyTakers.get(potMatch).get(0);
          double potScore = getScore(curr, pot, questionInfo);
          idScorePairs.add(new Double[] {id, potScore});
        }
      }

      // sort the array of [id, score] pairs based off score
      // when sorted --> go through each element and add to list
      // so this will give us a list of IDs based on order of score
      idScorePairs.sort((a, b) -> Double.compare(b[1], a[1]));
      List<Double> sortedIDs = new ArrayList<>();
      for (Double[] pairs : idScorePairs) {
        sortedIDs.add(pairs[0]);
      }

      // add to hashmap
      // will have ID --> List of sorted IDs
      // the first id being most similar
      finalMatches.put(curr.get(0), sortedIDs);
    }
    return finalMatches;
  }

  /**
   * Calculates similarity score based off two people and the points for each question.
   *
   * @param person1 list of answers one user had
   * @param person2 list of answers another user had
   * @param scores  score of each question --> add to total score if answers are the same
   * @return total score
   */
  public double getScore(List<Double> person1, List<Double> person2, List<Double> scores) {
    int totalScore = 0;
    for (int i = 1; i < person1.size(); i++) {
      if (person1.get(i).equals(person2.get(i))) {
        totalScore += scores.get(i);
      }
    }

    return totalScore;
  }
}
