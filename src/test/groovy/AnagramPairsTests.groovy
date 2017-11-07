import org.junit.Before
import org.junit.Test

import static org.junit.Assert.*

class AnagramPairsTests {

  AnagramPairs solution

  @Before
  void setUp() {
    solution = new AnagramPairs()
  }

  @Test
  void scrubInput_RemovesAllNonAlphaNumericCharacters() {
    String input = "Surely you can't be serious. I am serious and don't call me Shirley. -Airplane"
    List scrubbed = solution.scrubInput(input)

    assert scrubbed.size() > 0
    scrubbed.each {
      assert it !=~ /[^a-zA-Z]/
    }
  }

  @Test
  void scrubInput_RemovesWordsLessThanThreeCharactersLong() {
    String input = "Do or do not, there is no try. -Yoda"
    List scrubbed = solution.scrubInput(input)

    assert scrubbed.size() == 2
    assert scrubbed.contains("there")
    assert scrubbed.contains("yoda")
  }

  @Test
  void scrubInput_ConvertsToLowercase() {
    String input = "My name is Maximus Decimus Meridius"
    List scrubbed = solution.scrubInput(input)

    assert scrubbed.size() == 4
    assert scrubbed.contains("maximus")
    assert scrubbed.contains("decimus")
    assert scrubbed.contains("meridius")
  }

  @Test
  void scrubInput_ReturnsListOfCleanWords() {
    String input = "Surely. And they're completely right!"
    List scrubbed = solution.scrubInput(input)

    assert scrubbed.size() == 4
    assertEquals(['surely', 'they', 'completely', 'right'], scrubbed)
  }

  
}