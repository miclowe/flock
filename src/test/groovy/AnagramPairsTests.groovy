import org.junit.After
import org.junit.Before
import org.junit.Test

class AnagramPairsTests {

  AnagramPairs solution
  ByteArrayOutputStream out
  PrintStream orig

  @Before
  void setUp() {
    orig = System.out
    out = new ByteArrayOutputStream()
    System.setOut(new PrintStream(out))
    solution = new AnagramPairs()
  }

  @After
  void tearDown() {
    System.setOut(orig)
  }

  @Test
  void processWords_PrintsRequirementIfInsufficientInput() {
    solution.processWords(['make', 'my', 'day'])
    assert out.toString().trim() == "A string consisting of at least 4 words is required"
  }

  @Test
  void processWords_PrintsResults() {
    solution.processWords(['happy', 'eaters', 'heat', 'yappers'])
    assert out.toString().trim() == "[happy, eaters] and [heat, yappers]"
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
    assert scrubbed == ['surely', 'they', 'completely', 'right']
  }

  @Test
  void getAllCombinations_ReturnsListOfAllPairs() {
    List words = ['maximus', 'decimus', 'meridius', 'inigo', 'montoya']
    List pairs = solution.getAllCombinations(words)

    assert pairs.size() == 10
    assert pairs == [
        ['maximus', 'decimus'], ['maximus', 'meridius'], ['maximus', 'inigo'], ['maximus', 'montoya'],
        ['decimus', 'meridius'], ['decimus', 'inigo'], ['decimus', 'montoya'],
        ['meridius', 'inigo'], ['meridius', 'montoya'],
        ['inigo', 'montoya']
    ]
  }

  @Test
  void findAnagramPairs_ReturnsEmptyListIfNoAnagramPairs() {
    List pairs = [['toto', 'tinman'], ['toto', 'wizard'], ['tinman', 'wizard']]
    List anagrams = solution.findAnagramPairs(pairs)

    assert anagrams.size() == 0
  }

  @Test
  void findAnagramPairs_ReturnsMultipleAnagramPairs() {
    List pairs = [
        ['happy', 'eaters'], ['happy', 'heat'], ['happy', 'yappers'], ['happy', 'heater'], ['happy', 'sappy'],
        ['eaters', 'heat'], ['eaters', 'yappers'], ['eaters', 'heater'], ['eaters', 'sappy'],
        ['heat', 'yappers'], ['heat', 'heater'], ['heat', 'sappy'],
        ['yappers', 'heater'], ['yappers', 'sappy'],
        ['heater', 'sappy']
    ]
    List anagrams = solution.findAnagramPairs(pairs)

    assert anagrams.size() == 3
    assert anagrams == [
        [['happy', 'eaters'], ['heat', 'yappers']],
        [['happy', 'eaters'], ['heater', 'sappy']],
        [['heat', 'yappers'], ['heater', 'sappy']]
    ]
  }

  @Test
  void isAnagram_IfStringsNotAnagramsReturnsFalse() {
    assert !solution.isAnagram('thor', 'spidey')
  }

  @Test
  void isAnagram_IfStringsAreAnagramsReturnsTrue() {
    assert solution.isAnagram('stop','pots')
  }

  @Test
  void printResults_PrintsMessageIfNoAnagrams() {
    List results = []
    solution.printResults(results)
    assert out.toString().trim() == 'No anagram pairs found'
  }

  @Test
  void printResults_PrintsJoinedPairs() {
    List results = [[['spock, bones'], ['peons, bocks']]]
    solution.printResults(results)
    assert out.toString().trim() == '[spock, bones] and [peons, bocks]'
  }

}