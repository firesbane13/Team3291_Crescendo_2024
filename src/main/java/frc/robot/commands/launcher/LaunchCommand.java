// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.launcher;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.Launcher;
import frc.robot.subsystems.LauncherSubsystem;

public class LaunchCommand extends Command {
  public LauncherSubsystem launcherSubsystem;

  /** Creates a new LaunchCommand. */
  public LaunchCommand(LauncherSubsystem launcherSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.

    this.launcherSubsystem = launcherSubsystem;
    addRequirements(launcherSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.launcherSubsystem.setLauncherSpeed(Launcher.launcherSpeed);
    this.launcherSubsystem.setFeederSpeed(Launcher.feederSpeed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.launcherSubsystem.stopLauncher();
    this.launcherSubsystem.stopFeeder();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    boolean status = false;

    return status;
  }
}
