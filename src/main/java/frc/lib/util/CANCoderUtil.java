package frc.lib.util;

import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.CANCoderStatusFrame;
import com.ctre.phoenix6.hardware.CANcoder;

public class CANCoderUtil {
    public enum CCUsage {
        kAll,
        kSensorDataOnly,
        kFaultsOnly,
        kMinimal
    }

    public static void setCANCoderBusUsage(
        CANCoder cancoder,
        CCUsage usage
    ) {
        if (usage == CCUsage.kAll) {
        cancoder.setStatusFramePeriod(CANCoderStatusFrame.SensorData, 10);
        cancoder.setStatusFramePeriod(CANCoderStatusFrame.VbatAndFaults, 10);
        } else if (usage == CCUsage.kSensorDataOnly) {
        cancoder.setStatusFramePeriod(CANCoderStatusFrame.SensorData, 10);
        cancoder.setStatusFramePeriod(CANCoderStatusFrame.VbatAndFaults, 100);
        } else if (usage == CCUsage.kFaultsOnly) {
        cancoder.setStatusFramePeriod(CANCoderStatusFrame.SensorData, 100);
        cancoder.setStatusFramePeriod(CANCoderStatusFrame.VbatAndFaults, 10);
        } else if (usage == CCUsage.kMinimal) {
        cancoder.setStatusFramePeriod(CANCoderStatusFrame.SensorData, 100);
        cancoder.setStatusFramePeriod(CANCoderStatusFrame.VbatAndFaults, 100);
        }
    }

    public static void setCANCoderBusUsageV6(
        CANcoder cancoder,
        CCUsage usage
    ) {
        if (usage == CCUsage.kAll) {
            
        } else if (usage == CCUsage.kSensorDataOnly) {

        } else if (usage == CCUsage.kFaultsOnly) {

        } else if (usage == CCUsage.kMinimal) {

        }
    }
}
