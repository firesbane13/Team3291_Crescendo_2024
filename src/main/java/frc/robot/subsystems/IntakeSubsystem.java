// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Intake;

public class IntakeSubsystem extends SubsystemBase {
  public CANSparkMax intakeMotor;

  public CANSparkMax intakeRotationMotor;
  public Encoder intakeRotationEncoder;


  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {
    this.intakeMotor = new CANSparkMax(Intake.intakeMotorID, CANSparkMax.MotorType.kBrushless);
    this.intakeRotationMotor = new CANSparkMax(Intake.intakeRotationMotorID, CANSparkMax.MotorType.kBrushless);
 
    this.intakeRotationEncoder = new Encoder(0, 1);
  }

  public void setIntakeSpeed(double speed) {
    this.intakeMotor.set(speed);
  }

  public void setIntakeRotationSpeed(double speed) {
    this.intakeRotationMotor.set(speed);
  }

  public void stopIntake() {
    this.intakeMotor.set(0);
  }

  public void stopIntakeRotation() {
    this.intakeRotationMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
