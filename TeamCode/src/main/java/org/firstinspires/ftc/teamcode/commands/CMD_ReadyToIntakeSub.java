package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.RobotContainer;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Bucket;
import org.firstinspires.ftc.teamcode.subsystems.SUB_SubBucket;
import org.firstinspires.ftc.teamcode.subsystems.SUB_SubBucketSlide;

public class CMD_ReadyToIntakeSub extends SequentialCommandGroup {
    public CMD_ReadyToIntakeSub(SUB_SubBucket p_subBucket, SUB_Bucket p_bucket, SUB_SubBucketSlide p_subbucketslide, GlobalVariables p_variables) {
        addCommands(
                new InstantCommand(()-> p_variables.setRobotState(GlobalVariables.RobotState.TRANSITIONING_TO_INTAKE)),
                new InstantCommand(() -> p_subBucket.setPosition(Constants.SubBucketServoConst.kSubReadyToIntake)),
                new InstantCommand(() -> p_subbucketslide.setTargetPosition(Constants.SubBucketSlideConst.kIntake)),
                new CMD_SlideInPosition(p_subbucketslide),
                new InstantCommand(()-> p_variables.setRobotState(GlobalVariables.RobotState.READY_TO_INTAKE))
        );
    }


}
