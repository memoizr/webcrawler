This was fun. I actually enjoyed doing this.

to compile just run `./gradlew build jar`, then run the actual program `java -jar 1account-test-1.0.jar`


I used TDD to develop this, but I didn't spec out all scenarios for the sake of sticking to the time budget.
Also, there's no error handling.
Also, there's no proper detection of async libraries.
But it should illustrate my style; I left many quirky comments throughout, basically some of the stuff I'd say if we were pairing on this.