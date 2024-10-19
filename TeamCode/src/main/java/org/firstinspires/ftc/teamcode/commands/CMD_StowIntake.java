package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.ConditionalCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.ParallelCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Bucket;
import org.firstinspires.ftc.teamcode.subsystems.SUB_BucketLift;
import org.firstinspires.ftc.teamcode.subsystems.SUB_SubBucket;
import org.firstinspires.ftc.teamcode.subsystems.SUB_SubBucketSlide;

public class CMD_StowIntake extends SequentialCommandGroup {
    public CMD_StowIntake(SUB_SubBucket p_subBucket, SUB_Bucket p_bucket, SUB_SubBucketSlide p_subbucketslide, SUB_BucketLift p_bucketlift, GlobalVariables p_variables) {
        addCommands(
                new ConditionalCommand(
                        new ParallelCommandGroup( // stow intake wall
                                new CMD_BucketLiftReset(p_bucketlift),
                                new CMD_LiftInPosition(p_bucketlift)
                        ),
                        new CMD_StowIntakeSub(p_subBucket, p_bucket, p_subbucketslide, p_variables),
                        ()-> p_variables.isIntakeState(GlobalVariables.IntakeState.WALL)
                ),
                new InstantCommand(() -> p_variables.setRobotState(GlobalVariables.RobotState.INTAKE))
        );
    }
}
