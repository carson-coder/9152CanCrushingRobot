// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.TankDrive;
import frc.robot.commands.TestModeCommand;

public class MotorControl extends SubsystemBase {
    private CANSparkMax motorLeft1 = new CANSparkMax(Constants.Robot.MOTOR_LEFT_1_ID, MotorType.kBrushless);
    private CANSparkMax motorLeft2 = new CANSparkMax(Constants.Robot.MOTOR_LEFT_2_ID, MotorType.kBrushless);
    private CANSparkMax motorRight1 = new CANSparkMax(Constants.Robot.MOTOR_RIGHT_1_ID, MotorType.kBrushless);
    private CANSparkMax motorRight2 = new CANSparkMax(Constants.Robot.MOTOR_RIGHT_2_ID, MotorType.kBrushless);


    private TankDrive tankDrive;

    /** Creates a new DriveTrain. */
    public MotorControl() {
        SmartDashboard.putData(this);
        
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
        motorLeft1.set(speed/Constants.Robot.SPEED_DIVIDER);
        motorLeft2.set(speed/Constants.Robot.SPEED_DIVIDER);
        SmartDashboard.putNumber("Left Motor Speed", speed);
    }

    public void setRightMotors(double speed) {
        motorRight1.set(speed/Constants.Robot.SPEED_DIVIDER);
        motorRight2.set(speed/Constants.Robot.SPEED_DIVIDER);
        SmartDashboard.putNumber("Right Motor Speed", speed);
    }

    public Command testModeCommand() {
        return new TestModeCommand(motorLeft1, motorLeft2, motorRight1, motorRight2);
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.setSmartDashboardType("SwerveDrive");
        builder.setActuator(true);
        builder.addDoubleProperty("Front Left Velocity", () -> {return motorLeft1.get() * Constants.Robot.SPEED_DIVIDER   * 2;}, null);
        builder.addDoubleProperty("Back Left Velocity", () -> {return motorLeft2.get() * Constants.Robot.SPEED_DIVIDER    * 2;}, null);
        builder.addDoubleProperty("Front Right Velocity", () -> {return motorRight1.get() * Constants.Robot.SPEED_DIVIDER * 2;}, null);
        builder.addDoubleProperty("Back Right Velocity", () -> {return motorRight2.get() * Constants.Robot.SPEED_DIVIDER  * 2;}, null);

        builder.addDoubleProperty("Front Left Angle", () -> {return 0;}, null);
        builder.addDoubleProperty("Back Left Angle", () -> {return 0;}, null);
        builder.addDoubleProperty("Front Right Angle", () -> {return 0;}, null);
        builder.addDoubleProperty("Back Right Angle", () -> {return 0;}, null);

        builder.addDoubleProperty("Robot Angle", () -> {return 0;}, null);
    }
}
