package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.SUB_SubBucketSlide;

public class CMD_SlideInPosition extends CommandBase {
    SUB_SubBucketSlide m_subbucketslide;
    public CMD_SlideInPosition(SUB_SubBucketSlide p_subbucketslide) {
        m_subbucketslide = p_subbucketslide;
    }

    @Override
    public boolean isFinished() {
        return Math.abs(m_subbucketslide.getCurrentPosition() - m_subbucketslide.getTargetPosition()) <= Constants.SubBucketSlideConst.kTolerance;
    }
}
