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

#### Regression
- A software bug which occurs when a feature stops working due to a modification to existing code.

#### Refactoring
Changing existing code without modifying its behaviour.  
Normally done to improve non-functional characteristics:
- increase readability
- reduce complexity
