# Anagram Pairs

Create a function that finds two words in a text that are anagarams of another two words in that text. For example:

*Happy eaters always heat their yappers.*
Would yield: *happy eaters and heat yappers*

The function should also work on long blocks of text.

Rules:
- Treat all letters as lowercase.
- Ignore any words less than 4 characters long.
- Treat all non-alpha-numeric characters as whitespace. So "Surely. And they're completely right!" becomes four words: "surely they completely right"
- Neither of the words in the first pair can be repeated in the second pair.

---
# Running the Script

If Groovy is installed, the script can be run from the command line as such

```groovy AnagramPairs.groovy "Happy eaters always heat their yappers"```

Another option would be to copy the script code and paste into the [online Groovy console](https://groovyconsole.appspot.com/). Just be sure to replace `args[0]` on [line 1](https://github.com/miclowe/flock/blob/04ce6730b19b415907af4f801426929a6c9c7225/src/main/groovy/AnagramPairs.groovy#L1) with a string of text.
