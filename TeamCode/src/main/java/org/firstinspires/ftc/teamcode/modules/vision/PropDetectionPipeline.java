package org.firstinspires.ftc.teamcode.modules.vision;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class PropDetectionPipeline extends OpenCvPipeline {
	public int PROP_HEIGHT = 0;
	private final AllianceColor ALLIANCECOLOR;
    Mat ybcrcb = new Mat();
    Mat leftCrop, centerCrop, rightCrop;
    double avgLeft, avgCenter, avgRight;
    Mat output = new Mat();
	
    Scalar rectColorLeft = new Scalar(255.0, 0.0, 0.0);
    Scalar rectColorCenter = new Scalar(255.0, 0.0, 0.0);
    Scalar rectColorRight = new Scalar(255.0, 0.0, 0.0);
    public enum PropPosition {
        LEFT,
        CENTER,
        RIGHT
    }
    public volatile PropPosition position;

    public PropDetectionPipeline(AllianceColor color) {
        this.ALLIANCECOLOR = color;
    }

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, ybcrcb, Imgproc.COLOR_RGB2YCrCb);

        Rect leftRect = null;
        Rect centerRect = null;
        Rect rightRect = null;

        switch (ALLIANCECOLOR) {
            case RED:
                leftRect = new Rect(50, 140, 120, 120);
                centerRect = new Rect(260, 120, 100, 100);
                rightRect = new Rect(480, 140, 120, 120);
                break;
            case BLUE:
                leftRect = new Rect(80, 140, 100, 100);
                centerRect = new Rect(280, 120, 100, 100);
                rightRect = new Rect(500, 140, 100, 100);
                break;
        }

        input.copyTo(output);

        leftCrop = ybcrcb.submat(leftRect);
        centerCrop = ybcrcb.submat(centerRect);
        rightCrop = ybcrcb.submat(rightRect);

        switch (ALLIANCECOLOR) {
            case RED:
                Core.extractChannel(leftCrop, leftCrop, 1);
                Core.extractChannel(centerCrop, centerCrop, 1);
                Core.extractChannel(rightCrop, rightCrop, 1);
                break;
            case BLUE:
                Core.extractChannel(leftCrop, leftCrop, 2);
                Core.extractChannel(centerCrop, centerCrop, 2);
                Core.extractChannel(rightCrop, rightCrop, 2);
                break;
        }

        avgLeft = Core.mean(leftCrop).val[0];
        avgCenter = Core.mean(centerCrop).val[0];
        avgRight = Core.mean(rightCrop).val[0];
		
        double mx = Math.max(avgLeft, Math.max(avgCenter, avgRight));

        if (mx == avgLeft) {
            rectColorLeft = new Scalar(0.0, 255.0, 0.0);
            rectColorCenter = new Scalar(255.0, 0.0, 0.0);
            rectColorRight = new Scalar(255.0, 0.0, 0.0);
            position = PropPosition.LEFT;
        }
        else if (mx == avgCenter) {
            rectColorLeft = new Scalar(255.0, 0.0, 0.0);
            rectColorCenter = new Scalar(0.0, 255.0, 0.0);
            rectColorRight = new Scalar(255.0, 0.0, 0.0);
            position = PropPosition.CENTER;
        }
        else if (mx == avgRight) {
            rectColorLeft = new Scalar(255.0, 0.0, 0.0);
            rectColorCenter = new Scalar(255.0, 0.0, 0.0);
            rectColorRight = new Scalar(0.0, 255.0, 0.0);
            position = PropPosition.RIGHT;
        }
		
       
        Imgproc.rectangle(output, leftRect, rectColorLeft, 2);
        Imgproc.rectangle(output, centerRect, rectColorCenter, 2);
        Imgproc.rectangle(output, rightRect, rectColorRight, 2);

        return output;
    }

    public PropPosition getPropPosition() {
        return position;
    }
}
