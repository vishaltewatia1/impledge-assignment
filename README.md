# impledge-assignment
# execution of code :
1. Download the zip file through the github and extract all the file in your device.
2. open FindLongestFirstAndSecondCompoundWord.java file in any ide (VS Code) and run the code (make sure java package is install in your system ).
3. you can rename Input_02.text to Input_01.text , if you want solution of 1st text file .  
# Design: 
The software starts through reading a text report named "Input_02.Txt" to extract a list of phrases. Each word is then processed to perceive compound words.

# Approach:
Here's a step-through-step rationalization of the code's technique: and its read phrases from the enter report and insert them into the Trie. Additionally, for every word, create a list of its prefixes and add them to the wordSuffixes queue.

# Process:
1. For each phrase suffix, take a look at if it is able to be discovered in the Trie.
2. If found, check if it's far an extended compound phrase than the present day longestCompoundWord. If so, replace secondLongestCompoundWord and longestCompoundWord.
3. If not observed, upload the remaining suffix with the authentic word to the wordSuffixes queue to discover in addition opportunities.
4. Once the queue is empty, the program outputs the longest and second-longest compound phrases
