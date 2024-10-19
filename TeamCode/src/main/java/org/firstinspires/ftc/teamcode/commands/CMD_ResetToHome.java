package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Bucket;
import org.firstinspires.ftc.teamcode.subsystems.SUB_BucketLift;
import org.firstinspires.ftc.teamcode.subsystems.SUB_SubBucket;
import org.firstinspires.ftc.teamcode.subsystems.SUB_SubBucketSlide;

public class CMD_ResetToHome extends SequentialCommandGroup {
    public CMD_ResetToHome(SUB_Bucket p_bucket, SUB_BucketLift p_bucketlift, SUB_SubBucket p_subbucket, SUB_SubBucketSlide p_subbucketslide, GlobalVariables p_variables) {
        addCommands(
                new InstantCommand(() -> p_bucket.setPosition(Constants.BucketServoConst.kBucketHome)),
                new CMD_BucketLiftReset(p_bucketlift),
                new InstantCommand(() -> p_subbucket.setPosition(Constants.SubBucketServoConst.kSubHome)),
                new InstantCommand(() -> p_subbucketslide.setTargetPosition(Constants.SubBucketSlideConst.kHome)),
                new InstantCommand(() -> p_variables.setRobotState(GlobalVariables.RobotState.HOME))
        );
    }
}
