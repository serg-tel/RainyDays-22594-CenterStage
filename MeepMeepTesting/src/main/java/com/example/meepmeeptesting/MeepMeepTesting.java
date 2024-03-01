package com.example.meepmeeptesting;

import static com.example.meepmeeptesting.PositionConstants.BACKDROP_CENTER_COORDS;
import static com.example.meepmeeptesting.PositionConstants.BACKDROP_CENTER_VECTOR;
import static com.example.meepmeeptesting.PositionConstants.BACKDROP_RIGHT_COORDS;
import static com.example.meepmeeptesting.PositionConstants.BACKDROP_RIGHT_VECTOR;
import static com.example.meepmeeptesting.PositionConstants.DOOR_DOWN_COORDS;
import static com.example.meepmeeptesting.PositionConstants.DOOR_UP_COORDS;
import static com.example.meepmeeptesting.PositionConstants.END;
import static com.example.meepmeeptesting.PositionConstants.FAR_START_COORDS;
import static com.example.meepmeeptesting.PositionConstants.FIRST_PIXEL_STACK_COORDS;
import static com.example.meepmeeptesting.PositionConstants.NEAR_START_COORDS;
import static com.example.meepmeeptesting.PositionConstants.PURPLE_CENTER_FAR;
import static com.example.meepmeeptesting.PositionConstants.PURPLE_CENTER_NEAR;
import static com.example.meepmeeptesting.PositionConstants.PURPLE_LEFT_FAR;
import static com.example.meepmeeptesting.PositionConstants.PURPLE_LEFT_NEAR;
import static com.example.meepmeeptesting.PositionConstants.SECOND_PIXEL_STACK_COORDS;
import static com.example.meepmeeptesting.PositionConstants.START_HEADING;
import static com.example.meepmeeptesting.PositionConstants.THIRD_PIXEL_STACK_COORDS;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.SampleMecanumDrive;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(40, 75, 3.5, 50, 6.5)
                .setDimensions(16.83, 15.7677)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(NEAR_START_COORDS[0], NEAR_START_COORDS[1], START_HEADING))
                                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                                    //scorer.closeLower();
                                    //scorer.closeUpper();
                                    //scorer.deployAuto();
                                    //lift.resetEncoders();
                                })
                                .waitSeconds(0.8)
                                .lineTo(new Vector2d(PURPLE_CENTER_NEAR[0], PURPLE_CENTER_NEAR[1]))
                                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                                    //scorer.openLower();
                                })
                                .UNSTABLE_addTemporalMarkerOffset(0.1, () -> {
                                    //scorer.deployAutoPush();
                                })
                                .UNSTABLE_addTemporalMarkerOffset(0.2, () -> {
                                    //scorer.deploy();
                                })
                                .waitSeconds(0.2)

                                // GOING TO BACKDROP
                                .lineToSplineHeading(new Pose2d(BACKDROP_CENTER_COORDS[0], BACKDROP_CENTER_COORDS[1], Math.toRadians(0)))
                                .UNSTABLE_addTemporalMarkerOffset(0.05, () -> {
                                    //scorer.openUpper();
                                })
                                .UNSTABLE_addTemporalMarkerOffset(0.35, () -> {
                                    //scorer.take();
                                })
                                .waitSeconds(0.4)

                                // GOING TO STACK
                                .setReversed(true)
                                .setVelConstraint(SampleMecanumDrive.getVelocityConstraint(50, 3.5, 6.5))
                                .splineToConstantHeading(new Vector2d(DOOR_UP_COORDS[0], DOOR_UP_COORDS[1]), Math.toRadians(180))
                                .splineToConstantHeading(new Vector2d(DOOR_DOWN_COORDS[0], DOOR_DOWN_COORDS[1]), Math.toRadians(180))
                                .resetVelConstraint()
                                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                                    //intake.take();
                                    //intake.openLeftFlap();
                                })
                                .splineToConstantHeading(new Vector2d(THIRD_PIXEL_STACK_COORDS[0] + 1, THIRD_PIXEL_STACK_COORDS[1] - 2.5), Math.toRadians(90))
                                .setVelConstraint(SampleMecanumDrive.getVelocityConstraint(30, 3.5, 6.5))
                                .splineToConstantHeading(new Vector2d(THIRD_PIXEL_STACK_COORDS[0], THIRD_PIXEL_STACK_COORDS[1] + 4), Math.toRadians(90))
                                .setReversed(false)
                                .resetVelConstraint()
                                .waitSeconds(0.8)

                                // GOING TO BACKDROP
                                .setVelConstraint(SampleMecanumDrive.getVelocityConstraint(50, 3.5, 6.5))
                                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                                    //scorer.closeLower();
                                    //scorer.closeUpper();
                                    //intake.eject();
                                    //intake.closeLeftFlap();
                                })
                                .UNSTABLE_addTemporalMarkerOffset(0.8, () -> {
                                    //intake.stop();
                                })
                                .splineToConstantHeading(new Vector2d(DOOR_DOWN_COORDS[0], DOOR_DOWN_COORDS[1]), Math.toRadians(0))
                                .splineToConstantHeading(new Vector2d(DOOR_UP_COORDS[0], DOOR_UP_COORDS[1]), Math.toRadians(0))
                                .resetVelConstraint()
                                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                                    //scorer.deploy();
                                    //lift.setReference(570);
                                })
                                .splineToConstantHeading(BACKDROP_RIGHT_VECTOR, Math.toRadians(0))
                                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                                    //scorer.openLower();
                                    //scorer.openUpper();
                                })
                                .UNSTABLE_addTemporalMarkerOffset(0.3, () -> {
                                    //scorer.take();
                                    //lift.setReference(0);
                                })
                                .waitSeconds(0.4)

                                // GOING TO STACK
                                .setReversed(true)
                                .setVelConstraint(SampleMecanumDrive.getVelocityConstraint(50, 3.5, 6.5))
                                .splineToConstantHeading(new Vector2d(DOOR_UP_COORDS[0], DOOR_UP_COORDS[1]), Math.toRadians(180))
                                .splineToConstantHeading(new Vector2d(DOOR_DOWN_COORDS[0], DOOR_DOWN_COORDS[1]), Math.toRadians(180))
                                .resetVelConstraint()
                                .UNSTABLE_addTemporalMarkerOffset(0.1, () -> {
                                    //intake.take();
                                })
                                .splineToConstantHeading(new Vector2d(THIRD_PIXEL_STACK_COORDS[0], THIRD_PIXEL_STACK_COORDS[1] + 3), Math.toRadians(90))
                                .setReversed(false)
                                .waitSeconds(0.8)

                                // GOING TO BACKDROP
                                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                                    //scorer.closeLower();
                                    //scorer.closeUpper();
                                    //intake.eject();
                                })
                                .UNSTABLE_addTemporalMarkerOffset(0.8, () -> {
                                    //intake.stop();
                                })
                                .splineToConstantHeading(new Vector2d(DOOR_DOWN_COORDS[0], DOOR_DOWN_COORDS[1]), Math.toRadians(0))
                                .splineToConstantHeading(new Vector2d(DOOR_UP_COORDS[0], DOOR_UP_COORDS[1]), Math.toRadians(0))
                                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                                    //scorer.deploy();
                                    //lift.setReference(570);
                                })
                                .splineToConstantHeading(BACKDROP_RIGHT_VECTOR, Math.toRadians(0))
                                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                                    //scorer.openLower();
                                    //scorer.openUpper();
                                })
                                .UNSTABLE_addTemporalMarkerOffset(0.3, () -> {
                                    //scorer.take();
                                    //lift.setReference(0);
                                })
                                .waitSeconds(0.4)

                                // PARKING
                                .lineToSplineHeading(new Pose2d(END[0], END[1], Math.toRadians(END[2])))
                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}