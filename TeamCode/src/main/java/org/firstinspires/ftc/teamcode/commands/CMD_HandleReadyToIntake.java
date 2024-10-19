package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.RobotContainer;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Bucket;
import org.firstinspires.ftc.teamcode.subsystems.SUB_BucketLift;
import org.firstinspires.ftc.teamcode.subsystems.SUB_SubBucket;
import org.firstinspires.ftc.teamcode.subsystems.SUB_SubBucketSlide;

public class CMD_HandleReadyToIntake extends CommandBase {
    RobotContainer m_robot;
    public CMD_HandleReadyToIntake(RobotContainer p_robot) {
        m_robot = p_robot;
    }

    @Override
    public void initialize() {
        if (m_robot.GlobalVariables.isRobotState(GlobalVariables.RobotState.HOME)||
            m_robot.GlobalVariables.isRobotState(GlobalVariables.RobotState.READY_TO_INTAKE)||
            m_robot.GlobalVariables.isRobotState(GlobalVariables.RobotState.INTAKE)||
            m_robot.GlobalVariables.isRobotState(GlobalVariables.RobotState.STOW)) {

        } else {
            return;
        }

        if (m_robot.GlobalVariables.isRobotState(GlobalVariables.RobotState.HOME)||
                m_robot.GlobalVariables.isRobotState(GlobalVariables.RobotState.STOW)) {
            m_robot.schedule(new CMD_ReadyToIntake(m_robot.m_subBucket, m_robot.m_bucket, m_robot.m_subbucketslide, m_robot.m_bucketLift, m_robot.GlobalVariables));
        }

        if (m_robot.GlobalVariables.isRobotState(GlobalVariables.RobotState.READY_TO_INTAKE)) {
            m_robot.schedule(
//                    new InstantCommand(()-> m_robot.m_subBucket.intakeOn())
                    new CMD_Intake(m_robot.m_subBucket, m_robot.m_bucket, m_robot.m_subbucketslide, m_robot.m_bucketLift, m_robot.GlobalVariables)
//                    new CMD_IntakeSub(m_robot.m_subBucket, m_robot.m_bucket, m_robot.m_subbucketslide, m_robot.GlobalVariables)
            );
        }

        if (m_robot.GlobalVariables.isRobotState(GlobalVariables.RobotState.INTAKE)) {
            m_robot.schedule(
                    new CMD_StowIntake(m_robot.m_subBucket, m_robot.m_bucket, m_robot.m_subbucketslide, m_robot.m_bucketLift, m_robot.GlobalVariables)
            );
        }
    }
}
