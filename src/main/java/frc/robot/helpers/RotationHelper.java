package frc.robot.helpers;

public class RotationHelper {
    public static double modRotations(double encoderValue) {
        encoderValue = encoderValue % 1.0;

        if (encoderValue < 0) {
            encoderValue += 1.0;
        }

        return encoderValue;
    }

    public static double modDegrees(double encoderValue) {
        encoderValue = encoderValue % 360.0;

        if (encoderValue < 0) {
            encoderValue += 360.0;
        }

        return encoderValue;
    }

    public static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
}
