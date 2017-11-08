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
