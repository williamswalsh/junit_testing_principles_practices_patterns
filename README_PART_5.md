### Comparing styles of unit testing using 4 pillar concept

#### Protection against regressions
- communication based testing only verifies a thin slice of code
- Other style don't affect this metric 

#### Fast Feedback
- communication based testing can introduce extra latency due to the overhead introduced by mocks
- Other style don't affect this metric

#### Resistance to refactoring - How resistant is the test to changes in the SUT? False positives?
- output based - as this style only tests the output of the method it isn't dependent on the implementation details - so there are no false positives
- state based - this style isn't as resistant as it tests the state of the system after an operation - the state could be implementation detail 
- communication based - worst style for this metric - mocks should only verify observable behaviour as it has to be backward compatible, therefore changes to it are unlikely
  - You can decrease the number of false positives by proper encapsulation and coupling tests to observable behaviour only.

#### Maintainability
- output based - very maintainable - very short tests 
- state based - less maintainable - longer tests and assertion sections - can improve this by adding equals() method to object to reduce to one assertEquals statement or can have helper methods - which also increases complexity(decreasing maintainability)
- communication based - least maintainable - mocking takes a lot of space and understanding.
Can end up with mock chains which are highly complex.


### Functional Programming
This is programming with mathematical functions f(x).  
A mathematical function is a function that doesn't have any hidden inputs or outputs.  
All inputs or outputs must be explicitly express in the method signature.  
N.B.: It always outputs the same output for the same input.  
A mathematical function is also called a pure function.  

#### Referential Transparency
**N.B.:** To identify a pure function, you should be able to replace the call to the function with the return value of the function.  
If the outcome of the code is the same before and after the swap then it is a mathematical/pure function.
<pre>
    int increment(int numToIncrement) {
        return numToIncrement++;
    }

    int y = increment(2);
    int y = 3;
    # here increment is a pure function
</pre>

#### Hidden Input
A reference to internal/external state which isn't present in the method signature contract.  
e.g. private mutable variable, data from database(not in signature)

#### Hidden Output
A side effect is a hidden output.   
An operation creates a side effect when it mutates the state of a class, file, etc.  
Exceptions are hidden outputs.   
They bypass the contract established by the method signature. 
  
#### Functional Architecture
The goal of functional programming is not to eliminate side effects. 
The goal is to push the side effects out to the mutable shell.  
The goal is to introduce a separation between business logic and code that incurs side effects.  
It maximises the code written in a function(immutable) way.  
Immutable means that once an object is created it cannot be changed(mutated).  
The separation is made by separating:
- code that makes decisions(business logic)
- code that acts based on those decisions(service application layer, db interaction, etc.)

A functional system is divided into two components:
- a functional core(immutable) - generates value objects e.g. FileAction
- a mutable shell

The mutable shell gathers all the inputs.  
The functional core decide the outcomes from a decision.  
The mutable shell performs the outcome of the decision which can involve side effects.  
**N.B.** The mutable shell should be "as dumb as possible". It doesn't make any decision.  
It just does what it is told by the functional core.

It is very easy to test as you can strip the mutable shell from the functional core.  
You can use output based testing on the functional core components.  

Exceptions/Errors should be handled by service layer.

A class from the functional core should not work with a collaborator, but with the output of its work, a value.

### Refactoring SUTs and Tests
- Separation of concerns
- Identify roles of application work flow:  
  - Audit Manager -> manges audits
  - Persister -> An Interface which persists data to the underlying storage format
- 



