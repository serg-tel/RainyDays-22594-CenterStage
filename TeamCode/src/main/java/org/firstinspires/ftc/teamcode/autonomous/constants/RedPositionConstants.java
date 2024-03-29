package org.firstinspires.ftc.teamcode.autonomous.constants;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

@Config
public class RedPositionConstants {
    public static Pose2d NEAR_START_POSE = new Pose2d(15, -63 + 0.19, Math.toRadians(90));

    public static Vector2d PURPLE_LEFT_VECTOR = new Vector2d(11, -37);
    public static double PURPLE_LEFT_HEADING = Math.toRadians(155);
    public static Vector2d PURPLE_CENTER_VECTOR = new Vector2d(13, -36); // 13, 33 for no scorer
    public static Vector2d PURPLE_RIGHT_VECTOR = new Vector2d(23.2, -44);
    public static double PURPLE_RIGHT_HEADING = Math.toRadians(90);



    public static Vector2d BACKDROP_LEFT_VECTOR = new Vector2d(49.5, -29);
    public static Vector2d BACKDROP_CENTER_VECTOR = new Vector2d(48.9, -35);
    public static Vector2d BACKDROP_CENTER_LEFT_VECTOR = new Vector2d(48.9, -35);
    public static Vector2d BACKDROP_CENTER_RIGHT_VECTOR = new Vector2d(48.9, -37.5);
    public static Vector2d BACKDROP_RIGHT_VECTOR = new Vector2d(49.6, -42);

    public static Vector2d DIFF_VECTOR = new Vector2d(0, -0.5);

    public static Vector2d RIGGING_UP_VECTOR = new Vector2d(5, -59);
    public static Vector2d RIGGING_DOWN_VECTOR = new Vector2d(-30, -59);
    public static Vector2d DOOR_UP_VECTOR = new Vector2d(24, -5);
    public static Vector2d DOOR_DOWN_VECTOR = new Vector2d(-40, -5);
    public static Vector2d PIXEL_STACK_VECTOR = new Vector2d(-58.3,  -35);
}
