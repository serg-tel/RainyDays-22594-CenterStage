package org.firstinspires.ftc.teamcode.teleop.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.modules.Intake;
import org.firstinspires.ftc.teamcode.modules.Lift;
import org.firstinspires.ftc.teamcode.modules.Scorer;

@Config
@TeleOp(group = "test")
public class ScoringTesting extends LinearOpMode {
    private final ElapsedTime runtime = new ElapsedTime();
    private Intake intake;
    private Lift lift;
    private Scorer scorer;
    public FtcDashboard dashboard;
    public static double SERVO_POS = 0.5;
    public static double SERVO_POS_BOX = 0.5;
    public static double diff = 0.043;

    @Override
    public void runOpMode() throws InterruptedException {
        initRobot();
        runtime.reset();
        waitForStart();

        while (opModeIsActive()) {
            intake.opControlOld();
            lift.opControl();
            scorer.opControl();

            telemetry.addData("Runtime", runtime.toString());
            telemetry.update();
        }
    }

    private void initRobot() {
        dashboard = FtcDashboard.getInstance();

        intake = new Intake(this);
        lift = new Lift(this, dashboard);
        scorer = new Scorer(this, lift);

        telemetry.update();
    }
}
