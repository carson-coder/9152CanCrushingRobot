// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.crushing.Crushing;
import frc.robot.commands.crushing.Lights;

public class CanCrushing extends SubsystemBase {
    Trigger run;
    Trigger stop;
    
    Crushing cmd;
    boolean stopped = false;

    /** Creates a new CanCrushing. */
    public CanCrushing() {}

    public void init() {
        Trigger STOP = new Trigger(RobotContainer.led::get);
        run = new CommandXboxController(Constants.OperatorConstants.JOYSTICK_PORT).a().and(STOP);
        run.onTrue(this.runOnce(() -> {if (stopped) {return;} CommandScheduler.getInstance().schedule(crushing());}));

        stop = new CommandXboxController(Constants.OperatorConstants.JOYSTICK_PORT).b().or(STOP.negate());
        stop.onTrue(this.runOnce(() -> {if (stopped) {return;} CommandScheduler.getInstance().cancel(cmd);}));
        SmartDashboard.putData("Crushing Subsystem", this);
    }

    public Command crushing() {
        if (cmd == null || cmd.isFinished()) {
            cmd = new Crushing();
        }
        return cmd;
    }
    
    @Override
    public void periodic() {
        //SmartDashboard.putData("Crushing Subsystem", this);
    }

    public void exit() {
        run = null;
        stop = null;
        stopped = true;
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addDoubleProperty("Crushing Motor Speed", RobotContainer.crushingMotor::get, null);
    }
}
