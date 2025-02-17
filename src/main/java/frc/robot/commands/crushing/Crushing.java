// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.crushing;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class Crushing extends Command {
    CANSparkMax motor;
    public Timer timer;
    public boolean forward;

    /** Creates a new Crushing. */
    public Crushing(boolean forward=true) {
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(Robot.crushingSubsystem);
        this.motor = RobotContainer.crushingMotor;
        timer = new Timer();
        timer.reset();
        timer.start();
        this.forward = forward;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        System.out.println("Running Crushing Command");
        RobotContainer.Crushing = true;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        motor.set((forward ? 1: -1)/Constants.Robot.SPEED_DIVIDER);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        motor.set(0);
        RobotContainer.Crushing = false;
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return (timer.get() > Constants.CRUSHING_TIME) || !RobotContainer.led.get();
    }
}
