// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms ofde
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.driving.TankDrive;
import frc.robot.commands.driving.TestMode;

public class MotorControl extends SubsystemBase {

    private TankDrive tankDrive;

    /** Creates a new DriveTrain. */
    public MotorControl() {
        SmartDashboard.putData("Motor Control", this);
    }

    // Init function to make command that require this subsystem
    public void init() {
        tankDrive = new TankDrive();
        this.setDefaultCommand(tankDrive);
    }
    
    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public void setLeftMotors(double speed) {
        RobotContainer.motorLeft1.set(speed/Constants.Robot.SPEED_DIVIDER);
        RobotContainer.motorLeft2.set(speed/Constants.Robot.SPEED_DIVIDER);
    }

    public void setRightMotors(double speed) {
        RobotContainer.motorRight1.set(speed/Constants.Robot.SPEED_DIVIDER);
        RobotContainer.motorRight2.set(speed/Constants.Robot.SPEED_DIVIDER);
    }

    public Command testModeCommand() {
        TestMode cmd = new TestMode();
        return cmd;
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.setActuator(true);
        
        builder.addStringProperty("SwerveDrive/.type", () -> {return "SwerveDrive";}, null);
        builder.addDoubleProperty("SwerveDrive/Front Left Velocity", () -> {return RobotContainer.motorLeft1.get() * Constants.Robot.SPEED_DIVIDER   * 2;}, null);
        builder.addDoubleProperty("SwerveDrive/Back Left Velocity", () -> {return RobotContainer.motorLeft2.get() * Constants.Robot.SPEED_DIVIDER    * 2;}, null);
        builder.addDoubleProperty("SwerveDrive/Front Right Velocity", () -> {return RobotContainer.motorRight1.get() * Constants.Robot.SPEED_DIVIDER * 2;}, null);
        builder.addDoubleProperty("SwerveDrive/Back Right Velocity", () -> {return RobotContainer.motorRight2.get() * Constants.Robot.SPEED_DIVIDER  * 2;}, null);

        builder.addDoubleProperty("SwerveDrive/Front Left Angle", () -> {return 0;}, null);
        builder.addDoubleProperty("SwerveDrive/Back Left Angle", () -> {return 0;}, null);
        builder.addDoubleProperty("SwerveDrive/Front Right Angle", () -> {return 0;}, null);
        builder.addDoubleProperty("SwerveDrive/Back Right Angle", () -> {return 0;}, null);

        builder.addDoubleProperty("SwerveDrive/Robot Angle", () -> {return 0;}, null);
        
        builder.addDoubleProperty("Left Speed", RobotContainer.motorLeft1::get, this::setLeftMotors);
        builder.addDoubleProperty("Right Speed", RobotContainer.motorRight1::get, this::setRightMotors);
    }
}
