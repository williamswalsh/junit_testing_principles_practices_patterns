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

### Unit Test definition
- verifies a small piece of code(a unit)
- does it quickly(relative term) - this can lead to mocking of out-of-process dependencies.
- does it in an isolated manner i.e. a unit test should not be coupled to other tests.
  N.B.: You should be able to run the unit tests in parallel.  

### Two Schools of testing
Both schools follow the same definition of a unit test. \
They differ in how the point on isolation is interpreted 
- Classical
  - Unit tests are isolated from other tests.
  - A unit test is a class or a group of classes.
  - Only shared dependencies are mocked.
  
- London(mocking)
  - Unit tests are isolated from their collaborators.
  - a unit test is a class.
  - All collaborators are mocked.


### Definitions

#### Dependency types
- **Shared dependency**
  - When a dependency is shared between unit tests(not SUTs). 
  - This is when one unit test can influence the outcome of another test.
  - This can occur when a database is written to by one test affecting the outcome of another.
  - If the database is read only, there can be no writes, therefore no communication between unit tests.
  - e.g. a mutable database, a mutable file system, a static mutable variable shared between MUT(methods under test). 


- **Private dependency**
  - A dependency that is not shared.


- **Out-of-process dependency**
  - A dependency that runs outside the app's execution process.
  - e.g. database


- **Volatile dependency**
  - Displays non-deterministic behaviour - they provide different results on each invocation.
  - e.g. rand num generator, class returning a datetime
  - Must set up and config a runtime environment?
  - A DB is a shared and a volatile dependency.


- **Immutable dependency**
  - e.g. the number 5 or an enum value.
  - Every number 5 is identical and so can be "shared" - can be used **interchangeably**.
  - They are identified "by their content". 
  - No need to mock as the actual value can be used.


**Private & Immutable dependency - Value Object**
- e.g. 5


**Collaborator**
- Is either shared or mutable
- 

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
- 
#### Styles of Testing
- London style - Uses test doubles for all but immutable dependencies
- Classical style - Uses test doubles for shared dependencies
