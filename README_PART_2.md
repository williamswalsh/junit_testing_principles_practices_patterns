#### Four pillars of a good unit test
- Protection against regressions
- Resistance to refactoring
- Fast feedback
- Maintainability

#### Protection against regressions
Must protect from regressions in order to sustain project growth.
- How much code is executed during the test?
  - More code -> more potential for regressions
- How complex is that code?
- What is the domain significance of the code?

#### Resistance to refactoring
The degree to which the test can sustain a refactoring of the SUT code without turning red.  
Red Flag: when you refactor the code, the behaviour still works, but the test now fail.  
Also called a False-Positive.  
The more a test is coupled to the underlying implementation of the SUT, the more false positives which will occur.  
N.B. You must decouple the test from the underlying implementation.  
The test should verify the end result the SUT delivers, not the steps required to do so.  
Focus on the outcome.  
The test should tell a story about the problem domain.  
Aim at the result instead of the implementation details

#### Fast feedback
#### Maintainability

#### Categorizing test types by the four pillars
- End-to-end tests
  - Test all code so they protect against regressions
  - Normally focus on the outcome from the end users point of view -> resistant to refactoring  
  - They are SLOW
- Trivial test - test that is so trivial it is unlikely to ever break
  - e.g. testing a setter or getter of a class member
  - They are FAST
  - Unlikely to reveal any regression
  - Unlikely to produce false positive as its so simple -> Good resistance to refactoring
- Brittle test - throws a lot of false positives
  - Runs fast
  - It can't withstand a refactoring of the SUT.
  - Good chance of catching a regression
    - but does so with a lot of false positives

### Definitions

#### CAP Theorem - Similar to 3 of the 4 pillars
- It is impossible for a distributed datastore to provide more than 2 of the following 3 guarantees:
  - C - Consistency - Every read receives the latest write or an error.
  - A - Availability - every request receives a response.
  - P - Partition Tolerance - system continues to operate despite network partitioning(losing connections between nodes).

#### Test Accuracy
<pre>
                   Number of bugs found             
  Test accuracy = ----------------------
                  Number of false alarms
</pre>

#### Test Value Estimate - using four pillars
<pre> 
     Test value = [0-10] * [0-10] * [0-10] * [0-10]
</pre>

#### Regression
- A software bug which occurs when a feature stops working due to a modification to existing code.

#### Refactoring
Changing existing code without modifying its behaviour.  
Normally done to improve non-functional characteristics:
- increase readability
- reduce complexity
