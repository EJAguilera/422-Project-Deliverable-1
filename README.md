# 422-Project-Deliverable-3
Task items completed for CPTS 422 Project Deliverable 3, submitted by Elisha Aguilera SID #011745359 for WSUE CPTS 422.
Special Note: Pitclipse will only work with this project if project compiler set to Java SE-11, or at least version older than Java SE-16. 

### Asumptions
- Operators and operands and considered 'flat', as in, 'a + (b + c)' is considered 4 operators counting the '(' and ')' and 3 operands, not 4 operators and 4 operands.
- Operators are exclusively considered elements described in the Checkstyle `TokenType` as 'operators', which excludes loops and function declarations as operators.
- Number of operands depends on the number of operators: most operators will always have at least two operands, with a limit subset having only a single operand.
- Comments include both single-line and end-of-block comments, edge case doesn't include a comment block that opens but never closes.
- Project is configured as a Maven project, but additional files installed by dependency managers are not included in this remote respository. All packages should be specified in the `pom.xml` file.

### Fault Models for Checks
1. Halstead Difficulty:
- 1.1 Operator is for unknown reason ignored and not added to hashtable.
    - Operators are added to hashtable
- 1.2 Operand is for unknown reason ignored and not added to hashtable.
    - Operands are added to hashtable
- 1.3 If unique operands is somehow counted as 0, might have divide-by-zero error.
    - Caught error wherein the operators and operands iterator was looping endlessly
    - Caught another error wherein the integer value was distorting the division

2. Halstead Volume:
- 2.1 Operator is ignored for unknown reason and not added to hashtable
- 2.2 Operand is ignored for unknown reason and not added to hastable.
- 2.3 Volume for unknown reason computed as either 0 or NaN.
    - Caught error where the value was not reset between runs, causing unexpected output

3. Halstead Length:
- 3.1 Valid token is not counted for unknown reason
    - Some operands were ignored despite being valid

4. Halstead Effort:
- 4.1 Effort is not computed correctly
    - Caught issue where the value was not computed correctly.

5. Halstead Vocabulary:
- 5.1 Sum of unique operators, operands is not computed correctly
    - Caught iterator over set error

6. Expression:
- Truly bizarre error, it will run the check with the black-box, but not the white-box coverage test
- 6.1 Expression token is ignored for unknown reason
    - All expression tokens counted
- 6.2 Count is not incremented properly
    - Count is properly incremented

7. GetComments:
- Unfortunately could not figure out appropriate configuration and context for comments, test engine wouldn't run the black-box.
- 7.1 Comment tokens are ignored for unknown reason
- 7.2 Block comment line numbers are not computed corrently,

8. GetOperators:
- 8.1 Operator token is ignored for unknown reason
- 8.2 Operator count is not computed correctly
    - Operators computed correctly, paranthesis included changed count
9. GetOperands:
- 9.1 Operands token is ignored for unknown reason
- 9.2 Operands count is not computed correctly
    - Operands computed correctly

10. Looping Statements:
- 10.1 Looping statements count not computed correctly 
    - The `SLIST` along with the `.getChildCount` was misapplied. Fixed with exclusion of some token-types.

11. Variable Declaration:
- 11.1 Variable definition token not counted for  unknown reasons
- 11.2 Variable definiton count not computed correctly for unknown reasons
    - Both issues resolved by test, evidently any 'x = y' is counted as a declaration, even if it's already declared

#### Class Testing Differences
Were class testing to be applied on the project, each Check as a class would have a sequence of methods to run in a test set. For example, Halstead Volume has the standard `beginTree`, `visitToken` and `finishTree` functions, along with various other functions such as `getVolume`. The difference between this versus standard unit testing is that Classes (or Objects) have sequences of methods that may mutate the state. As such, we can generate a state-chart and a state-sequence-tree from that, and make assertions because on what we expect the state to be after a series of method calls. In this case, if we feed this through a test engine, we could expect the state value to be in 'finish-tree' state whenever the file is finished parsing.