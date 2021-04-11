package edu.brown.cs.student.main.match_evaluator;


import edu.brown.cs.student.main.stable_roommates.Person;
import edu.brown.cs.student.main.survey.Answer;
import edu.brown.cs.student.main.survey.Question;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchEvaluator {
  private List<Question> questions;
  private Map<Person, List<Answer>> personToAnswers;

  // DATA IS IN FORM:
  // first element is "ID" of person, next are question answers.
  // -1,q1,q2,q3,...
  // 0 a1,a2,a3,...
  // 1 a1,a2,a3...
  public MatchEvaluator(List<Question> questions, Map<Person, List<Answer>> personToAnswers) {
    this.questions = questions;
    this.personToAnswers = personToAnswers;

  }

  /**
   * Gets all the info -- answers from each user who took survey -- evaluates similarity.
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
   * @param questionList - a list of questions; score of each question -- add to total score if answers are the same
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