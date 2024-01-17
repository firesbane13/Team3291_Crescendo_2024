// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.Swerve;
import frc.robot.modules.SwerveModule;

public class SwerveSubsystem extends SubsystemBase {
  private final AHRS gyro;

  private SwerveDriveOdometry swerveOdometry;
  private SwerveModule[] mSwerveMods;

  private Field2d field;

  /** Creates a new SwerveSubsystem. */
  public SwerveSubsystem() {
    gyro = new AHRS();

    zeroGryo();

    mSwerveMods = new SwerveModule[] {
        new SwerveModule(0, Swerve.Mod0.constants),
        new SwerveModule(1, Swerve.Mod1.constants),
        new SwerveModule(2, Swerve.Mod2.constants),
        new SwerveModule(3, Swerve.Mod3.constants)
    };

    resetToAbsolute();

    swerveOdometry = new SwerveDriveOdometry(
      Swerve.swerveKinematics, 
      getYaw(), 
      getModulePositions()
    );

    field = new Field2d();

    SmartDashboard.putData("Field", field);
  }

  public void drive(
    Translation2d translation,
    double rotation,
    boolean fieldRelative,
    boolean isOpenLoop
  ) {
   // SwerveModuleState[] swerveModuleStates = Swerve.swerveKinematics.toSwerveModuleStates(
    //  fieldRelative ? ChassisSpeeds.fromFieldRelativeSpeeds(
    //    translation, getYaw())
   //P );
  }

  private void zeroGryo() {
    gyro.reset();
  }

  private void resetToAbsolute() {
    for (SwerveModule mod : mSwerveMods) {
      mod.resetToAbsolute();
    }
  }

  private Rotation2d getYaw() {
    if (Swerve.invertGyro) {
      return Rotation2d.fromDegrees(360 - gyro.getYaw());
    } else {
      return Rotation2d.fromDegrees(gyro.getYaw());
    }
  }

  private SwerveModulePosition[] getModulePositions() {
    SwerveModulePosition[] positions = new SwerveModulePosition[4];

    for (SwerveModule mod : mSwerveMods) {
      positions[mod.moduleNumber] = mod.getPosition();
    }

    return positions;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
