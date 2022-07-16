# JUnit Testing book - personal samples

Unit tests should act as a safety net. \
Unit tests should verify the correctness of an application. \
The correctness of an application is defined from the requirements. \
Code is a liability not an asset, less of it is better. \
Unit tests class test a SUT(System Under Test) i.e. a class. \
Unit tests need to prepare collaborators which collaborate with the SUT.
A single unit test method only tests a MUT(Method Under Test) i.e. method of a class.


### Code quality indicators
- Unit tests are a good negative indicator. \
  This means is that if no tests exist, that the code quality is certainly bad.  
- Unit tests are a bad  positive indicator. \
  This means is that if a lot of tests exist, their presence alone doesn't mean that the code quality is good.
- The same applies for code coverage. \
  If there is no code coverage the code quality is certainly bad. \
  If there is high code coverage the code quality is not certainly good. \
  A test suite that provides 100% coverage can be of poor quality. :-) LOL!

### The cost of a unit test:
- The time it takes to run the test, against each code change
- Dealing with false alarms raised by the test
- The time spent reading the test to understand the behaviour
- The time spent refactoring the test when the behaviour is refactored

### Coverage Metrics
<pre>
                  Lines of code executed by the test suite
  Code Coverage = ---------------------------------------
                          Total lines of code

                  Branches traversed by the test suite
Branch Coverage = ------------------------------------
                        Total number of branches
</pre>

Code   coverage ratio amount can be gamed easily. \
Branch coverage ratio amount can't to game. \
Consider these two code fragments, for the case that the condition is always true:

    if (condition) {
        good();
    } else {
        bad();
    }
    vs: 
    if (condition) {good();} else {bad();}
Both cases have a branch coverage of 50%. \
The second case has line coverage of 100%, the first case less. \

NB: Coverage metrics don't guarantee that the underlying code is tested, \
only that it has been executed. \
Assertions are important as they constrain the outcomes to the expected requirements. \
A test without asserts will always pass. :-D 

#### Test Double
This is an object that looks and behaves like its release intended counterpart, \
but it is actually simplified for testing purposes.

#### Mock
A mock is a special kind of "test double" that allows you to examine interactions between \
the system under test and its collaborators.


#### AAA
- Arrange the dependencies
- Act out the action under test
- Assert the expected outcome

#### Styles of Testing
- Classical style - instantiates all collaborators
- London style - using mocks to mock collaborators