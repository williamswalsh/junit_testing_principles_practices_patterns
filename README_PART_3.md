#### Test Double types
- mocks:
  - mock - A mock created with a mocking framework. Can introspect.
  - spy  - A manually created mock
- stubs:
  - stub - A dependency that you configure to return different values based on scenario. Doesn't introspect. 
  - dummy - A simple hardcoded value e.g. null, "My String" etc.
  - fake  - implemented to replace a dependency that doesn't yet exist

#### Incoming vs Outgoing interactions
Mocks perform and examine Outgoing interactions.  
They control and verify the output.  
e.g. Mocks will "mock" data leaving mocked methods.

Stubs perform incoming interactions.  
They control the input.  
e.g. Stubs will provide data to a SUT for processing. 



#### Over specification
Verifying things that aren't part of the end result.  
e.g. Asserting interactions with stubs

#### Black box testing
Examines the functionality of a system without knowing its internal structure.  
Resistant to refactoring as it only verifies the outcome of the test.

#### White box testing
Method of testing that verifies the apps inner workings.  
Coupling to implementation is a problem with this method of testing.  
Code coverage tools examine in a "white box" manner.  
The best strategy is to analyze tests using white box(code coverage, etc.)
and use back box to create a test.

#### Test Pyramid
This is a pyramid which advocates for a specific ratio of tests types.
<pre>
          ^
        /    \
       /      \
      /        \
     / end-2-end\
    /------------\
   /  integration \
  /----------------\
 /     unit         \
----------------------
</pre>