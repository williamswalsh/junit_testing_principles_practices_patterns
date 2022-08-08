### Identifying code to refactor

Can categorise code into 2 dimensions:
- Complexity/Domain Significance
- Num of collaborators

#### Complexity
<pre>
  cyclomatic complexity = 1 + (number of branching points)
  
  if ( predicateA )
  -> 1 branching point
  -> cyclomatic complexity = 2 = 1 + 1 branching point

  if ( predicateA && predicateB )
  -> 2 branching points
  -> cyclomatic complexity = 3 = 1 + 2 branching points
</pre>

#### Number of collaborators
A collaborator is a dependency that is mutable or out-of-process or both.  
Code with a lot of collaborators is expensive to test.  
The domain classes should only work with "in process" dependencies.  

4 types of code can be derived from these two dimensions: 
- Domain model, algorithms - 
- Trivial code - getters, setters, etc.
- Controllers - co-ordinates work of other components - high num of collabs
- Overcomplicated code - fat controllers - which do all work don't delegate

The more complex the code the fewer collaborators it should have.


#### Humble Object Pattern
