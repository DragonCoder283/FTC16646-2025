package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_BucketLift;

public class CMD_ReadyToDeployChamber extends SequentialCommandGroup {
    public CMD_ReadyToDeployChamber(SUB_BucketLift p_bucketlift) {
        addCommands(
                new InstantCommand(() -> p_bucketlift.setPosition(Constants.BucketLiftConst.kReadyToDeployChamber)),
                new CMD_LiftInPosition(p_bucketlift)
        );
    }
}
