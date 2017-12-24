"# 101BD" 
Module 1 homework 1 
	Program runs MapReduce Job to find longest words
	Mapper takes line, parse line to words, write out word length as key and word as value
	Reducer takes key -> word length, values -> words, write out words length -> words
	Uses CustomKey to define order in shuffle/sort phase (descending)
	Build tool sbt
	JUnit Test coverage 80%
	
	