// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class TestModeCommand extends Command {
    private CANSparkMax motorLeft1;
    private CANSparkMax motorLeft2;
    private CANSparkMax motorRight1;
    private CANSparkMax motorRight2;

    private final Timer timer = new Timer();
    
    /** Creates a new TestModeCommand. */
    public TestModeCommand(CANSparkMax motorLeft1, CANSparkMax motorLeft2, CANSparkMax motorRight1, CANSparkMax motorRight2) {
        addRequirements(Robot.motorControl);
        this.motorLeft1 = motorLeft1;
        this.motorLeft2 = motorLeft2;
        this.motorRight1 = motorRight1;
        this.motorRight2 = motorRight2;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        int time = (int)Math.round(timer.get()) % 4;
        switch (time) {
            case 0:
                motorRight2.set(0);
                motorLeft1.set(1/Constants.Robot.SPEED_DIVIDER);
                break;

            case 1:
                motorLeft1.set(0);
                motorLeft2.set(1/Constants.Robot.SPEED_DIVIDER);
                break;

            case 2:
                motorLeft2.set(0);
                motorRight1.set(1/Constants.Robot.SPEED_DIVIDER);
                break;
            
            case 3:
                motorRight1.set(0);
                motorRight2.set(1/Constants.Robot.SPEED_DIVIDER);
                break;
            
            default:
                break;
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        motorLeft1.set(0);
        motorLeft2.set(0);
        motorRight1.set(0);
        motorRight2.set(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
