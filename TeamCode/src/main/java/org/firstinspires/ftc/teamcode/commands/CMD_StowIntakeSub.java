package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.WaitCommand;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Bucket;
import org.firstinspires.ftc.teamcode.subsystems.SUB_SubBucket;
import org.firstinspires.ftc.teamcode.subsystems.SUB_SubBucketSlide;

public class CMD_StowIntakeSub extends SequentialCommandGroup {
    public CMD_StowIntakeSub(SUB_SubBucket p_subBucket, SUB_Bucket p_bucket, SUB_SubBucketSlide p_subbucketslide, GlobalVariables p_variables) {
        addCommands(
                new InstantCommand(p_subBucket::intakeOff),
                new InstantCommand(() -> p_subBucket.setPosition(Constants.SubBucketServoConst.kSubHome)),
                new InstantCommand(() -> p_bucket.setPosition(Constants.BucketServoConst.kBucketHome)),
                new WaitCommand(1000),
                new CMD_SubBucketSlideReset(p_subbucketslide),
                new InstantCommand(() -> p_variables.setRobotState(GlobalVariables.RobotState.HOME))
        );
    }
}
