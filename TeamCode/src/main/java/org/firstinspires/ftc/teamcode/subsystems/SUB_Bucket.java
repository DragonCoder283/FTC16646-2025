package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.ftclib.command.SubsystemBase;

public class SUB_Bucket extends SubsystemBase {
    Servo m_bucketservo;
    public SUB_Bucket(OpMode p_opMode) {
        m_bucketservo = p_opMode.hardwareMap.get(Servo.class, "BucketServo");
        m_bucketservo.setPosition(Constants.BucketServoConst.kBucketHome);
    }

    public void setPosition(double position) {
        m_bucketservo.setPosition(position);
    }
}
