// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.crushing;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class Lights extends Command {
  /** Creates a new Lights. */
  AddressableLED m_led = new AddressableLED(9);
  AddressableLEDBuffer m_ledBuffer = new AddressableLEDBuffer(60);

  DigitalInput led = new DigitalInput(0);

  public Lights() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.lightControl);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
    m_led.setLength(m_ledBuffer.getLength());
    // Set the data
    m_led.setData(m_ledBuffer);
    m_led.start();
    SmartDashboard.putData(this);
    led_colors();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // for (int i = 0; i < m_ledBuffer.getLength(); i++) {
    //   m_ledBuffer.setHSV(i, Math.round(((float)i/m_ledBuffer.getLength())*180), 255, 255);
    // }
    boolean obstructed = !led.get();
    if (RobotContainer.Crushing) {
      for (int i = 0; i < m_ledBuffer.getLength(); i++) {
        m_ledBuffer.setRGB(i, 255, 0, 0);
        colors[i] = m_ledBuffer.getLED(i).toHexString();
      }
    }
    else if (!obstructed) {
      for (int i = 0; i < m_ledBuffer.getLength(); i++) {
        m_ledBuffer.setRGB(i, 0, 255, 0);
        colors[i] = m_ledBuffer.getLED(i).toHexString();
      }
    } else {
      if ((double)Math.round(System.currentTimeMillis() / 1000) % 2 == 0) {
        for (int i = 0; i < m_ledBuffer.getLength(); i++) {
          m_ledBuffer.setRGB(i, 255, 0, 0);
          colors[i] = m_ledBuffer.getLED(i).toHexString();
        }
      } else {
        for (int i = 0; i < m_ledBuffer.getLength(); i++) {
          m_ledBuffer.setRGB(i, 0, 0, 0);
          colors[i] = m_ledBuffer.getLED(i).toHexString();
        }
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("end");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  String[] colors = new String[m_ledBuffer.getLength()];
  public String[] led_colors() {
    for (var i = 0; i < m_ledBuffer.getLength(); i++) {
      // Sets the specified LED to the RGB values for red
      colors[i] = m_ledBuffer.getLED(i).toHexString();
    }
    return colors;
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    super.initSendable(builder);
    builder.addStringArrayProperty(getName(), () -> {return colors;}, null);
  }
}
