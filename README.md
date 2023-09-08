# TransfermateTest
All steps are done:
1. Creating test cases (positive-happyflow/negativeflow)
2. Execute by automation test cases from point 1
3. Activate account with Email and SMS text.

Framework created with Java Selenium and Cucumber BDD tool

Framework designed following POM model with Page Factory.
In TransfermateTests.class I'm using @Ignore because I'm assuming you will run the test by Cucumber, and I don't want the test to be run twice from command-line.

ExtentReport will generate reports with screeenshot on fail, only if you run the the tests from TransfermateTests.class

![image](https://user-images.githubusercontent.com/123422575/214205514-37c05e34-b8c4-461b-b90b-2a6c02441534.png)


Cucumber reports will generate reports with screenshot on fail, if you run it from TestRunner and the Registration.feature

![image](https://user-images.githubusercontent.com/123422575/214204851-a3ee5900-a994-401c-b6fd-70b4720f2f8a.png)

Framework also supports command line execution for cucumber and also the Transfermate.class

-> Open cmd and type cd /path/to/project
type: mvm clean test
type: mvn verify -DskipTests 

And it will generate a more detailed report for Cucumber tests like in the pictures below:

![image](https://user-images.githubusercontent.com/123422575/214206491-fa3a4fe0-775c-49f1-b46c-f6dc4c92b110.png)
![image](https://user-images.githubusercontent.com/123422575/214206511-232345ef-007e-4ac0-b813-d44df688c872.png)
![image](https://user-images.githubusercontent.com/123422575/214206548-b38d8a79-b987-4dbf-b953-9416f0045efc.png)





