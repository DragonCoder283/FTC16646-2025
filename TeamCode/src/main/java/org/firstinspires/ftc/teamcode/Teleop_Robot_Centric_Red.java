package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.apache.commons.math3.analysis.function.Add;
import org.firstinspires.ftc.teamcode.commands.*;

import org.firstinspires.ftc.teamcode.ftclib.command.ConditionalCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.ParallelCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.WaitCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.button.GamepadButton;
import org.firstinspires.ftc.teamcode.ftclib.first.math.trajectory.TrapezoidProfile;
import org.firstinspires.ftc.teamcode.ftclib.gamepad.GamepadEx;
import org.firstinspires.ftc.teamcode.ftclib.gamepad.GamepadKeys;
import org.firstinspires.ftc.teamcode.ftclib.command.Command;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Bucket;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Teleop Red", group ="Teleop Red")
public class Teleop_Robot_Centric_Red extends LinearOpMode {

     public RobotContainer m_robot;
//     public SUB_Bucket m_bucket;
     private GamepadEx m_driverOp;
     private GamepadEx m_toolOp;
     private boolean m_setFieldCentric = false;

     private static ElapsedTime m_runTime = new ElapsedTime();
     private ElapsedTime m_releaseTimeout = new ElapsedTime();

     public void initialize() {
          telemetry.clearAll();
          telemetry.addData("init complete", true);

          m_runTime.reset();
     }

     @Override
     public void runOpMode() throws InterruptedException {
          initializeSubsystems();
          // waitForStart();
          while (!opModeIsActive() && !isStopRequested()) {
               telemetry.update();
          }

          m_runTime.reset();
          while (!isStopRequested() && opModeIsActive()) {
               m_robot.run(); // run the scheduler

               m_robot.drivetrain.update();
               Pose2d poseEstimate = m_robot.drivetrain.getPoseEstimate();
               telemetry.addData("ODM:","x[%3.2f] y[%3.2f] heading(%3.2f)", poseEstimate.getX(), poseEstimate.getY(), Math.toDegrees(poseEstimate.getHeading()));
               telemetry.addData("RobotState", m_robot.GlobalVariables.getRobotState().name());
               telemetry.addData("intake mode", m_robot.GlobalVariables.getIntakeState().name());
               telemetry.update();
          }

          //
          endOfOpMode();
          m_robot.reset();
     }

     public void endOfOpMode() {

     }

     public void initializeSubsystems() {
          m_robot = new RobotContainer(this);
//          m_bucket = new SUB_Bucket(this);
          m_driverOp = new GamepadEx(gamepad1);
          m_toolOp = new GamepadEx(gamepad2);

          setSide();

          //drivetrain initialization
          m_robot.drivetrain.setPoseEstimate(new Pose2d(0, 0, Math.toDegrees(0)));
          m_robot.drivetrain.setFieldCentric(false);
          m_robot.drivetrain.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
          m_robot.drivetrain.setDefaultCommand(new RR_MecanumDriveDefault(m_robot.drivetrain, m_driverOp,0.0,0.01));
          //button bindings and global variables initialization
          configureButtonBindings();
     }

     public void configureButtonBindings() {
          AddButtonCommand(m_driverOp, GamepadKeys.Button.A, new ConditionalCommand(
                  new InstantCommand(() -> m_robot.m_subBucket.intakeOff()),
                  new InstantCommand(() -> m_robot.m_subBucket.intakeOn()),
                  ()->m_robot.m_subBucket.intakeIsOn()
          ));

          AddButtonCommand(m_driverOp, GamepadKeys.Button.B, new ConditionalCommand(
                  new InstantCommand(() -> m_robot.m_subBucket.setPosition(Constants.SubBucketServoConst.kSubIntake)),
                  new InstantCommand(() -> m_robot.m_subBucket.setPosition(Constants.SubBucketServoConst.kSubReadyToIntake)),
                  () -> m_robot.m_subBucket.isReadyToIntake()
          ));

          AddButtonCommand(m_driverOp, GamepadKeys.Button.X, new InstantCommand(() -> m_robot.GlobalVariables.setIntakeState(GlobalVariables.IntakeState.SUBMERSIBLE)));

          AddButtonCommand(m_driverOp, GamepadKeys.Button.Y, new InstantCommand(() -> m_robot.GlobalVariables.setIntakeState(GlobalVariables.IntakeState.WALL)));

          AddButtonCommand(m_driverOp, GamepadKeys.Button.RIGHT_BUMPER, new CMD_HandleReadyToIntake(m_robot));

          AddButtonCommand(m_driverOp, GamepadKeys.Button.LEFT_BUMPER, new CMD_HandleReadyToDeploy(m_robot));

          AddButtonCommand(m_driverOp, GamepadKeys.Button.DPAD_LEFT, new CMD_ReadyToIntake(m_robot.m_subBucket, m_robot.m_bucket, m_robot.m_subbucketslide, m_robot.m_bucketLift, m_robot.GlobalVariables));

          AddButtonCommand(m_driverOp, GamepadKeys.Button.DPAD_UP, new CMD_Intake(m_robot.m_subBucket, m_robot.m_bucket, m_robot.m_subbucketslide, m_robot.m_bucketLift, m_robot.GlobalVariables));

          AddButtonCommand(m_driverOp, GamepadKeys.Button.DPAD_RIGHT, new CMD_StowIntake(m_robot.m_subBucket, m_robot.m_bucket, m_robot.m_subbucketslide, m_robot.m_bucketLift, m_robot.GlobalVariables));

          AddButtonCommand(m_driverOp, GamepadKeys.Button.BACK, new CMD_ResetToHome(m_robot.m_bucket, m_robot.m_bucketLift, m_robot.m_subBucket, m_robot.m_subbucketslide, m_robot.GlobalVariables));
     }


     public void setSide() {
          m_robot.setRedSide();
     }

     public void AddButtonCommand(GamepadEx gamepad, GamepadKeys.Button button, Command command) {
          (new GamepadButton(gamepad, button)).whenPressed(command, true);
     }

     public void AddButtonCommandNoInterrupt(GamepadEx gamepad, GamepadKeys.Button button, Command command) {
          (new GamepadButton(gamepad, button)).whenPressed(command, false);
     }
}