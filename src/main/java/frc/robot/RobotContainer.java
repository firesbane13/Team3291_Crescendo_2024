// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.Swerve;
// import frc.robot.Constants.OperatorConstants;
// import frc.robot.commands.Autos;
// import frc.robot.commands.ExampleCommand;
import frc.robot.commands.autonomous.Auto01;
import frc.robot.commands.autonomous.Auto02;
import frc.robot.commands.climber.ClimbUpCommand;
import frc.robot.commands.climber.LowerRobotCommand;
import frc.robot.commands.drivetrain.SwerveDrive;
import frc.robot.commands.intake.PickUpCommand;
import frc.robot.commands.intake.ReleasePieceCommand;
import frc.robot.commands.launcher.LaunchCommand;
import frc.robot.subsystems.ClimberSubsystem;
// import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.SwerveSubsystem;
// import frc.robot.subsystems.VisionSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  /*
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
  */

  // AUTONOMOUS
  private Auto01 auto01 = new Auto01();
  private Auto02 auto02 = new Auto02();

  SendableChooser<Command> m_Chooser = new SendableChooser<>();

  // SUBSYSTEMS
  private SwerveSubsystem swerveSubsystem = new SwerveSubsystem();
  private LauncherSubsystem launcherSubsystem = new LauncherSubsystem();
  private ClimberSubsystem climberSubsystem = new ClimberSubsystem();
  private IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  //private VisionSubsystem visionSubsystem = new VisionSubsystem();

  // DRIVE TRAIN


  // LAUNCHER
  private LaunchCommand launch = new LaunchCommand(launcherSubsystem);
  
  // CLIMBER
  private ClimbUpCommand climbUp = new ClimbUpCommand(climberSubsystem);
  private LowerRobotCommand lowerRobot = new LowerRobotCommand(climberSubsystem);

  // INTAKE
  private PickUpCommand pickUp = new PickUpCommand(intakeSubsystem);
  private ReleasePieceCommand releasePiece = new ReleasePieceCommand(intakeSubsystem);

  // CONTROLLERS
  public CommandJoystick controller00 = new CommandJoystick(Constants.OperatorConstants.PrimaryControllerPort);
  public CommandJoystick controller01 = new CommandJoystick(Constants.OperatorConstants.SecondaryControllerPort);

  public final JoystickButton robotCentricButton = new JoystickButton(controller00.getHID(), Constants.ControllerButtons.lb);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    /*
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));
    */

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    // m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());

    m_Chooser.setDefaultOption("Autonmous 01", auto01);
    m_Chooser.addOption("Autonomous 02", auto02);
    SmartDashboard.putData("Autonomous Choices", m_Chooser);

    swerveSubsystem.setDefaultCommand(
      new SwerveDrive(
        swerveSubsystem,
        () -> controller00.getRawAxis(1),
        () -> controller00.getRawAxis(0),
        () -> controller00.getRawAxis(4),
        () -> robotCentricButton.getAsBoolean()
      )
    );

    controller01.button(Constants.ControllerButtons.a).whileTrue(launch);
    controller01.button(Constants.ControllerButtons.b).whileTrue(climbUp);
    controller01.button(Constants.ControllerButtons.y).whileTrue(lowerRobot);
    controller01.button(Constants.ControllerButtons.rb).whileTrue(pickUp);
    controller01.button(Constants.ControllerButtons.lb).whileTrue(releasePiece);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    // return Autos.exampleAuto(m_exampleSubsystem);
    return m_Chooser.getSelected();
  }
}
