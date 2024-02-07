package org.firstinspires.ftc.teamcode.autonomous.constants;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

public class RedPositionConstants {
    public static Pose2d NEAR_START_POSE = new Pose2d(15, -63, Math.toRadians(90));

    public static Vector2d PURPLE_LEFT_VECTOR = new Vector2d(11, -37);
    public static double PURPLE_LEFT_HEADING = Math.toRadians(155);
    public static Vector2d PURPLE_CENTER_VECTOR = new Vector2d(13, -36); // 13, 33 for no scorer
    public static Vector2d PURPLE_RIGHT_VECTOR = new Vector2d(22.65, -44);
    public static double PURPLE_RIGHT_HEADING = Math.toRadians(90);



    public static Vector2d BACKDROP_LEFT_VECTOR = new Vector2d(47.85, -29);
    public static Vector2d BACKDROP_CENTER_VECTOR = new Vector2d(47.85, -35);
    public static Vector2d BACKDROP_CENTER_LEFT_VECTOR = new Vector2d(47.85, -35);
    public static Vector2d BACKDROP_CENTER_RIGHT_VECTOR = new Vector2d(47.85, -37.5);
    public static Vector2d BACKDROP_RIGHT_VECTOR = new Vector2d(47.85, -42);

    public static Vector2d DIFF_VECTOR = new Vector2d(0, -0.5);

    public static Vector2d RIGGING_UP_VECTOR = new Vector2d(5, -58);
    public static Vector2d RIGGING_DOWN_VECTOR = new Vector2d(-30, -58);
    public static Vector2d PIXEL_STACK_VECTOR = new Vector2d(-59.3,  -35);
}
