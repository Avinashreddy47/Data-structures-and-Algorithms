Name   : Sallagonda Avinash Reddy
UTA ID : 1002034491
Programming Language : JAVA

Structure :
Classes : maxconnect4, AiPlayer, GameBoard
 

class maxconnect4 :
methods : main(), exit_function(value).


class AiPlayer :
methods : findBestPlay(), minu(), maxu()

findBestPlay() -> This function makes the decision to use the min and max value to transfer the machine using the two functions given below.
minu() -> The minu functions calculated the minimizer.
maxu() -> TThe maxu function calculated the maximizer value.
fun()->returns whether the maximizer or minimizer depending on the minu and maxu functions. 


class GameBoard :
methods : getScore(), getCurrentTurn(), getPieceCount(), getGameBoard(), isValidPlay(), printGameBoard(), evaluationFunction(),horizontal(),vertical()

getScore() -> It returns the current game scores.
getCurrentTurn() -> This function indicates whether which player turn it is.
getGameBoard() -> This method gets the current board status and .paases it to printGameBoard() function
printGameBoard() -> It prints hows the current board exactly looks.
eval() -> This function calculates the score of the non terminal and terminal nodes
horizontal() ->It calculates the score of the board if the status is horizantal
vertical() ->It calculates the score of the board if the status is diagonal
diagonal() ->It calculates the score of the board if the status is diagonal
fun()->It caluclates the scores of horizantal,vertcial and diagonal boards


How to run Code :
Compile using the below command:
javac maxconnect4.java AiPlayer.java GameBoard.java

Execute using the below command:

For interactive mode:
java maxconnect4 interactive [input_file] [computer-next/human-next] [depth]  
example: java maxconnect4 interactive input.txt computer-next 7

For one-move mode:
java maxconnect4 one-move [input_file] [output_file] [depth]  
example: java maxconnect4 one-move input1.txt output.txt 7


For time
@echo off
set startTime=%time%
java maxconnect4 interactive input1.txt computer-next 7
echo Start Time: %startTime%
echo Finish Time: %time%
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
The depth and execution time of user results are as below:

Depth limit	Time	
1		0.130	seconds
2		0.169	seconds
3		0.181	seconds
4		0.190	seconds
5		0.273	seconds
6		0.701	seconds
7		1.011	seconds
8		1.931	seconds
9		4.879	seconds
10		16.523	seconds
11		51.315	seconds
12		3 minute 04.31seconds

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------