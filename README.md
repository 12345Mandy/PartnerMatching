# cs0320 Term Project 2021

**Team Members:** Alana White (awhite35), Mario Camacho (mcamach6), Mandy He (mhe26), Pedro Monteiro Borges (pmontei1)

**Team Strengths and Weaknesses:** 

#### Alana (awhite35)

Overview: Sophomore who took CS 15/16 concentrating in CS and Visual Arts

Strengths: 
* Object-oriented design patterns
* Using Java
* HTML/CSS
* Making frontend look pretty

Weaknesses: 
* Writing comprehensive tests
* SQL commands
* Containerization related stuff


#### Mandy(mhe26)

Overview: Freshman who took CS19 who is looking for a way to combine CS and design (aka. Not super sure what I want to major in but leaning toward CS and/or smth else design or psych related)

Strengths:
* Figma and Adobe XD (if we need to do prototypes for frontend stuff)
* Java + HTML/CSS (what we learned in this class)
* Decent at testing.
* Relatively new to the world of CS, but open to learning anything needed for this project!!!

Developing Skills/weaknesses(not confident in these skills yet, but not like terrible either):
* Javascript
* SQL


#### Mario (mcamach6)

Overview: Sophomore who took 17/18. Systems and Software Principles.

Strengths:
* Implementing algorithms and writing organized code.
* Debugging not using print statements.
* Good at SQL, i think

Weaknesses:
* Frontend
* Designing generalized classes
* Writing tests that are descriptive/cover a lot of code

#### Pedro (pmontei1)

Overview: Sophomore who took CS17/18 concentrating in CS (visual/software)

Strengths: 
* Implementing backend algorithms/design patterns
* Debugging
* Okay with sql

Weaknesses
* Anything frontend related
* Documentation through comments/README
* Writing readable code on the first attempt

**Project Idea(s):** 
### Idea 1: Datamatch but for other things

Partner matching app that allows users to design and take custom surveys (either specialized to finding CS lab partners or more generic)

Problems:
* Assigning partners for CS labs can be difficult (Some of us are TAs)
* It would be helpful to have a general tool that generates pairings based off user data

Requirements:
* Allows users to design their own surveys (questions + visual design); users can assign importance of different questions in generating surveys
  * Explanation: Users can decide what they want to match people based off of so it’s applicable in more situations
  * Challenges: Storing data based off user input, front-end that allows adding more options
* Users can take surveys
  * Explanation: This is so users can take surveys to be matched with partners based off of their needs
  * Challenges: frontend design, parsing invalid inputs
* Based off the results of surveys, generate partner matches
  * Explanation: Main reason survey was made. Users can contact their matches and work on labs, develop relationships, etc.
  * Challenges: How should we weigh certain questions and how do we know our matches were a good “fit”? This would be the algorithmic component.
* Admins can control pairings not being repeated (in the case of CS labs)
  * Explanation: In many cases, including CS labs, you want to generate many pairings based off of responses to the survey with no repeat in partners.
  * Challenges: Avoiding repeats while still making optimal pairings.

**HTA Approval (dpark20):** Idea approved - focus on partner matching algorithm and add complexity there


### Idea 2: Online educational tool

TeacherBot?

Problems:
* Accessible education outreach for more niche/difficult topics
* Hard to know where to start and what to reference when learning a new topic.

Requirements:
* Prompts user to ask input what they want to learn about
  * Explanation: the user should be able to decide which topics they want to study 
  * Challenge: Parsing the user’s selections and using them to find relevant topics/subtopics on what they want to learn
* Make a dataset of different topics (scrape from internet?)
  * Explanation: In order to be able to recommend resources for users to learn difficult topics, we need to have resources that have been shown to be useful.
  * Challenge: getting a sufficient amount of data/recommendations from the internet that are credible.
* Given inputs, pulls resources from dataset
  * Explanation: Use user inputs in order to search dataset for recommended resources related to the topic
  * Challenges: constructing a search algorithm that can find relevant resources based off user queries
* Outputs websites, videos, articles, related to topic to user in a UI
  * Explanation: the resources given to the user should be easily accessible and the user should be able to select which resources they would like to use
  * Challenge: presenting the results in an intuitive format and doing so while accommodating different types of outputs

**HTA Approval (dpark20):** Idea approved but I would be wary of trying to find resources. It might be more challenging than you think depending on what scope you're working at.


### Idea 3: Recipe Recommendation

Recommends recipes based off of ingredients you have + preferences

Problems:
* Having ingredients left over and not knowing what to make
* Wanting to try to cook new foods but not having ideas

Requirements:
* Let users input ingredients in their house/pantry+relative abundance of each ingredient (a bit, a lot, etc.) + kitchen tools they have
  * Explanation: In order to recommend recipes, it’s good to know what ingredients, how much of each ingredient and what tools the user has.
  * Challenge: Setting up an interface that will allow the user to easily put in these values.
* Find/integrate a database of recipes
  * Explanation: we need to have a set of recipes to choose from depending on the ingredients and tools the user has
  * Challenge: finding a variety of recipes and creating a large enough dataset to give meaningful results or finding an online database that conforms to our data expectations
* Based off of ingredients, kitchen items, and users’ food preferences (e.g. salty foods, sweet foods), recommend recipes for them to try
  * Explanation: Main purpose of the app. Allows users to see what food they may create with the inputs
  * Challenge: Algorithmic component. How do we parse the inputs and find a set of dishes that can be created(without any assumptions of what more the user has)?
* Save recipes they find to look up later
  * Explanation: Allows for users to easily find foods they may have enjoyed. Can help reduce the need to input ingredients again.
  * Challenges: How long should we save the recipes? Create an interface to allow for recipes to be seen, removed, edited, etc.

**HTA Approval (dpark20):** Idea approved

**Mentor TA:** Raj!

## Meetings
_On your first meeting with your mentor TA, you should plan dates for at least the following meetings:_

**Specs, Mockup, and Design Meeting:** _(Schedule for on or before March 15)_

**4-Way Checkpoint:** _(Schedule for on or before April 5)_

**Adversary Checkpoint:** _(Schedule for on or before April 12 once you are assigned an adversary TA)_


## FINAL PROJECT

## What is it

We settled with idea #1 (partner matching):
*Users can create own surveys, generate unique URL
*Other users can take surveys
*Survey owner can generate matches
*User can view their matches

How our partner matching algorithm works:
  *Compare all pairs of people who took the survey
    **If two people have the same answer, increase how “similar” they are.
  *Once we go through all pairs, each person maps to a list of all other people
    **Sorted from most to least similar based off total similarity score
  *Use of stable roommates algorithm


## How to Build and Run

To build: mvn package in the top-level directory
To run: run ./run --gui from the top-level directory and yarn start from inside /frontend (see README in /frontend)
To run java tests: mvn test

