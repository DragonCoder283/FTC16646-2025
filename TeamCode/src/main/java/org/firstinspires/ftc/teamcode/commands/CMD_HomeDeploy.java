package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.ConditionalCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Bucket;
import org.firstinspires.ftc.teamcode.subsystems.SUB_BucketLift;

public class CMD_HomeDeploy extends SequentialCommandGroup {
    public CMD_HomeDeploy(SUB_Bucket p_bucket, SUB_BucketLift p_bucketlift, GlobalVariables p_variables) {
        addCommands(
                new ConditionalCommand(
                        new CMD_HomeDeployChamber(p_bucketlift),
                        new CMD_HomeDeployBucket(p_bucket, p_bucketlift, p_variables),
                        () -> p_variables.isIntakeState(GlobalVariables.IntakeState.WALL)
                )
        );
    }
}
