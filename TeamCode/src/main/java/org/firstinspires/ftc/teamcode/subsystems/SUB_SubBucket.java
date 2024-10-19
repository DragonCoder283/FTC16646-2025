package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.ftclib.command.SubsystemBase;

public class SUB_SubBucket extends SubsystemBase {
    Servo m_subbucketservo;
    CRServo m_subintakeservo;
    boolean m_bucketReadyToIntake = true;
    boolean intakeIsOn = false;
    public SUB_SubBucket(OpMode p_opMode){
        m_subbucketservo = p_opMode.hardwareMap.get(Servo.class, "SubBucketServo");
        m_subbucketservo.setPosition(Constants.SubBucketServoConst.kSubHome);
        m_subintakeservo = p_opMode.hardwareMap.get(CRServo.class, "SubIntakeServo");
    }

    public boolean isReadyToIntake(){
        return m_bucketReadyToIntake;
    }

    public void setPosition(double position) {
        m_subbucketservo.setPosition(position);
        if (position == Constants.SubBucketServoConst.kSubReadyToIntake) {
            m_bucketReadyToIntake = true;
        } else {
            m_bucketReadyToIntake = false;
        }
    }

    public void intakeOn() {
        m_subintakeservo.setPower(1);
        intakeIsOn = true;
    }

    public void intakeOff() {
        m_subintakeservo.setPower(0);
        intakeIsOn = false;
    }

    public void intakeReverse() {
        m_subintakeservo.setPower(-0.25);
        intakeIsOn = true;
    }

    public boolean intakeIsOn() {
        return intakeIsOn;
    }
}
