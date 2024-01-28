// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Intake;

public class IntakeSubsystem extends SubsystemBase {
  // Intake Motor that pushes and pulls the game pieces
  public CANSparkMax intakeMotor;

  public SparkPIDController intakePIDController;

  // Intake Motor that rotates the intake
  public CANSparkMax intakeRotationMotor;

  // Intake Encoder that tracks the rotation of the intake
  public DutyCycleEncoder intakeRotationEncoder;

  // Intake Limit Switch that stops the intake from rotating
  public DigitalInput intakeRotationLimitSwitch;

  // PID Controller for the intake rotation
  public PIDController intakeRotationPIDController;

  // Boolean that tracks if a game piece is detected
  public boolean gamePieceDetected = false;

  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {
    this.intakeMotor = new CANSparkMax(Intake.intakeMotorID, CANSparkMax.MotorType.kBrushless);
    this.intakePIDController = this.intakeMotor.getPIDController();

    this.intakeRotationMotor = new CANSparkMax(Intake.intakeRotationMotorID, CANSparkMax.MotorType.kBrushless);
    this.intakeRotationEncoder = new DutyCycleEncoder(Intake.intakeRotationEncoder);
    this.intakeRotationLimitSwitch = new DigitalInput(Intake.intakeRotationLimitSwitch);
    this.intakeRotationPIDController = new PIDController(Intake.intakeRotationP, Intake.intakeRotationI, Intake.intakeRotationD);
  }

  /**
   * Sets the speed of the intake motor
   * @param speed The speed of the intake motor
   */
  public void setIntakeSpeed(double speed) {
    // The value of the speed after PID
    double speedAdjustment = 0;

    // Send PID current speed value for processing
    this.intakePIDController.setReference(speed, CANSparkMax.ControlType.kDutyCycle);
    speedAdjustment = this.intakePIDController.getOutputMax();

    this.intakeMotor.set(speedAdjustment);
  }

  /**
   * Sets the speed of the intake rotation motor
   * @param speed The speed of the intake rotation motor
   */
  public void setIntakeRotationSpeed(double speed) {
    // Stop the intake rotation motor if the limit switch is pressed
    if (speed > 0 && this.intakeRotationLimitSwitch.get()) {
      speed = 0;
    }

    // If the intake is rotating backwards, set the game piece detected boolean to false
    if (speed < 0) {
      gamePieceDetected = false;
    }

    this.intakeRotationMotor.set(speed);
  }

  /**
   * Sets the position of the intake rotation motor
   * @param position The position of the intake rotation motor
   */
  public void stopIntake() {
    this.intakeMotor.set(0);
  }

  /**
   * Sets the position of the intake rotation motor
   * @param position The position of the intake rotation motor
   */
  public void stopIntakeRotation() {
    this.intakeRotationMotor.set(0);
  }

  /**
   * Configures the intake PID controller.
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
   * Configures a motor to factory defaults.
   * 
   * @param motor
   */
  private void configMotor(CANSparkMax motor) {
    motor.restoreFactoryDefaults();
  }

  /**
   * Configures the intake PID controller.
   */
  private void configureIntakePID() {
    this.configurePID(this.intakePIDController, Intake.intakeP, Intake.intakeI, Intake.intakeD, Intake.intakeIz, Intake.intakeFF, Intake.intakeMaxOutput, Intake.intakeMinOutput);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    // Stop the intake rotation motor if the limit switch is pressed
    if (this.intakeRotationLimitSwitch.get()) {
      stopIntake();

      /**
       * Set the game piece detected boolean to true.   Using this to 
       * lower the intake speed to do the auto adjustments
       */ 
      gamePieceDetected = true;
    } else if (gamePieceDetected) {
      // If the game piece is detected, lower the intake speed
      setIntakeSpeed(Intake.intakeSpeedSlow);
    }
  }
}
