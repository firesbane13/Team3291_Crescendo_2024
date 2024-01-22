// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.signals.SensorDirectionValue;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;
import frc.lib.config.SwerveModuleConstants;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int PrimaryControllerPort = 0;
    public static final int SecondaryControllerPort = 1;
  }

  public static class ControllerButtons {
    public static final int a = 1;
    public static final int b = 2;
    public static final int x = 3;
    public static final int y = 4;
    
    public static final int lb = 5;
    public static final int rb = 6;

    public static final int back = 7;
    public static final int start = 8;

    public static final int l3 = 9;
    public static final int r3 = 10;

    // AXIS
    public static final int leftXAxis = 0;
    public static final int leftYAxis = 1;
    public static final int lt = 2;
    public static final int rt = 3;
    public static final int rightXAxis = 4;
    public static final int rightYAxis = 5;
  }

  public static class Swerve {
    public static final double stickDeadband = 0.1;//configure and mess around with later
    public static final boolean invertGyro = false; // Always ensure Gyro is CCW+ CW- 

    public static final double trackWidth = Units.inchesToMeters(21.25);
    public static final double wheelBase = Units.inchesToMeters(21.25);
    public static final double wheelDiameter = Units.inchesToMeters(4.0);
    public static final double wheelCircumference = wheelDiameter * Math.PI;
    public static final double driveGearRatio = (6.75 / 1.0); // 6.75:1
    public static final double angleGearRatio = ((150/7) / 1.0); // 150/7:1

    public static final double kMaxTranslationAcceleration = 3.0; //meters per second squared
    public static final double kMaxStrafeAcceleration = 3.0; //meters per second squared
    public static final double kMaxRotationAcceleration = 3.0; //radians per second squared

    // Swerve Kinematics
    public static final SwerveDriveKinematics swerveKinematics =
        new SwerveDriveKinematics(
            //front left
            new Translation2d(wheelBase / 2.0, trackWidth / 2.0),
            //front right
            new Translation2d(wheelBase / 2.0, -trackWidth / 2.0),
            //back left
            new Translation2d(-wheelBase / 2.0, trackWidth / 2.0),
            //Back right
            new Translation2d(-wheelBase / 2.0, -trackWidth / 2.0));

    /* Swerve Profiling Values */
    // Meters per second IN THEORY
    public static final double maxSpeed = 4.4196;

    /**
     * Find out?
     * 
     * In theory the max speed is already given, but I can't find the max
     * angular velocity, so we'll probably calculate both anyways
     */
    public static final double maxAngularVelocity = 11.5;

    // Swerve Voltage Compensation
    public static final double voltageComp = 12.0;

    /* Drive Motor Conversion Factors */
    // The conversion factor to multiply the native units by 
    public static final double driveConversionPositionFactor =
        (wheelDiameter * Math.PI) / driveGearRatio;

    // The conversion factor to multiply the native units by 
    public static final double driveConversionVelocityFactor = driveConversionPositionFactor / 60.0;

    // The conversion factor to multiply the native units by (angle position)
    public static final double angleConversionFactor = 360.0 / angleGearRatio;

    // Swerve Current Limiting
    public static final int angleContinuousCurrentLimit = 20;
    public static final int driveContinuousCurrentLimit = 80;

    // Motor Inverts
    public static final boolean driveInvert = false;
    public static final boolean angleInvert = true;

    /* Angle Encoder Invert */
    public static final boolean canCoderInvert = false;

    // Angle Encoder Invert
    public static final SensorDirectionValue canCoderInvert_v6 = SensorDirectionValue.Clockwise_Positive;

    // Angle Motor PID Values
    public static final double angleKP = 0.003; //play around with/tune this later 
    public static final double angleKI = 0.0000001;
    public static final double angleKD = 0.0001;
    public static final double angleKFF = 0.0;

    /**************************
     * Module Specific Constants
     **************************/
    // Front Left Module - Module 0 
    public static final class Mod0 {
      // SparkMAX CAN Device ID
      public static final int driveMotorID = 10;

      // SparkMAX CAN Device ID
      public static final int angleMotorID = 11;

      // CANCoder CAN Device ID
      public static final int canCoderID = 2;

      // Wheel starting angle offset
      public static final Rotation2d angleOffset = Rotation2d.fromDegrees(325.019531250);

      // Constants in a nice package.
      public static final SwerveModuleConstants constants = new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
    }

    // Back Right Module - Module 1
    public static final class Mod1 {
      // SparkMAX CAN Device ID
      public static final int driveMotorID = 6;

      // SparkMAX CAN Device ID
      public static final int angleMotorID = 5;

      // CANCoder CAN Device ID
      public static final int canCoderID = 3;

      // Wheel starting angle offset
      public static final Rotation2d angleOffset = Rotation2d.fromDegrees(147.041015625);

      // Constants in a nice package.
      public static final SwerveModuleConstants constants = new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
    }

    // Back Left Module - Module 2
    public static final class Mod2 {
      // SparkMAX CAN Device ID
      public static final int driveMotorID = 12;

      // SparkMAX CAN Device ID
      public static final int angleMotorID = 13;

      // CANCoder CAN Device ID
      public static final int canCoderID = 1;

      // Wheel starting angle offset
      public static final Rotation2d angleOffset = Rotation2d.fromDegrees(174.8144);

      // Constants in a nice package.
      public static final SwerveModuleConstants constants = new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
    }

    // Front Right Module - Module 3
    public static final class Mod3 {
      // SparkMAX CAN Device ID
      public static final int driveMotorID = 9;

      // SparkMAX CAN Device ID
      public static final int angleMotorID = 8;

      // CANCoder CAN Device ID
      public static final int canCoderID = 0;

      // Wheel starting angle offset
      public static final Rotation2d angleOffset = Rotation2d.fromDegrees(284.326171875);

      // Constants in a nice package.
      public static final SwerveModuleConstants constants = new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
    }
  }

  public static final class Launcher {
    public static final int launcherMotorID = 7;
    public static final int feederMotorID = 8;

    public static final double launcherSpeed = 1.0;
    public static final double feederSpeed = 0.5;

    public static final double launcherSpeedSlow = 0.25;
    public static final double feederSpeedSlow = 0.25;
  }

  public static final class Intake {
    public static final int intakeMotorID = 9;
    public static final int intakeRotationMotorID = 10;

    public static final double intakeSpeed = 0.5;
    public static final double intakeRotationSpeed = 0.5;

    public static final double intakeSpeedSlow = 0.25;
  }

  public static final class Climber {
    public static final int climberMotorLeftID = 11;
    public static final int climberMotorRightID = 12;

    public static final double climberSpeed = 0.5;

    public static final double climberSpeedSlow = 0.25;
  }
}
