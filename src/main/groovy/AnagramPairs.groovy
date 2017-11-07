List<String> words = scrubInput(args[0])

if (words.size() < 4) {
  println "A string consisting of at least 4 words is required"
} else {
  List combos = getAllCombinations(words)
  Map results = findAnagramPairs(combos)
  printResults(results)
}

// Treat non-alphanumeric characters as whitespace
// Disregard words less than 4 characters long
List<String> scrubInput(String str) {
  str.toLowerCase().split("[^a-zA-Z]").findAll { it.length() > 3 }
}

// List of all possible 2-word combinations
List<List<String>> getAllCombinations(List<String> words) {
  List combos = []
  for (i = 0; i < words.size(); i++) {
    for (j = i + 1; j < words.size(); j++) {
      combos << [words[i], words[j]]
    }
  }
  combos
}

// Iterate through list of all possible 2-word combinations to find anagram pairs
Map findAnagramPairs(List<List<String>> combos) {
  Map result = [:]
  for (k = 0; k < combos.size(); k++) {
    for (m = k + 1; m < combos.size(); m++) {
      if (!combos[k].contains(combos[m][0]) && !combos[k].contains(combos[m][1])) {
        // Join the pair of strings
        String s1 = combos[k].join()
        String s2 = combos[m].join()
        if (s1.length() == s2.length() && isAnagram(s1, s2)) {
          if (isAnagram(s1, s2)) {
            result[combos[k]] = combos[m]
          }
        }
      }
    }
  }
  result
}

// Check if paired strings are anagrams
boolean isAnagram(String s1, String s2) {
  s1.split("").sort() == s2.split("").sort()
}

void printResults(Map results) {
  if (results.size() > 0) {
    results.each { k, v ->
      println "${k.join(" ")} and ${v.join(" ")}"
    }
  } else {
    println "No anagram pairs found"
  }
}
