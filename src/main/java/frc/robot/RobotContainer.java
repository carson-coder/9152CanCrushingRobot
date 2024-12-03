// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    public static CANSparkMax motorLeft1 = new CANSparkMax(Constants.Robot.MOTOR_LEFT_1_ID, MotorType.kBrushed);
    public static CANSparkMax motorLeft2 = new CANSparkMax(Constants.Robot.MOTOR_LEFT_2_ID, MotorType.kBrushed);
    public static CANSparkMax motorRight1 = new CANSparkMax(Constants.Robot.MOTOR_RIGHT_1_ID, MotorType.kBrushed);
    public static CANSparkMax motorRight2 = new CANSparkMax(Constants.Robot.MOTOR_RIGHT_2_ID, MotorType.kBrushed);

    public static CANSparkMax crushingMotor = new CANSparkMax(Constants.CRUSHING_MOTOR, MotorType.kBrushed);
    
    public static boolean Crushing = false;

    // Replace with CommandPS4Controller or CommandJoystick if needed
    private static final CommandXboxController m_driverController =
            new CommandXboxController(OperatorConstants.JOYSTICK_PORT);

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public static double GetDriverRawAxis(int axis) {
        return m_driverController.getRawAxis(axis);
    }

    public static double GetDriverRawAxis(int port, int axis) {
        return new Joystick(port).getRawAxis(axis);
    }

    public static boolean is_keyboard() {
        return m_driverController.getHID().getAxisCount() < 4;
    }

    public RobotContainer() {}
}
