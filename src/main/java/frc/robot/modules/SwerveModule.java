package frc.robot.modules;

import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.lib.config.SwerveModuleConstants;
import frc.lib.util.CANSparkMaxUtil;
import frc.lib.util.CANSparkMaxUtil.Usage;
import frc.robot.Constants.Swerve;

public class SwerveModule {
    // Module number identifier
    public int moduleNumber;

    // This is the last angle that we wanted to the wheel to move to.
    private Rotation2d lastAngle;

    /**
     * This is the starting angle of the wheel.   Since motors can be motor 
     * differently and the starting position of the shaft starts in a different
     * location, we need to know what the starting angle when the wheels are 
     * facing forward.   This is the basis of all angle changes after the 
     * robot is started up.
     */
    private Rotation2d angleOffset;

    // Used to angle the wheel in the directions we want to drive.
    private CANSparkMax angleMotor;

    // Used to rotate the wheel to move the robot.
    private CANSparkMax driveMotor;

    // Used to get the current position and state (velocity) of the drive motor
    private RelativeEncoder driveEncoder;

    /**
     * Currently defined, but not used in any calculations.   I'm assuming this was
     * first defined to be used for the angle motor, but we are doing that with the 
     * CANCoder (angleEncoder) defined below.
     */
    private RelativeEncoder integratedAngleEncoder;

    // Used to get the current angle (direction) of the wheel 
    private CANcoder angleEncoder;

    /** 
     * Used to PID control the angle motor power level.   The angle is converted from 
     * degrees to percentage of power.
     */
    private PIDController anglePid;

    public SwerveModule(int moduleNumber, SwerveModuleConstants moduleConstants) {
        // This is the module identifier.
        this.moduleNumber = moduleNumber;

        // This is the offset of the wheel in relation to the 0 position of the CANCoder.
        this.angleOffset  = moduleConstants.angleOffset;

        // Initializing the angle motor PID Controller
        this.anglePid = new PIDController(
            Swerve.angleKP,
            Swerve.angleKI,
            Swerve.angleKD
        );

        // Initializing the CANCoder for the 
        this.angleEncoder = new CANcoder(moduleConstants.canCoderId);
    }

    /**
     * configAngleEncoder()
     * 
     * This is intended to set the default strategy for each angle encoder (CANCoder)
     */
    private void configAngleEncoder() {
    }

    /**
     * configDriveMotor() 
     * 
     * This is intended to set default values for this modules drive motor (SparkMAX)
     */
    private void configDriveMotor() {
        // Set motor to original specs
        this.driveMotor.restoreFactoryDefaults();

        // This sets the transmission rate of data 
        CANSparkMaxUtil.setCANSparkMaxBusUsage(driveMotor, Usage.kAll);

        // Sets the current limit to the drive motor in AMPs
        this.driveMotor.setSmartCurrentLimit(Swerve.driveContinuousCurrentLimit);

        // Invert drive motor (currently, false)
        this.driveMotor.setInverted(Swerve.driveInvert);

        // When the motor isn't being used, set to brake mode
        this.driveMotor.setIdleMode(IdleMode.kBrake);

        // Set the conversion factor for velocity of the encoder. Multiplied by the native output units to give you velocity
        this.driveEncoder.setVelocityConversionFactor(Swerve.driveConversionVelocityFactor);

        // Set the conversion factor for position of the encoder. Multiplied by the native output units to give you position.
        this.driveEncoder.setPositionConversionFactor(Swerve.driveConversionPositionFactor);

        // Sets the voltage compensation setting for all modes on the SPARK and enables voltage compensation.
        this.driveMotor.enableVoltageCompensation(Swerve.voltageComp);

        // Writes values to the motor
        this.driveMotor.burnFlash();

        // Set starting position of the drive encoder
        this.driveEncoder.setPosition(0.0);
    }
}
