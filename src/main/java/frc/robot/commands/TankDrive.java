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
    // Invert cause my controller is weird
    double leftStickY =  -RobotContainer.GetDriverRawAxis(Constants.LEFT_STICK_Y);
    double rightStickX = -RobotContainer.GetDriverRawAxis(Constants.RIGHT_STICK_X);

    Robot.driveTrain.arcadeDrive(leftStickY, rightStickX);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.driveTrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
