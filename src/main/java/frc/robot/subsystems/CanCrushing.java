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

    /** Creates a new CanCrushing. */
    public CanCrushing() {}

    public void init() {
        run = new CommandXboxController(Constants.OperatorConstants.JOYSTICK_PORT).a();
        run.onTrue(this.runOnce(() -> {CommandScheduler.getInstance().schedule(crushing());}));

        stop = new CommandXboxController(Constants.OperatorConstants.JOYSTICK_PORT).b();
        stop.onTrue(this.runOnce(() -> {CommandScheduler.getInstance().cancel(cmd);}));
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

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addDoubleProperty("Crushing Motor Speed", RobotContainer.crushingMotor::get, null);
    }
}
