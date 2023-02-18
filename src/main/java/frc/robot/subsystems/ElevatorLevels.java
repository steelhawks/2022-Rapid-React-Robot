package frc.robot.subsystems;

public enum ElevatorLevels {
    LOW(0),
    MID(3), // 50
    HIGH(6); // 140

    private int encoderVal;

    ElevatorLevels(int encoderVal) {
        this.encoderVal = encoderVal;
    }

    public int getEncoderVal() {
        return encoderVal;
    }
}
