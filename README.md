# cs0320 Term Project 2021

**Team Members:** Alana White (awhite35), Mario Camacho (mcamach6), Mandy He (mhe26), Pedro Monteiro Borges (pmontei1)

## FINAL PROJECT

## What is it

We settled with idea #1 (partner matching):
* Users can create own surveys, generate unique URL
* Other users can take surveys
* Survey owner can generate matches
* User can view their matches

How our partner matching algorithm works:
* Compare all pairs of people who took the survey
* If two people have the same answer, increase how “similar” they are.
* Once we go through all pairs, each person maps to a list of all other people
* Sorted from most to least similar based off total similarity score
* Use of stable roommates algorithm


## How to Build and Run

* To build: mvn package in the top-level directory
* To run: run ./run --gui from the top-level directory and yarn start from inside /frontend (see README in /frontend)
* To run java tests: mvn test

## Main Pages

### Login/Sign Up
![image](https://user-images.githubusercontent.com/32823187/115271226-1c832100-a10b-11eb-8ca5-d6c0eee5bb53.png)

#### Edit Your Profile
![Screen Shot 2021-04-19 at 12 32 59 PM](https://user-images.githubusercontent.com/32823187/115272748-c1eac480-a10c-11eb-9b9e-c5cced3dcba6.png)

### Homepage
![Screen Shot 2021-04-19 at 12 37 11 PM](https://user-images.githubusercontent.com/32823187/115272332-4721a980-a10c-11eb-9955-b9f08f0488e0.png)

### Browse Surveys
![Screen Shot 2021-04-19 at 12 34 23 PM](https://user-images.githubusercontent.com/32823187/115272429-64567800-a10c-11eb-856c-b07da164a927.png)


Click on a survey to fill it out!

![Screen Shot 2021-04-19 at 12 34 44 PM](https://user-images.githubusercontent.com/32823187/115272521-7b956580-a10c-11eb-81e1-6ec9b8fded5f.png)


When the creator of the survey generates the pairs, check who you have been paired with!
![Screen Shot 2021-04-19 at 12 38 48 PM](https://user-images.githubusercontent.com/32823187/115272624-9c5dbb00-a10c-11eb-9d76-1ec5c16b57e3.png)


