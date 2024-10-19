package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.ftclib.command.ConditionalCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Bucket;
import org.firstinspires.ftc.teamcode.subsystems.SUB_BucketLift;
import org.firstinspires.ftc.teamcode.subsystems.SUB_SubBucket;
import org.firstinspires.ftc.teamcode.subsystems.SUB_SubBucketSlide;

public class CMD_ReadyToDeploy extends SequentialCommandGroup {
    public CMD_ReadyToDeploy(SUB_Bucket p_bucket, SUB_BucketLift p_bucketlift, GlobalVariables p_variables) {
        addCommands(
                new ConditionalCommand(
                        new CMD_ReadyToDeployChamber(p_bucketlift),
                        new CMD_ReadyToDeployBucket(p_bucket, p_bucketlift, p_variables),
                        () -> p_variables.isIntakeState(GlobalVariables.IntakeState.WALL)
                )
        );
    }
}
