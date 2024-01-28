// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Climber;

public class ClimberSubsystem extends SubsystemBase {
  private CANSparkMax climberMotorLeft;
  private CANSparkMax climberMotorRight;

  private RelativeEncoder climberEncoderLeft;
  private RelativeEncoder climberEncoderRight;

  private SparkPIDController climberPIDLeft;
  private SparkPIDController climberPIDRight;

  /** Creates a new ClimberSubsystem. */
  public ClimberSubsystem() {
    this.climberMotorLeft = new CANSparkMax(Climber.climberMotorLeftID, CANSparkMax.MotorType.kBrushless);
    this.climberMotorRight = new CANSparkMax(Climber.climberMotorRightID, CANSparkMax.MotorType.kBrushless);

    this.configMotor(this.climberMotorLeft);
    this.configMotor(this.climberMotorRight);

    this.climberEncoderLeft = this.climberMotorLeft.getEncoder();
    this.climberEncoderRight = this.climberMotorRight.getEncoder();

    this.climberPIDLeft = this.climberMotorLeft.getPIDController();
    this.climberPIDRight = this.climberMotorRight.getPIDController();

    this.configurePID(this.climberPIDLeft, Climber.climberLeftP, Climber.climberLeftI, Climber.climberLeftD, Climber.climberLeftIz, Climber.climberLeftFF, Climber.climberLeftMaxOutput, Climber.climberLeftMinOutput);
    this.configurePID(this.climberPIDRight, Climber.climberRightP, Climber.climberRightI, Climber.climberRightD, Climber.climberRightIz, Climber.climberRightFF, Climber.climberRightMaxOutput, Climber.climberRightMinOutput);
  }

  /**
   * Sets climber left motor speed.
   * 
   * @param speed
   */
  public void setClimberLeftSpeed(double speed) {
    this.climberMotorLeft.set(speed);
  }

  /**
   * Sets climber right motor speed.
   * 
   * @param speed
   */
  public void setClimberRightSpeed(double speed) {
    this.climberMotorRight.set(speed);
  }

  /**
   * Sets both climber motor speeds.
   * 
   * @param speed
   */
  public void setClimberSpeed(double speed) {
    this.climberMotorLeft.set(speed);
    this.climberMotorRight.set(speed);
  }

  /**
   * Sets climber left motor velocity.
   * 
   * @param velocity
   */
  public void setLeftClimberVelocity(double velocity) {
    double targetVelocity = velocity * Climber.climberLeftMaxRPM;

    this.climberPIDLeft.setReference(targetVelocity, CANSparkMax.ControlType.kVelocity);
  }

  /**
   * Sets climber right motor velocity.
   * 
   * @param velocity
   */
  public void setRightClimberVelocity(double velocity) {
    double targetVelocity = velocity * Climber.climberRightMaxRPM;

    this.climberPIDRight.setReference(targetVelocity, CANSparkMax.ControlType.kVelocity);
  }

  /**
   * Sets both climber motor velocities.
   * 
   * @param velocity
   */
  public void setClimberVelocity(double velocity) {
    double targetVelocity = velocity * Climber.climberLeftMaxRPM;

    this.climberPIDLeft.setReference(targetVelocity, CANSparkMax.ControlType.kVelocity);
    this.climberPIDRight.setReference(targetVelocity, CANSparkMax.ControlType.kVelocity);
  }

  /**
   * Gets climber left motor velocity.
   * 
   * @return
   */
  public double getLeftClimberVelocity() {
    return this.climberEncoderLeft.getVelocity();
  }

  /**
   * Gets climber right motor velocity.
   * 
   * @return
   */
  public double getRightClimberVelocity() {
    return this.climberEncoderRight.getVelocity();
  }

  /**
   * Stops climber motors.
   */
  public void stopClimber() {
    this.climberMotorLeft.set(0);
    this.climberMotorRight.set(0);
  }

  /**
   * Stops climber left motor.
   */
  public void stopLeftClimber() {
    this.climberMotorLeft.set(0);
  }

  /**
   * Stops climber right motor.
   */
  public void stopRightClimber() {
    this.climberMotorRight.set(0);
  }

  /**
   * Configures motor.
   * 
   * @param motor
   */
  private void configMotor(CANSparkMax motor) {
    motor.restoreFactoryDefaults();
  }

  /**
   * Configures PID.
   * 
   * @param pid
   * @param kP
   * @param kI
   * @param kD
   * @param kIz
   * @param kFF
   * @param kMaxOutput
   * @param kMinOutput
   */
  private void configurePID(SparkPIDController pid, double kP, double kI, double kD, double kIz, double kFF, double kMaxOutput, double kMinOutput) {
    pid.setP(kP);
    pid.setI(kI);
    pid.setD(kD);
    pid.setIZone(kIz);
    pid.setFF(kFF);
    pid.setOutputRange(kMinOutput, kMaxOutput);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
