package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.base.RobotMap;
import edu.wpi.first.wpilibj.superclasses.CommandRC;

/**
 *
 * @author Author
 */
public class CommandTemplate extends CommandRC {

    public CommandTemplate(int requiredSystem) {
        super(requiredSystem);
    }
    
    //Set up what the robot will do while this command is running.
    protected void execute() {
    }

    //Determine the conditions that will stop this command.
    protected boolean isFinished() {
        return false;
    }    
}