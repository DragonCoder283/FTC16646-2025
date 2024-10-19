package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Bucket;
import org.firstinspires.ftc.teamcode.subsystems.SUB_SubBucket;
import org.firstinspires.ftc.teamcode.subsystems.SUB_SubBucketSlide;

public class CMD_IntakeSub extends SequentialCommandGroup {
    public CMD_IntakeSub(SUB_SubBucket p_subBucket, SUB_Bucket p_bucket, SUB_SubBucketSlide p_subbucketslide, GlobalVariables p_variables) {
        addCommands(
                new InstantCommand(() -> p_subbucketslide.setTargetPosition(Constants.SubBucketSlideConst.kTransfer)),
                new CMD_SlideInPosition(p_subbucketslide),
                new InstantCommand(() -> p_subBucket.setPosition(Constants.SubBucketServoConst.kSubTransfer)),
                new InstantCommand(() -> p_bucket.setPosition(Constants.BucketServoConst.kBucketReceive)),
                new InstantCommand(p_subBucket::intakeReverse),
                new InstantCommand(()-> p_variables.setRobotState(GlobalVariables.RobotState.INTAKE))
        );
    }
}
