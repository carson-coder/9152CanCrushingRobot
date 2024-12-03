// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.crushing.Lights;

public class LightsSubsystem extends SubsystemBase {
  /** Creates a new LightsSubsystem. */
  public LightsSubsystem() {}
  
  public void init() {
    this.setDefaultCommand(new Lights());
    SmartDashboard.putData("Crushing Subsystem", this);
  }

  @Override
  public void periodic() {
    SmartDashboard.putData("Crushing Subsystem", this);
  }
}
