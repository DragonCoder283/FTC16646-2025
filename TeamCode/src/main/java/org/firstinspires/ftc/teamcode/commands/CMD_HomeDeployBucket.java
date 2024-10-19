package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Bucket;
import org.firstinspires.ftc.teamcode.subsystems.SUB_BucketLift;

public class CMD_HomeDeployBucket extends SequentialCommandGroup {
    public CMD_HomeDeployBucket(SUB_Bucket p_bucket, SUB_BucketLift p_bucketlift, GlobalVariables p_variables) {
        addCommands(
                new InstantCommand(() -> p_bucket.setPosition(Constants.BucketServoConst.kBucketHome)),
                new InstantCommand(() -> p_bucketlift.setPosition(Constants.BucketLiftConst.kHome)),
                new CMD_LiftInPosition(p_bucketlift),
                new InstantCommand(()-> p_variables.setRobotState(GlobalVariables.RobotState.HOME))
        );
    }
}