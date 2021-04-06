import edu.brown.cs.student.main.match_evaluator.MatchEvaluator;
import edu.brown.cs.student.main.stable_roommates.Person;
import edu.brown.cs.student.main.stable_roommates.StableRoommates;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class TestMatchEvaluator {
  @Test
  public void testOne() {
    MatchEvaluator matchEvaluator = new MatchEvaluator();
    Map<Person, List<Person>> prefs = matchEvaluator.evaluateMatches();

    for (Person currPerson : prefs.keySet()) {
      currPerson.setPreferences(prefs.get(currPerson));
    }

    Map<Person, Person> pairs = new StableRoommates(prefs).getPairs();

    int x = 0;
  }
}
