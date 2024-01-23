// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Launcher;

public class LauncherSubsystem extends SubsystemBase {
  private CANSparkMax launcherMotor;
  private CANSparkMax feederMotor;

  private RelativeEncoder launcherEncoder;
  private RelativeEncoder feederEncoder;

  private SparkPIDController launcherPID;
  private SparkPIDController feederPID;

  /** Creates a new LauncherSubsystem. */
  public LauncherSubsystem() {
    // Motor initialization
    this.launcherMotor = new CANSparkMax(Launcher.launcherMotorID, CANSparkMax.MotorType.kBrushless);
    this.feederMotor = new CANSparkMax(Launcher.feederMotorID, CANSparkMax.MotorType.kBrushless);

    // Motor configuration
    this.configMotor(this.launcherMotor);
    this.configMotor(this.feederMotor);

    // Encoder initialization
    this.launcherEncoder = this.launcherMotor.getEncoder();
    this.feederEncoder = this.feederMotor.getEncoder();

    // PID controller initialization
    this.launcherPID = this.launcherMotor.getPIDController();
    this.feederPID = this.feederMotor.getPIDController();

    // PID controller configuration
    this.configureLauncherPID();
    this.configureFeederPID();

    // Burn flash
    this.launcherMotor.burnFlash();
    this.feederMotor.burnFlash();
  }

  /**
   * Configures a motor to factory defaults.
   * 
   * @param motor
   */
  private void configMotor(CANSparkMax motor) {
    motor.restoreFactoryDefaults();
  }

  /**
   * Configures a PID controller.
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

  /**
   * Configures the launcher PID controller.
   */
  private void configureLauncherPID() {
    this.configurePID(this.launcherPID, Launcher.launcherP, Launcher.launcherI, Launcher.launcherD, Launcher.launcherIz, Launcher.launcherFF, Launcher.launcherMaxOutput, Launcher.launcherMinOutput);
  }

  /**
   * Configures the feeder PID controller.
   */
  private void configureFeederPID() {
    this.configurePID(this.feederPID, Launcher.feederP, Launcher.feederI, Launcher.feederD, Launcher.feederIz, Launcher.feederFF, Launcher.feederMaxOutput, Launcher.feederMinOutput);
  }

  /**
   * Sets the launcher speed.
   * 
   * @param speed
   */
  public void setLauncherSpeed(double speed) {
    this.launcherMotor.set(speed);
  }

  /**
   * Sets the feeder speed.
   * 
   * @param speed
   */
  public void setFeederSpeed(double speed) {
    this.feederMotor.set(speed);
  }

  /**
   * Sets the launcher velocity.
   * 
   * @return
   */
  public void setLauncherVelocity(double velocity) {
    double targetVelocity = velocity * Launcher.launcherMaxRPM;

    this.launcherPID.setReference(targetVelocity, CANSparkMax.ControlType.kVelocity);
  }

  /**
   * Gets the launcher velocity.
   * 
   * @return
   */
  public double getLauncherVelocity() {
    return this.launcherEncoder.getVelocity();
  }

  /**
   * Sets the feeder velocity.
   * 
   * @param velocity
   */
  public void setFeederVelocity(double velocity) {
    double targetVelocity = velocity * Launcher.feederMaxRPM;
    
    this.feederPID.setReference(targetVelocity, CANSparkMax.ControlType.kVelocity);
  }

  /**
   * Gets the feeder velocity.
   * 
   * @return
   */
  public double getFeederVelocity() {
    return this.feederEncoder.getVelocity();
  }

  /**
   * Stops the launcher.
   */
  public void stopLauncher() {
    this.launcherMotor.set(0);
  }

  /**
   * Stops the feeder.
   */
  public void stopFeeder() {
    this.feederMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
