// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.driving;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class TankDrive extends Command {

    private DifferentialDrive driveTrain = new DifferentialDrive(Robot.motorControl::setLeftMotors, Robot.motorControl::setRightMotors);
    
    /** Creates a new TankDrive. */
    public TankDrive() {
        addRequirements(Robot.motorControl);
        SmartDashboard.putData(this);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        //SmartDashboard.putData(driveTrain);

        double rotation;
        double speed;
        if (RobotContainer.is_keyboard()) {
            rotation = RobotContainer.GetDriverRawAxis(Constants.KEYBOARD_X);
            speed =    RobotContainer.GetDriverRawAxis(Constants.KEYBOARD_Y);
        } else {
            rotation = RobotContainer.GetDriverRawAxis(Constants.RIGHT_STICK_X);
            speed =    RobotContainer.GetDriverRawAxis(Constants.LEFT_STICK_Y);
        }
        
        // Inverted cause
        driveTrain.arcadeDrive(-speed, -rotation);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        driveTrain.stopMotor();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
