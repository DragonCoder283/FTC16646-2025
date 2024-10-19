package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Bucket;

public class CMD_DeployBucket extends SequentialCommandGroup {
    public CMD_DeployBucket(SUB_Bucket p_bucket, GlobalVariables p_variables) {
        addCommands(
                new InstantCommand(() -> p_bucket.setPosition(Constants.BucketServoConst.kBucketDeployBucket)),
                new InstantCommand(()-> p_variables.setRobotState(GlobalVariables.RobotState.DEPLOY))
        );
    }
}