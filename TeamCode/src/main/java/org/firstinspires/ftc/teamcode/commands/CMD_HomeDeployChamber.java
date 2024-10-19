package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_BucketLift;

public class CMD_HomeDeployChamber extends SequentialCommandGroup {
    public CMD_HomeDeployChamber(SUB_BucketLift p_bucketlift) {
        addCommands(
                new CMD_BucketLiftReset(p_bucketlift),
                new CMD_LiftInPosition(p_bucketlift)
        );
    }
}
