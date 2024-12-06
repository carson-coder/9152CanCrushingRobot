// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.Currency;

import com.fasterxml.jackson.databind.deser.impl.CreatorCollector;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.CanCrushing;
import frc.robot.subsystems.LightsSubsystem;
import frc.robot.subsystems.MotorControl;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    private RobotContainer m_robotContainer;

    public static MotorControl motorControl = new MotorControl();
    public static LightsSubsystem lightControl = new LightsSubsystem();

    @Override
    public void robotInit() {
        m_robotContainer = new RobotContainer();
        motorControl.init();
        lightControl.init();
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        SmartDashboard.updateValues();
    }

    @Override
    public void disabledInit() {}

    @Override
    public void disabledPeriodic() {}

    @Override
    public void autonomousInit() {}

    @Override
    public void autonomousPeriodic() {}

    public static CanCrushing crushingSubsystem;

    @Override
    public void teleopInit() {
        if (!Constants.ONLYMOVE) {
            crushingSubsystem = new CanCrushing();
            crushingSubsystem.init();
        }
    }

    @Override
    public void teleopPeriodic() {}

    @Override
    public void teleopExit() {
        CommandScheduler.getInstance().cancel(CommandScheduler.getInstance().requiring(crushingSubsystem));
        crushingSubsystem.exit();
        crushingSubsystem = null;
    }

    @Override
    public void testInit() {
        // Cancels all running commands at the start of test mode.
        CommandScheduler.getInstance().cancelAll();
        CommandScheduler.getInstance().schedule(motorControl.testModeCommand());
    }

    @Override
    public void testPeriodic() {}

    @Override
    public void testExit() {
        CommandScheduler.getInstance().cancelAll();
    }
    
    @Override
    public void simulationInit() {}

    @Override
    public void simulationPeriodic() {}
}
