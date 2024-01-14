package frc.lib.config;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.signals.AbsoluteSensorRangeValue;

import frc.robot.Constants.Swerve;

public class CTREConfigs {
    public static CANcoderConfiguration swerveCanCoderConfig;

    public CTREConfigs() {
        swerveCanCoderConfig = new CANcoderConfiguration();

        swerveCanCoderConfig.MagnetSensor.AbsoluteSensorRange = AbsoluteSensorRangeValue.Unsigned_0To1;
        swerveCanCoderConfig.MagnetSensor.SensorDirection = Swerve.canCoderInvert;
    }
}
