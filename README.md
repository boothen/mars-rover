# Mars Rover
Mars Rover - technical assignment for LME

## Requires
* Java 17

## Build and Execute Tests

> ./gradlew clean build

## Notes and considerations.

`RobotProgram` is the main entry point for exercise. It takes the input instruction set and returns the generated output.

I've used JUnit tests to verify each class. The `RobotProgramTest` verifies the provided input and output. Additional input/output instructions could be added and verified here.

I've assumed that if `North` is `(x, y+1)` then `East` is `(x+1, y)`.

`The maximum value for any coordinate is 50.` - I've determined to be a requirement that is validated when Mars is created. 
If a size of greater than 50 is passed then an IllegalArgumentException is thrown.

`All instruction strings will be less than 100 characters in length.` - From the wording of this statement I've assumed this is just a fact 
rather than a requirement to be validated.

`There is also a possibility that additional command types may be required in the future and
provision should be made for this.` - to facilitate this, I've mapped a `RobotInstruction` to a `Command`. 
Although, it's easy to determine what a new `RobotInstruction` will be, if we take an example of adding a Backwards command. The following steps are required.

1. The new Instruction code `B` should be added to RobotInstruction.
2. Update an existing or add a new class `Command` class in `Robot`.
   * For a Backwards instruction the `MoveCommand` could be updated to pass the instruction to `RobotOrientation.move()`. Backwards could be implemented in `RobotOrientation.move()` by determining the opposite orientation from the current orientation and calculating the move `RobotLocation` from that.
3. Map the new `RobotInstruction` to the `Command` in `INSTRUCTION_COMMAND_MAP` in `Robot`.
