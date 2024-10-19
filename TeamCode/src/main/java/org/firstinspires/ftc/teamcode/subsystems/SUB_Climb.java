package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.ftclib.command.SubsystemBase;

public class SUB_Climb extends SubsystemBase {
    DcMotorEx m_climbmotor;
    public SUB_Climb(OpMode p_opMode) {
        m_climbmotor = p_opMode.hardwareMap.get(DcMotorEx.class, "climbMotor");

        m_climbmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m_climbmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        m_climbmotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void setPosition(int position) {
        m_climbmotor.setTargetPosition(position);
    }
}
