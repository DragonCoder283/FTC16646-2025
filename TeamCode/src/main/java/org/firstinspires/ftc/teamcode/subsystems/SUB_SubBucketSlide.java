package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.ftclib.command.SubsystemBase;

public class SUB_SubBucketSlide extends SubsystemBase {
    DcMotorEx m_subbucketmotor;
    Telemetry m_telemetry;

    public SUB_SubBucketSlide(OpMode p_opMode) {
        m_telemetry = p_opMode.telemetry;
        m_subbucketmotor = p_opMode.hardwareMap.get(DcMotorEx.class, "submersibleSlide");
        m_subbucketmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m_subbucketmotor.setTargetPosition(0);
        m_subbucketmotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m_subbucketmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        m_subbucketmotor.setDirection(DcMotorSimple.Direction.FORWARD);
        m_subbucketmotor.setPower(1);
    }

    @Override
    public void periodic() {
        m_telemetry.addData("Sub-Bucket Slide Motor Current Value: ", m_subbucketmotor.getCurrentPosition());
        m_telemetry.addData("Sub-Bucket Slide Motor Target Value: ", m_subbucketmotor.getTargetPosition());
    }

    public int getCurrentPosition() {
        return m_subbucketmotor.getCurrentPosition();
    }

    public int getTargetPosition() {
        return m_subbucketmotor.getTargetPosition();
    }

    public void setTargetPosition(int position) {
        m_subbucketmotor.setTargetPosition(position);
    }

    public void startReset(){
        m_subbucketmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        m_subbucketmotor.setPower(-.5);
    }

    public void resetEncoder(){
        m_subbucketmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m_subbucketmotor.setTargetPosition(m_subbucketmotor.getCurrentPosition());
        m_subbucketmotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m_subbucketmotor.setPower(0);
    }
}
