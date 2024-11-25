// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.driving;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class TestMode extends Command {

    private final Timer timer = new Timer();
    
    /** Creates a new TestModeCommand. */
    public TestMode() {
        addRequirements(Robot.motorControl);
        SmartDashboard.putData(this);
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
                RobotContainer.motorRight2.set(0);
                RobotContainer.motorLeft1.set(1/Constants.Robot.SPEED_DIVIDER);
                break;

            case 1:
                RobotContainer.motorLeft1.set(0);
                RobotContainer.motorLeft2.set(1/Constants.Robot.SPEED_DIVIDER);
                break;

            case 2:
                RobotContainer.motorLeft2.set(0);
                RobotContainer.motorRight1.set(1/Constants.Robot.SPEED_DIVIDER);
                break;
            
            case 3:
                RobotContainer.motorRight1.set(0);
                RobotContainer.motorRight2.set(1/Constants.Robot.SPEED_DIVIDER);
                break;
            
            default:
                break;
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        RobotContainer.motorLeft1.set(0);
        RobotContainer.motorLeft2.set(0);
        RobotContainer.motorRight1.set(0);
        RobotContainer.motorRight2.set(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        // Instantly Exit
        return true;
    }
}
