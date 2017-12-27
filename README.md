"# 101BD" 
Module 1 homework 1 
	Program runs MapReduce Job to find longest words
	Mapper takes line, parse line to words, write out word length as key and word as value
	Reducer takes key -> word length, values -> list of words, write out words length -> words
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
Module 1 homework 2 
	Program runs MapReduce Job to sum and calculate average of bytes sent per ip address from Apache log file 
	Driver replace "\t" to "," (CSV)
	Mapper takes line, parse log properties, filter and count zero bytes sent, write out counter of zero bytes sent, write out ip address as key and CountAverageTuple(1, Bytes sent) holder POJO as value 
	Reducer takes key -> ip address, values  -> list of CountAverageTuple(1, Bytes sent), calculate sum and average per ip address, write out ip address -> average and sum
	Uses CountAverageTuple(1, Bytes sent) to pass values from mapper to reducer and to help implement combiner as reducer 
	LogRexExp class parse Apache log line, return ApacheLog POJO and throws IllegalArgumentException if finds bag log or zero bytes sent  
	ApacheLog POJO class to hold Apache log line properties
	Build tool sbt
	JUnit Test coverage 79%
	Example 
	ip1,29811.15,82649538560
	ip10,277.0,153458
	ip100,4166.4,173588880
	ip1000,31679.0,10035590144
	ip1001,29570.637,9618648064
	ip1002,24777.469,9208843264
	ip1003,28861.582,9995891712
	ip1004,23904.412,9714155520
	ip1005,31679.0,10035590144
	ip1006,31679.0,10035590144
	