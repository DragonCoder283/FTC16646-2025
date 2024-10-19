package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ftclib.command.Command;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.*;

public class CMD_SubBucketSlideReset extends CommandBase {
    SUB_SubBucketSlide m_bucketslide;
    boolean m_isFinished = false;
    boolean m_isMoving = true;
    int m_previousEncoderValue;

    ElapsedTime m_runTime = new ElapsedTime();

    public CMD_SubBucketSlideReset(SUB_SubBucketSlide p_bucketslide) {
        m_bucketslide = p_bucketslide;
        addRequirements(p_bucketslide);
    }

    @Override
    public void initialize() {
        m_isFinished = false;
        m_previousEncoderValue = m_bucketslide.getCurrentPosition() + 10;
        m_bucketslide.startReset();
        m_isMoving = true;
    }

    @Override
    public void execute(){
        if (m_isMoving) {
            if (m_previousEncoderValue <= m_bucketslide.getCurrentPosition()) {
                m_isMoving = false;
                m_runTime.reset();
            }
            m_previousEncoderValue = m_bucketslide.getCurrentPosition();
        } else {
            // wait for the elevator is stabilize before finish
            if (m_runTime.milliseconds() > 200) m_isFinished = true;
        }
    }

    @Override
    public boolean isFinished() {
        return m_isFinished;
    }

    @Override
    public void end(boolean interrupted){
        m_bucketslide.resetEncoder();
    }
}