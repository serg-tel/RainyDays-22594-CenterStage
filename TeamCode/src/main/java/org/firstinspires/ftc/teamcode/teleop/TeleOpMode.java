package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.drive.BasicDrive;
import org.firstinspires.ftc.teamcode.drive.BasicDriveNoEncoders;
import org.firstinspires.ftc.teamcode.modules.PullUp;
import org.firstinspires.ftc.teamcode.modules.Deploy;
import org.firstinspires.ftc.teamcode.modules.Intake;
import org.firstinspires.ftc.teamcode.modules.Launch;
import org.firstinspires.ftc.teamcode.modules.Lift;

@TeleOp(name = "TeleOpMode")
public class TeleOpMode extends LinearOpMode {
    private BasicDrive basicDrive;
    private Lift lift;
    private Intake intake;
    private Deploy deploy;
    private PullUp claw;
    private Launch launch;
    private ElapsedTime runtime = new ElapsedTime();
    public FtcDashboard dashboard;

    @Override
    public void runOpMode() throws InterruptedException {
        initRobot();
        runtime.reset();
        waitForStart();
        // lift.runtimeReset();


        while (opModeIsActive()) {
            basicDrive.driveFieldCentric();
            // intake.tele();
            // lift.telePID();
            // deploy.easyTele();
            // claw.tele();

            telemetry.addData("Runtime", runtime.toString());

            telemetry.update();
        }
    }

    private void initRobot() {
        dashboard = FtcDashboard.getInstance();

        basicDrive = new BasicDrive(this);
        // lift = new Lift(this, dashboard);
        // intake = new Intake(this);
        // deploy = new Deploy(this);
        // claw = new PullUp(this);
        // launch = new Launch(this);

        telemetry.update();
    }
}
