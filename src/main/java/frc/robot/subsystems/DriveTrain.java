// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.TankDrive;

public class DriveTrain extends SubsystemBase {
  private CANSparkMax motorLeft1 = new CANSparkMax(Constants.Robot.MOTOR_LEFT_1_ID, MotorType.kBrushless);
  private CANSparkMax motorLeft2 = new CANSparkMax(Constants.Robot.MOTOR_LEFT_2_ID, MotorType.kBrushless);
  private CANSparkMax motorRight1 = new CANSparkMax(Constants.Robot.MOTOR_RIGHT_1_ID, MotorType.kBrushless);
  private CANSparkMax motorRight2 = new CANSparkMax(Constants.Robot.MOTOR_RIGHT_2_ID, MotorType.kBrushless);
  
  /** Creates a new DriveTrain. */
  public DriveTrain() {
    motorLeft1.setInverted(true);
    motorLeft2.setInverted(true);
  }

  public Command createTankDrive() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return new TankDrive();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setLeftMotors(double speed) {
    motorLeft1.set(speed);
    motorLeft2.set(speed);
  }

  public void setRightMotors(double speed) {
    motorRight1.set(speed);
    motorRight2.set(speed);
  }
}
