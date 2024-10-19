package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_BucketLift;

public class CMD_IntakeWall extends SequentialCommandGroup {
    public CMD_IntakeWall(SUB_BucketLift p_bucketlift, GlobalVariables p_variables) {
        addCommands(
                new InstantCommand(() -> p_bucketlift.setPosition(Constants.BucketLiftConst.kIntakeWall)),
                new CMD_LiftInPosition(p_bucketlift),
                new InstantCommand(() -> p_variables.setRobotState(GlobalVariables.RobotState.INTAKE))
        );
    }
}
