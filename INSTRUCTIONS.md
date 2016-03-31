Hi there!  

This document describes the programming exercise for all Android Engineering candidates at Slack. It's important to understand that this exercise exists in lieu of in-person coding during the onsite interview portion of the interview process. With that in mind, please ensure that the final product is representative of your coding style and the typical way you would approach and solve a coding challenge.

###The exercise
Create an Android app that displays a list of all users on a Slack team using the results of a call to `https://api.slack.com/methods/users.list`. (See below for credentials)

Upon tapping on a user row, the app should open the user's profile. On the individual profile page, you should show, at a minimum, the person's picture, username, real name, and job title. Other profile fields are optional.

###Additional details 
* The exercise should take a good afternoon's worth of work. 
* Some of the fields in the API response may not be used. Some may not even be documented. Use what you need to get the job done.
* The app should work offline from a cold start (e.g. force close and opening the app in airplane mode should still work fine after one previous launch). It is up to you as to how much data to cache and how. The persistence implementation does not matter, but the app should ideally be written in such a way that you could swap out implementations at a later date. In all cases however, the app should not crash if opened while the phone is offline.
* You need only support API 16 and above.
* You are encouraged to use any 3rd party libraries that you deem appropriate. Please provide a brief explanation of why you chose to use each of the libraries you end up using.
* Any design details are also up to you. You will not be intensely scrutinized for design choices (this is an engineering role!), but you should be ready to discuss them.
* The app should look and feel like something you are proud of. Feel free to have some fun and get creative :)  

###Credentials and Team Info 
Use the following API method and API token to complete this task. We've created a team and added some members specifically for this exercise that you can use.

API Documentation: https://api.slack.com/methods/users.list
See also: https://api.slack.com/web#basics
API Token for test team: `xoxp-5048173296-5048487710-19045732087-b5427e3b46`

###Instructions for submitting the exercise
1.  Create a new git repo and Android Studio project, committing with frequency and with the type of commit messages you would write on a typical project. 
2.  Include a README that gives an overview of the project. Anything you think we should know should go here.
3.  Zip up the repo and upload to the link provided in the email that contained these instructions and we will review it as soon as possible. Remember to include all appropriate git directories as well! (e.g `.git`).
4.  We'll build and run your app directly from the repo you provide.

Please let us know if you have any questions!

