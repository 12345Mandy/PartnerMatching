package edu.brown.cs.student.main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import edu.brown.cs.student.main.match_evaluator.MatchEvaluator;
import edu.brown.cs.student.main.stable_roommates.Person;
import edu.brown.cs.student.main.stable_roommates.StableRoommates;
import edu.brown.cs.student.main.survey.Answer;
import edu.brown.cs.student.main.survey.Question;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.json.JSONArray;
import org.json.JSONObject;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

import com.google.common.collect.ImmutableMap;

import freemarker.template.Configuration;

/**
 * The Main class of our project. This is where execution begins.
 */
public final class Main {

  private static final int DEFAULT_PORT = 4567;
  private static final Gson GSON = new Gson();

  /**
   * The initial method called when execution begins.
   *
   * @param args An array of command line arguments
   */
  public static void main(String[] args) {
    new Main(args).run();
  }

  private String[] args;

  private Main(String[] args) {
    this.args = args;
  }

  private void run() {
    // Parse command line arguments
    OptionParser parser = new OptionParser();
    parser.accepts("gui");
    parser.accepts("port").withRequiredArg().ofType(Integer.class)
        .defaultsTo(DEFAULT_PORT);
    OptionSet options = parser.parse(args);

    if (options.has("gui")) {
      runSparkServer((int) options.valueOf("port"));
    }

  }

  private static FreeMarkerEngine createEngine() {
    Configuration config = new Configuration();
    File templates = new File("src/main/resources/spark/template/freemarker");
    try {
      config.setDirectoryForTemplateLoading(templates);
    } catch (IOException ioe) {
      System.out.printf("ERROR: Unable use %s for template loading.%n",
          templates);
      System.exit(1);
    }
    return new FreeMarkerEngine(config);
  }

  private void runSparkServer(int port) {
    Spark.port(port);
    Spark.externalStaticFileLocation("src/main/resources/static");
    Spark.exception(Exception.class, new ExceptionPrinter());

    FreeMarkerEngine freeMarker = createEngine();

    Spark.options("/*", (request, response) -> {
      String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
      if (accessControlRequestHeaders != null) {
        response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
      }

      String accessControlRequestMethod = request.headers("Access-Control-Request-Method");

      if (accessControlRequestMethod != null) {
        response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
      }

      return "OK";
    });

    Spark.before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

    // Setup Spark Routes
    Spark.post("/match", new MatchesHandler());
  }

  /**
   * handles creating matches.
   */
  private static class MatchesHandler implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
      JSONObject jsonObject = new JSONObject(request.body());

      // get the questions and answers sent from the frontend
      JSONArray questions = jsonObject.getJSONArray("questions");
      JSONArray answers = jsonObject.getJSONArray("answers");

      // creates a list of the questions from the survey
      List<Question> questionList = new ArrayList<>();
      for (int i = 0; i < questions.length(); i++) {
        JSONObject currQuestionData = questions.getJSONObject(i);

        // get the fields for a Question
        String questionText = currQuestionData.getString("question");
        double value = currQuestionData.getDouble("importance");
        JSONArray questionAnswers = currQuestionData.getJSONArray("options");

        // get the possible answers choices for each question
        List<Answer> actualAnswers = new ArrayList<>();
        for (int j = 0; j < questionAnswers.length(); j++) {
          String answerText = questionAnswers.getString(j);

          actualAnswers.add(new Answer(answerText));
        }

        Question currQuestion = new Question(questionText, value);
        currQuestion.setAnswers(actualAnswers);

        questionList.add(currQuestion);
      }

      // maps a person with their answer choice from the survey
      Map<Person, List<Answer>> personToAnswers = new HashMap<>();
      for (int i = 0; i < answers.length(); i++) {
        JSONObject currResponse = answers.getJSONObject(i);
        Person currPerson =
            new Person(currResponse.getString("userID"), currResponse.getString("email"));

        JSONArray currPersonAnswers = currResponse.getJSONArray("responses");

        // get the answers that the person responded with
        List<Answer> listOfAnswers = new ArrayList<>();
        for (int j = 0; j < currPersonAnswers.length(); j++) {
          assert currPersonAnswers.length() == questionList.size();
          Question parentQuestion = questionList.get(j);

          int indexOfAnswer = currPersonAnswers.getInt(j);

          Answer chosenAnswer = parentQuestion.getAnswers().get(indexOfAnswer);

          listOfAnswers.add(chosenAnswer);
        }

        personToAnswers.put(currPerson, listOfAnswers);
      }

      // run the algorithm
      MatchEvaluator matchEvaluator = new MatchEvaluator(questionList, personToAnswers);
      Map<Person, List<Person>> prefs = matchEvaluator.evaluateMatches();

      for (Person currPerson : prefs.keySet()) {
        currPerson.setPreferences(prefs.get(currPerson));
      }

      StableRoommates sr = new StableRoommates(prefs);
      Map<Person, Person> pairs = sr.getPairs();

      Person extra = null;
      for (Person currPerson : pairs.keySet()) {
        if (pairs.get(currPerson) == null) {
          pairs.remove(currPerson);
          extra = currPerson;
          break;
        }
      }

      // convert Person objects to their IDs
      Map<String, List<String>> idPairs = pairs.entrySet()
          .stream()
          .collect(Collectors.toMap(map -> map.getKey().getId(), map ->
              new ArrayList<String>(Arrays.asList(map.getValue().getId()))));

      Map<String, List<Answer>> idToAnswers = personToAnswers.entrySet()
          .stream()
          .collect(Collectors.toMap(map -> map.getKey().getId(), Map.Entry::getValue));


      if (extra != null) {
        List<List<String>> valuesList = new ArrayList<List<String>>(idPairs.values());
        int randomIndex = new Random().nextInt(valuesList.size());
        List<String> randomValue = valuesList.get(randomIndex);

        String firstRandomID = randomValue.get(0);
        String secondRandomID = idPairs.get(firstRandomID).get(0);
        String extraRandomID = extra.getId();

        idPairs.get(firstRandomID).add(extraRandomID);
        idPairs.get(secondRandomID).add(extraRandomID);
        idPairs.put(extraRandomID, Arrays.asList(firstRandomID, secondRandomID));
      }

      // send pairs and answers to frontend
      Map<String, Object> variables = ImmutableMap.of("pairs", idPairs, "answers", idToAnswers);

      return GSON.toJson(variables);
    }
  }

  /**
   * Display an error page when an exception occurs in the server.
   */
  private static class ExceptionPrinter implements ExceptionHandler {
    @Override
    public void handle(Exception e, Request req, Response res) {
      res.status(500);
      StringWriter stacktrace = new StringWriter();
      try (PrintWriter pw = new PrintWriter(stacktrace)) {
        pw.println("<pre>");
        e.printStackTrace(pw);
        pw.println("</pre>");
      }
      res.body(stacktrace.toString());
    }
  }
}
