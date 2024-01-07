package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.drive.BasicDrive;
import org.firstinspires.ftc.teamcode.modules.PullUp;
import org.firstinspires.ftc.teamcode.modules.Deploy;
import org.firstinspires.ftc.teamcode.modules.Intake;
import org.firstinspires.ftc.teamcode.modules.Launch;
import org.firstinspires.ftc.teamcode.modules.Lift;

@TeleOp(name = "MainTeleOp")
public class MainTeleOp extends LinearOpMode {
    public BasicDrive basicDrive;
    public Lift lift;
    public Intake intake;
    public Deploy deploy;
    public PullUp pullUp;
    public Launch launch;
    private ElapsedTime runtime = new ElapsedTime();
    public FtcDashboard dashboard;

    @Override
    public void runOpMode() throws InterruptedException {
        initRobot();
        runtime.reset();
        waitForStart();
        lift.runtimeReset();
        basicDrive.runtimeReset();


        while (opModeIsActive()) {
            if (deploy.getDriveState()) {
                //basicDrive.driveFieldCentricEncoder();
            }
            //basicDrive.testing();
            //lift.telePID();
            //intake.tele();
            deploy.testDoubleBeamServos();
            //pullUp.tele();
            //launch.tele();

            telemetry.addData("Runtime", runtime.toString());

            telemetry.update();
        }
    }

    private void initRobot() {
        dashboard = FtcDashboard.getInstance();

        basicDrive = new BasicDrive(this, dashboard);
        lift = new Lift(this, dashboard);
        intake = new Intake(this);
        deploy = new Deploy(this);
        pullUp = new PullUp(this, dashboard);
        //launch = new Launch(this);


        telemetry.update();
    }
}