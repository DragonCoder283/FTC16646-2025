package org.firstinspires.ftc.teamcode;

public class Constants {
    public static final class BucketServoConst{
        public static final double kBucketDeployBucket = 1.0;
        public static final double kBucketHome = 0.85;
        public static final double kBucketReceive = 0.65;
    }

    public static final class SubBucketServoConst{
        public static final double kSubIntake = 0.95;
        public static final double kSubReadyToIntake = 0.85;
        public static final double kSubHome = 0.5;
        public static final double kSubTransfer = 0.37;
    }

    public static final class BucketLiftConst{
        public static final int kTolerance = 10;
        public static final int kHome = 0;
        public static final int kReadyToIntakeWall = 300;
        public static final int kIntakeWall = 725;
        public static final int kReadyToDeployChamber = 1600;
        public static final int kDeployChamber = 1025;
        public static final int kReadyToDeployBucket = 2900;
    }

    public static final class SubBucketSlideConst{
        public static final int kTolerance = 10;
        public static final int kHome = 0;
        public static final int kTransfer = 615;
        public static final int kIntake = 1100;
        public static final int kMaxExtent = 1500;
    }
}
