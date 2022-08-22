### Unit Testing anti-patterns
An anti-pattern is a common solution to a recurring problem that looks appropriate on the surface but leads to problems further down the road.

#### Unit testing private methods
You shouldn't do this.You should only test observable behaviour.  
If you are not testing the observable behaviour then you are testing implementation details.  
This will lead to bad resistance to refactoring.  
The test will turn red when a refactor occurs of the underlying behaviour/implementation details.  
**N.B.** You should test the implementation details indirectly as part of the overarching behaviour.

The only reason that testing private methods is bad is because the private method is a proxy to an implementation detail.

If you find a private method that is too complex to test it could be:
- its dead code: that can't be controlled by the inputs and may require removal
- there is a missing abstraction - the private method could be extracted into its own class 

**Q:** When is testing private methods acceptable?  
**A:** There are times when it is acceptable. When a member is private and is part of the observable behaviour.  
e.g. private constructor used by out-of-process dependency hibernate ORM.

If you want to test a private method you can use reflection to facilitate the calling of private members.

#### Exposing private state to enable testing
- Exposing private state just for unit testing.
- Your tests should interact with SUT in same way that production does.  
  Your tests shouldn't have special privileges.
- **N.B.** Widening the public API surface for the sake of testability is a bad practice.

#### Leaking domain knowledge to tests
You don't want to duplicate business logic in the tests.  
You don't want the tests to contain the algorithms that the SUT performs.  
You should hardcode the expected results, the results should be gathered with a domain expert.  
This is an example of coupling to the implementation detail.

**N.B.**   
If you are refactoring a legacy application, you can precalculate the output using the legacy code to create the expected values for the tests.

#### Code pollution
Adding code to production code base that is only needed for testing.  
e.g. isTestEnv() boolean  
Can use interfaces to replace this duplicate behaviour.  



