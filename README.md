# JUnit Testing book - personal samples
Unit tests should act as a safety net.  
Unit tests should verify the correctness of an application.  
The correctness of an application is defined from the requirements.  
N.B.: Code is a liability not an asset, less of it is better.  
Unit tests class test a SUT(System Under Test) i.e. a class.  
Unit tests need to prepare collaborators which collaborate with the SUT.  
A single unit test method only tests a MUT(Method Under Test)  
i.e. method of a class.  
If you have a large complicated class graph, you should break it down.  
Avoid if statements in tests.

### Code quality indicators
- Unit tests are a good negative indicator.  
  This means is that if no tests exist, that the code quality is certainly bad.  
- Unit tests are a bad  positive indicator.  
  This means is that if a lot of tests exist, their presence alone doesn't mean that the code quality is good.
- The same applies for code coverage.  
  If there is no code coverage the code quality is certainly bad.  
  If there is high code coverage the code quality is not certainly good.  
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

Code   coverage ratio amount can be gamed easily.  
Branch coverage ratio amount can't to game.  
Consider these two code fragments, for the case that the condition is always true:

    if (condition) {
        good();
    } else {
        bad();
    }
    vs: 
    if (condition) {good();} else {bad();}
Both cases have a branch coverage of 50%.  
The second case has line coverage of 100%, the first case less.  

NB: Coverage metrics don't guarantee that the underlying code is tested,  
only that it has been executed.  
Assertions are important as they constrain the outcomes to the expected requirements.  
A test without asserts will always pass. :-D 

### Unit Test
- verifies a small piece of code(a unit)
- does it quickly(relative term) - this can lead to mocking of out-of-process dependencies.
- does it in an isolated manner i.e. a unit test should not be coupled to other tests.
  N.B.: You should be able to run the unit tests in parallel.  
- Over-specification is an issue - as you can couple the test to the implementation detail.

### Unit Test Naming
- don't follow rigid guidelines like "<method_name>__<scenario>__<expected_result>"
- name test as if you were describing it to a non-programmer, who is familiar with the problem domain.
- separate words with underscores
- The name should be a statement of fact, it should not contain should, would or could
- don't include MUT in test name - you don't test classes - you should test behaviour
  - You should target "the behaviour" not "the code".
- You can include basic english grammar 


### Integration Test
- An integration test verifies >1 unit.
- An integration test is an automated test which doesn't meet one of the 3 criteria of a unit test:  
  - London Style
    - verifies a single unit(a class)
    - does it quickly
    - does it in isolation from other units(classes)
  - Classical Style
    - verifies a single unit of behaviour (1 or more classes)
    - does it quickly
    - does it in isolation from other tests
- Normally requires a teardown phase - due to presence of out-of-process dependencies 

### Two Schools of testing
Both schools follow the same definition of a unit test.  
They differ in how the point on isolation is interpreted 
- Classical
  - Verifies a single "unit of behaviour".  
    e.g. When I call my dog, he comes to me
  - Unit tests are isolated from other tests.
  - A unit test is a class or a group of classes.
  - Only shared dependencies are mocked.
  - Normally multiple tests can fail, if one fails - must troubleshoot to find the source of issue.
  - Uses test doubles for shared dependencies
  
- London(mocking)
  - Unit tests are isolated from their collaborators.
  - Verifies a single "unit of code" (overly concerned with implementation)
    e.g. When I call my dog, he raises his left leg, then his right leg
  - a unit test is a class.
  - All collaborators are mocked.
  - Normally the failing class is obvious - only single point of failure 
  - Uses test doubles for all but immutable dependencies


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


- **Private & Immutable dependency - Value Object**
  - e.g. 5


- **Collaborator**
  - Is either shared or mutable


- **Test Double**
  - This is an object that looks and behaves like its release intended counterpart,  
  but it is actually simplified for testing purposes.


- **Mock**
  - A mock is a special kind of "test double" that allows you to examine interactions between  
  the system under test and its collaborators.


- **End-to-end test**
  - A subset of an integration test.
  - Normally UI based.
  - Normally includes out-of-process dependencies.
  - You should run these tests last, after unit & integration.


- **Test Fixture**
  - An object that the test "runs against".
  - This object needs to be in a **known fixed state**. (**fix**-ture)


#### AAA - Arrange, Act, Assert pattern
- **A**rrange the dependencies
  - If this section is too large, you can move the code into a private method in class  
    **OR** you can create a separate factory class.
  - Patterns: Object mother & Test Data Builder.
  - Sharing of dependency code can lead to high coupling between tests & reduces readability.
- **A**ct out the action under test
  - This section is normally a single line of code. 
  - If this section is multiple lines it could indicate a problem with the public interface of the SUT.
  - You may require the client to make 2 calls in a specific sequence to do 1 task - confusing. 
- **A**ssert the expected outcome
- Given-When-Then pattern is similiar to **AAA**
- In **TDD**, you would start off at the Assert section.
  - This is where you state what you expect from the code.
  - What is the objective?
- Can put comment before each section to distinguish it: // Arrange // Act // Assert
  - Or just separate with empty lines


#### Anti-patterns
- Having an if statement in a test - there should be no branching.
  - Break down test into each branch.
  - If statements make tests harder to understand.
- Having an Act section of a test with more than 1 line.
- High coupling between unit tests - by way of combining the startup code without creating private well-named methods
  - e.g. names like createStoreWithInventory(Product.X, 5), createCustomer()
  - These methods can have parameters to make them more generic to allow them to be reused across tests 
- Asserting interactions with stubs - fragile tests
- Leaking implementation details into the apps public API
- Code Pollution - adding code whose sole purpose is to enable or simplify a unit test.


#### Invariant Violation
When you purchase an item multiple things occur.
- the item stock is reduced to a valid amount
- the item is paid for  

Both these tasks have to occur for a purchase to occur.  
Therefore they should be placed into a wrapper method like purchase(ITEM.CAR, 1).  
There shouldn't be 2 separate calls ->reduceStockOfItem() then payForItem() exposed to the client.  
You shouldn't allow the client to make a mistake.   
You shouldn't rely on the client to not make a mistake.    
What happens when one of the methods isn't called.  
This leads to corrupt/inconsistent data in system.  

#### Parameterised Tests
Use these when the only difference is between existing tests is a variable value.
Can group tests using parameterised tests.  
Can pass in input and expected outcome(true|false) into parameterised test.  
However passing the outcome can make thinks harder to understand.  
So a compromise would be to group all failing tests and only pass in the failure causing parameters.
Also group all succeeding cases.