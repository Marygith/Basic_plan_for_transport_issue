# Overview:


#### This program is designed to solve a transport problem using five different DM algorithms:

+ the method of minimum cost
+ the method of northwest corner
+ the method of northeast corner
+ the method of southeast corner
+ the method of southwest corner

#### They will be compared by final transportation cost to find the most optimal one.
#### There will be some experimental transportation problems to define an algorithm that is the most beneficial statistically.

## Input

#### Input is divided into three parts(screens):

1. "A" value input - amount of products in each point of departure
2. "B" value input - amount of required products in each destination
3.  Transportation cost input from each Ai to each Bi in generated table with size A*B  


![](/app/src/main/res/drawable-v24/a_input.jpg)

![](/app/src/main/res/drawable-v24/b_input.jpg)

![](/app/src/main/res/drawable-v24/transportation_cost.jpg)

## Output


#### Output is represented by results of the algorithms

1. First screen: there are five buttons, each represents one of the algorithms and leads to the result of it.
Details: 
   * Every button has the result of algorithm - final cost of transportation 
   * Every result is colored - green is for the most optimal one, red is for the least optimal, blue is for middle
   * After every cost there is a percentage, that shows probability for this algorithm to be the most optimal
   * Percentage is calculated using random numbers from 1 to 100 as input 
2. Algorithm's screen shows basic plan and transportation cost(again). There is a button that leads to source data
3. Source data: all input collected and represented on one screen


![](/app/src/main/res/drawable-v24/five_algorithms.jpg)

![](/app/src/main/res/drawable-v24/result.jpg)

![](/app/src/main/res/drawable-v24/source_data.jpg)

## *Tools:*


+ Programming language - Kotlin

+ IDE - android studio

## *Team*:


Masha Naumenko P3122
