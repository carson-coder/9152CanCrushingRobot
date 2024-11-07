// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.TankDrive;

public class DriveTrain extends SubsystemBase {
  private CANSparkMax motorLeft1 = new CANSparkMax(Constants.Robot.MOTOR_LEFT_1_ID, MotorType.kBrushless);
  private CANSparkMax motorLeft2 = new CANSparkMax(Constants.Robot.MOTOR_LEFT_2_ID, MotorType.kBrushless);
  private CANSparkMax motorRight1 = new CANSparkMax(Constants.Robot.MOTOR_RIGHT_1_ID, MotorType.kBrushless);
  private CANSparkMax motorRight2 = new CANSparkMax(Constants.Robot.MOTOR_RIGHT_2_ID, MotorType.kBrushless);

  private DifferentialDrive driveTrain = new DifferentialDrive(this::setLeftMotors, this::setRightMotors);
  
  /** Creates a new DriveTrain. */
  public DriveTrain() {
    
  }

  public Command createTankDrive() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return new TankDrive();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SendableRegistry.addChild(driveTrain, motorLeft1);
    SendableRegistry.addChild(driveTrain, motorLeft2);
    SendableRegistry.addChild(driveTrain, motorRight1);
    SendableRegistry.addChild(driveTrain, motorRight2);
    SmartDashboard.putData("Robot", driveTrain);
  }

  public void setLeftMotors(double speed) {
    motorLeft1.set(speed);
    motorLeft2.set(speed);
    SmartDashboard.putNumber("Left Motor Speed", speed);
  }

  public void setRightMotors(double speed) {
    motorRight1.set(speed);
    motorRight2.set(speed);
    SmartDashboard.putNumber("Right Motor Speed", speed);
  }

  public void arcadeDrive(double speed, double rotate) {
    driveTrain.arcadeDrive(speed, rotate);
  }

  public void stop() {
    driveTrain.stopMotor();
  }
}
