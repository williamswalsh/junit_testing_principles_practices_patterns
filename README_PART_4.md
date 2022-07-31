#### Hexagonal Architecture concept

An app consists of 2 layers:
- the domain layer
  - This is where all the business logic is contained
  - There can be multiple domain classes which are dependent on each other.
- the application services layer
  - This is where the app communicates with out-of-process dependencies e.g. db, email, s3, msg bus, q
  - No domain logic here
  - This layer should depend on classes in the domain layer

There is a separation of concerns between domain other parts of app.  
Business logic should be exempted from all other responsibilities.  

Classes in the domain should not depend on classes in the app services layer.  
The domain layer should be isolated from the external world.

Communication with other applications is handled by the app services layer.

#### Tracing requirements down to class level

You start off with a business use case.  
Then you convert this task into subtasks, each fulfilling a distinct subtask.  
**NB:** You should be able to trace a test back up to the business requirements.

For a domain class the client is the application service.  
For an application service the client is the external user.  
#### Inter-system and Intra-system communications

Intra-system communications are implementation detail, 
therefore mocking domain classes reduces resistance to refactoring.  
You should use stubs here.  
Inter-system communications are not implementation details.

If an out-of-process dependency (like a db) is only accessed by a single app then it is considered private(not shared).  
Communications with this system is not part of the systems observable behaviour.  
It is therefore an implementation detail and should not be mocked.  
For an out-of-process private dependency there is no need to consider backward compatibility with other systems as it is not part of the systems observable behaviour.  

#### Mocks
Mocks are said to verify behaviour, they don't.  
Mocks verify implementation details between classes.  
Mocks emulate and examine implementation details.  

#### Code can be split into 4 
- public API - observable behaviour
- private API - observable behaviour
- public API - implementation detail
- private API - implementation detail

#### Styles of unit tests
- output based
  - You provide an input, and you verify the output from the SUT
  - This approach assumes there are no side effects
  - also called functional testing - side-effect-free programming
  - e.g. assertEquals(5, returnedResult)
- state based
  - verifying the state of the system after an operation
  - tests verify the "final" state
  - not verifying the return value(functional testing)
  - e.g. assertEquals(1, sut.productsList.length)
- communication based
  - mocks used to verify interactions between SUT and collaborators
  - e.g. verify(x.sendGreetingEmail(anyString())).called(Times.ONCE)

Output based produces tests of the highest quality.  
State based is 2nd best.  
Comm. based should only be used occasionally.  

