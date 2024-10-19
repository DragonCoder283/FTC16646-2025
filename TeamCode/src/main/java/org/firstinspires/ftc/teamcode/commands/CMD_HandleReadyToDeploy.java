package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.RobotContainer;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;

public class CMD_HandleReadyToDeploy extends CommandBase {
    RobotContainer m_robot;
    public CMD_HandleReadyToDeploy(RobotContainer p_robot) {
        m_robot = p_robot;
    }

    @Override
    public void initialize() {
        if (m_robot.GlobalVariables.isRobotState(GlobalVariables.RobotState.STOW)||
                m_robot.GlobalVariables.isRobotState(GlobalVariables.RobotState.READY_TO_DEPLOY)||
                m_robot.GlobalVariables.isRobotState(GlobalVariables.RobotState.DEPLOY) ||
                m_robot.GlobalVariables.isRobotState(GlobalVariables.RobotState.HOME)) {

        } else {
            return;
        }

        if (m_robot.GlobalVariables.isRobotState(GlobalVariables.RobotState.STOW)) {
            m_robot.schedule(
                    new SequentialCommandGroup(
                            new CMD_ReadyToDeploy(m_robot.m_bucket, m_robot.m_bucketLift, m_robot.GlobalVariables)
                    )
            );
        }

        if (m_robot.GlobalVariables.isRobotState(GlobalVariables.RobotState.READY_TO_DEPLOY)) {
            m_robot.schedule(
                    new SequentialCommandGroup(
                            new CMD_Deploy(m_robot.m_bucket, m_robot.m_bucketLift, m_robot.GlobalVariables)
                    )
            );
        }

        if (m_robot.GlobalVariables.isRobotState(GlobalVariables.RobotState.DEPLOY)) {
            m_robot.schedule(
                    new SequentialCommandGroup(
                            new CMD_HomeDeploy(m_robot.m_bucket, m_robot.m_bucketLift, m_robot.GlobalVariables)
                    )
            );
        }
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}
