package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.SUB_BucketLift;

public class CMD_LiftInPosition extends CommandBase {
    SUB_BucketLift m_bucketlift;
    public CMD_LiftInPosition(SUB_BucketLift p_leftbucketlift) {
        m_bucketlift = p_leftbucketlift;
    }

    @Override
    public boolean isFinished() {
        return Math.abs(m_bucketlift.getCurrentPosition() - m_bucketlift.getTargetPosition()) <= Constants.BucketLiftConst.kTolerance;
    }
}
