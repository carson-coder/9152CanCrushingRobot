// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class TankDrive extends Command {
  /** Creates a new TankDrive. */
  public TankDrive() {
    addRequirements(Robot.driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Controller Input
    // double leftStickY = RobotContainer.GetDriverRawAxis(Constants.LEFT_STICK_Y);
    // double rightStickY = RobotContainer.GetDriverRawAxis(Constants.RIGHT_STICK_Y);
    //Keyboard Input
    double leftStickY = -RobotContainer.GetDriverRawAxis(Constants.KEYBOARD_Y);
    double rightStickY = -RobotContainer.GetDriverRawAxis(Constants.OperatorConstants.SECOND_PORT, Constants.RIGHT_STICK_Y);

    Robot.driveTrain.setLeftMotors(leftStickY*0.1);
    Robot.driveTrain.setLeftMotors(rightStickY*0.1);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.driveTrain.setLeftMotors(0);
    Robot.driveTrain.setRightMotors(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
