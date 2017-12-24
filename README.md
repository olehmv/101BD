"# 101BD" 
Module 1 homework 1 
	Program runs MapReduce Job to find longest words
	Mapper takes line, parse line to words, write out word length as key and word as value
	Reducer takes key -> word length, values -> words, write out words length -> words
	Uses CustomKey to define order in shuffle/sort phase (descending)
	Build tool sbt
	JUnit Test coverage 80%
	Example 
	The facts, as you state them, are certainly most remarkable said Holmes. I take it that 	you have no theory yourself which can in any way account for them?
	10	remarkable	
	9	certainly	
	8	yourself	
	7	account	
	6	theory	Holmes	
	5	state	facts	which	
	4	them	said	most	have	that	them	take	
	3	The	for	way	any	can	you	are	you	
	2	no	it	as	in	
	1	I
	
	