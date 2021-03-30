package edu.brown.cs.student.main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import edu.brown.cs.student.main.stable_roommates.Person;
import edu.brown.cs.student.main.stable_roommates.StableRoommates;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import spark.ExceptionHandler;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.TemplateViewRoute;
import spark.template.freemarker.FreeMarkerEngine;

import com.google.common.collect.ImmutableMap;

import freemarker.template.Configuration;

/**
 * The Main class of our project. This is where execution begins.
 */
public final class Main {

  private static final int DEFAULT_PORT = 4567;

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

    Person one = new Person(1, "1");
    Person two = new Person(2, "2");
    Person three = new Person(3, "3");
    Person four = new Person(4, "4");
    Person five = new Person(5, "5");
    Person six = new Person(6, "6");

    List<Person> onePref = new ArrayList<>(Arrays.asList(four, two, six));
    List<Person> twoPref = new ArrayList<>(Arrays.asList(six, five, four, one, three));
    List<Person> threePref = new ArrayList<>(Arrays.asList(two, four, five));
    List<Person> fourPref = new ArrayList<>(Arrays.asList(five, two, three, six, one));
    List<Person> fivePref = new ArrayList<>(Arrays.asList(three, two, four));
    List<Person> sixPref = new ArrayList<>(Arrays.asList(one, four, two));

    one.setPreferences(onePref);
    two.setPreferences(twoPref);
    three.setPreferences(threePref);
    four.setPreferences(fourPref);
    five.setPreferences(fivePref);
    six.setPreferences(sixPref);

    LinkedHashMap<Person, List<Person>> prefs = new LinkedHashMap<>();
    prefs.put(one, onePref);
    prefs.put(two, twoPref);
    prefs.put(three, threePref);
    prefs.put(four, fourPref);
    prefs.put(five, fivePref);
    prefs.put(six, sixPref);

    StableRoommates sr = new StableRoommates(prefs);
    sr.generatePairs();

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

    // Setup Spark Routes
    Spark.get("/stars", new FrontHandler(), freeMarker);
  }

  /**
   * Handle requests to the front page of our Stars website.
   */
  private static class FrontHandler implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request req, Response res) {
      Map<String, Object> variables = ImmutableMap.of("title",
          "Stars: Query the database");
      return new ModelAndView(variables, "query.ftl");
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