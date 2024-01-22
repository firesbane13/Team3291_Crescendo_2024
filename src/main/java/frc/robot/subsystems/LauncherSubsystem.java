// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Launcher;

public class LauncherSubsystem extends SubsystemBase {
  private CANSparkMax launcherMotor;
  private CANSparkMax feederMotor;

  /** Creates a new LauncherSubsystem. */
  public LauncherSubsystem() {
    this.launcherMotor = new CANSparkMax(Launcher.launcherMotorID, CANSparkMax.MotorType.kBrushless);
    this.feederMotor = new CANSparkMax(Launcher.feederMotorID, CANSparkMax.MotorType.kBrushless);
  }

  public void setLauncherSpeed(double speed) {
    this.launcherMotor.set(speed);
  }

  public void setFeederSpeed(double speed) {
    this.feederMotor.set(speed);
  }

  public void stopLauncher() {
    this.launcherMotor.set(0);
  }

  public void stopFeeder() {
    this.feederMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
