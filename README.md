# app

## projectDSG
**DSG**: **D**uckhacks for **S**ocial **G**ood

## Inspiration
Due to the ongoing pandemic, there is a need for communication between individuals to share ideas and vital information regarding movements such as Black Lives Matter, while still being able to respect peoples' boundaries and social distance.

Another issue regarding information spreading is the need for a large following in order for an individual's voice to be heard. Right now, the main method of information sharing revolves around social media and interacting with followers. There are some people who have a lot of valuable information to share, yet it will not get far if they have a handful of followers.

## What it does
To solve the issue of reaching a wider audience while also managing to stay at a safe distance and limit interaction, projectDSG allows people to write down their ideas and information regarding protests, petitions, charities, and local businesses which will then be nicely packaged into a QR code that other people at a protest or gathering can scan, if they wish to become more actively engaged.

## How we built it
projectDSG was built using Android Studio, Java, Pastebin API -- Java, and goQR.me. 

## Challenges we ran into
Initially, the project started out with an idea of modifying the capabilities of contact tracing to be reworked for information sharing purposes, however, due to a lack of experience with Bluetooth Low Energy, we soon found integrating contact tracing into this project to be out of our reach given the time constraints. We later settled for the similar contactless information sharing method of QR codes.

Neither of us had knowledge on how to integrate an API within an app, so alongside trying to translate our idea into a streamlined user interface, we were met with issues of integrating the Pastebin API and attempting to modify it to fit our needs.

## Accomplishments that we're proud of
We have a functioning app that is capable of sharing information through the use of QR codes!
The app was developed in a manner that allows us to build upon the design and expand for future versions.

## What we learned
After much trial and error, we both have a better understanding of how APIs can be used and implemented to vastly improve the functionality of our app.
Adapting to a given situation and figuring out a different means of accomplishing our initial goal.

## What's next for projectDSG
Revisit the core sharing functionality and implement BLE to passively communicate with other devices nearby and share the information without having to interact with anyone. With this, hopefully, the information would be shared among users at a much faster rate as it will have the telephone effect of passing information over longer distances through the use of chaining phone connections.
Modify the information fields to be less constrictive and give the user more freedom in terms of what information they might want to share.
Look into a server that would be able to host the information temporarily for a maximum of 12 hours instead of relying on Pastebin or another file hosting site to hold the user's information.
Figure out a way to moderate content and filter our spam and misuse of the application.

##Topic
Education
