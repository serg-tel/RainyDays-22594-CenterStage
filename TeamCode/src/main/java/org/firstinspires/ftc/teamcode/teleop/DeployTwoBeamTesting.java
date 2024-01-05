package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.modules.Deploy;

@Config
@TeleOp(name = "DeployTwoBeamTesting")
public class DeployTwoBeamTesting extends LinearOpMode {
    private final ElapsedTime runtime = new ElapsedTime();
    private Servo servoRotationBeamLeft;
    private Servo servoRotationBeamRight;
    public FtcDashboard dashboard;
    public static double SERVO_POS = 0.5;

    @Override
    public void runOpMode() throws InterruptedException {
        initRobot();
        runtime.reset();
        waitForStart();

        while (opModeIsActive()) {
            if (gamepad2.a) {
                servoRotationBeamRight.setPosition(SERVO_POS);
            }

            telemetry.addData("Runtime", runtime.toString());
            telemetry.update();
        }
    }

    private void initRobot() {
        dashboard = FtcDashboard.getInstance();

        servoRotationBeamRight = hardwareMap.get(Servo.class, "servo_rotation_beam_right");

        telemetry.update();
    }
}