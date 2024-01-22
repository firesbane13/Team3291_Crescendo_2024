// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Climber;

public class ClimberSubsystem extends SubsystemBase {
  public CANSparkMax climberMotorLeft;
  public CANSparkMax climberMotorRight;

  /** Creates a new ClimberSubsystem. */
  public ClimberSubsystem() {
    this.climberMotorLeft = new CANSparkMax(Climber.climberMotorLeftID, CANSparkMax.MotorType.kBrushless);
    this.climberMotorRight = new CANSparkMax(Climber.climberMotorRightID, CANSparkMax.MotorType.kBrushless);
  }

  public void setClimberSpeed(double speed) {
    this.climberMotorLeft.set(speed);
    this.climberMotorRight.set(speed);
  }

  public void stopClimber() {
    this.climberMotorLeft.set(0);
    this.climberMotorRight.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
