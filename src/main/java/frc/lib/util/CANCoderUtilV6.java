package frc.lib.util;

import com.ctre.phoenix6.hardware.CANcoder;

public class CANCoderUtilV6 {
    public enum CCUsage {
        kAll,
        kSensorDataOnly,
        kFaultsOnly,
        kMinimal
    }

    public static void setCANCoderBusUsage(
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
