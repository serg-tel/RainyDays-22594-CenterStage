package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.modules.Intake;
import org.firstinspires.ftc.teamcode.modules.Lift;
import org.firstinspires.ftc.teamcode.modules.Scorer;
import org.firstinspires.ftc.teamcode.modules.vision.AllianceColor;
import org.firstinspires.ftc.teamcode.modules.vision.PropDetectionPipeline;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

@Autonomous
public class NearBackdropRedAutoScorer extends LinearOpMode {
    private FtcDashboard dashboard;
    private SampleMecanumDrive drive;
    private Intake intake;
    private Lift lift;
    private Scorer scorer;
    private OpenCvWebcam webcam;
    private PropDetectionPipeline pipeline;
    private PropDetectionPipeline.PropPosition position;
    private boolean sensor = false;
    @Override
    public void runOpMode() throws InterruptedException {
        init_robot();

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened() {
                webcam.startStreaming(640, 360, OpenCvCameraRotation.UPRIGHT);
                while (opModeInInit()) {
                    telemetry.addLine("camera turned on");
                    telemetry.addData("pos", pipeline.getPropPosition());
                    telemetry.update();
                }
                while (opModeIsActive() && sensor) {
                    // intake.autoControlSensor();
                }
            }
            @Override
            public void onError(int errorCode) {
            }
        });

        waitForStart();

        position = pipeline.getPropPosition();
        telemetry.addData("prop_pos", position);
        telemetry.update();

        webcam.closeCameraDeviceAsync(new OpenCvCamera.AsyncCameraCloseListener() {
            @Override
            public void onClose() {

            }
        });

        scorer.close_lower();
        scorer.take();
        lift.resetEncoders();

        switch (position) {
            case RIGHT:
                move_right();
                break;
            case CENTER:
                move_center();
                break;
            case LEFT:
                move_left();
                break;
        }
    }

    private void move_right() {
        Trajectory traj = drive.trajectoryBuilder(new Pose2d())
                .splineTo(new Vector2d(24, -1.6), Math.toRadians(-38))
                .build();

        drive.followTrajectory(traj);
        intake.setPower(0.3);
        sleep(700);
        intake.setPower(0);

        Trajectory traj1 = drive.trajectoryBuilder(traj.end(), true)
                .splineTo(new Vector2d(25.3, -32.88), Math.toRadians(-90))
                .build();
        drive.followTrajectory(traj1);
        lift.moveToPos2(400);
        scorer.deploy();
        sleep(600);
        lift.moveToPos2(0);

        Trajectory trajStrafe = drive.trajectoryBuilder(traj1.end(), false)
                .strafeLeft(8)
                .build();
        drive.followTrajectory(trajStrafe);
        sleep(500);
        scorer.open_lower();
        sleep(500);
        lift.moveToPos2(300);
        scorer.take();
        sleep(1500);
        lift.moveToPos2(0);
        sleep(400);

        Trajectory tra = drive.trajectoryBuilder(trajStrafe.end())
                .splineTo(new Vector2d(-0.3, -32.88), Math.toRadians(180))
                .build();
        drive.followTrajectory(tra);
    }

    private void move_center() {
        Trajectory traj = drive.trajectoryBuilder(new Pose2d())
                .lineTo(new Vector2d(26.1, 3.2))
                .build();
        drive.followTrajectory(traj);
        intake.setPower(0.25);
        sleep(700);
        intake.setPower(0);

        Trajectory traj1 = drive.trajectoryBuilder(traj.end(), true)
                .splineTo(new Vector2d(23.71, -32.38), Math.toRadians(-90))
                .build();
        drive.followTrajectory(traj1);
        lift.moveToPos2(400);
        scorer.deploy();
        sleep(600);
        lift.moveToPos2(0);

        sleep(500);
        scorer.open_lower();
        sleep(500);
        lift.moveToPos2(300);
        scorer.take();
        sleep(1500);
        lift.moveToPos2(0);
        sleep(400);


        Trajectory tra = drive.trajectoryBuilder(traj1.end())
                .splineTo(new Vector2d(-0.3, -32.88), Math.toRadians(180))
                .build();
        drive.followTrajectory(tra);


    }

    private void move_left() {
        Trajectory traj = drive.trajectoryBuilder(new Pose2d(), false)
                .forward(15)
                .splineTo(new Vector2d(24.3, 7.5), Math.toRadians(30))
                .build();
        drive.followTrajectory(traj);
        intake.setPower(0.32);
        sleep(700);
        intake.setPower(0);

        Trajectory traj1 = drive.trajectoryBuilder(traj.end(), true)
                .splineTo(new Vector2d(26.5, -32.88), Math.toRadians(-90))
                .build();
        drive.followTrajectory(traj1);
        lift.moveToPos2(400);
        scorer.deploy();
        sleep(450);
        lift.moveToPos2(0);

        Trajectory trajStrafe = drive.trajectoryBuilder(traj1.end(), false)
                .lineTo(new Vector2d(34.7, -34.4))
                .build();
        drive.followTrajectory(trajStrafe);
        scorer.open_lower();
        sleep(500);
        lift.moveToPos2(300);
        scorer.take();
        sleep(1500);
        lift.moveToPos2(0);
        sleep(100);

        Trajectory tra = drive.trajectoryBuilder(traj1.end())
                .splineTo(new Vector2d(-0.3, -33), Math.toRadians(180))
                .build();
        drive.followTrajectory(tra);


    }

    public void init_robot() {
        dashboard = FtcDashboard.getInstance();
        drive = new SampleMecanumDrive(this.hardwareMap);
        drive.setPoseEstimate(new Pose2d(0, 0, Math.toRadians(0)));
        intake = new Intake(this);
        lift = new Lift(this, dashboard);
        scorer = new Scorer(this, lift);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(
                hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        pipeline = new PropDetectionPipeline(AllianceColor.RED);
        webcam.setPipeline(pipeline);
        telemetry.update();
    }
}