package org.firstinspires.ftc.teamcode.opModes.auto.blue;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.control.Alliance;
import org.firstinspires.ftc.teamcode.opModes.auto.base.Prism;

@Autonomous
@Configurable
public class prismBlue extends Prism {
    @Override
    protected void buildPaths() {
        Path1 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(71.500, 176.000),
                                new Pose(71.500, 128.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(216.5), Math.toRadians(325))
                .build();

        Path2 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(71.500, 128.000),
                                new Pose(71.500, 100.000)
                        )
                ).setConstantHeadingInterpolation(325)
                .addPath(
                        new BezierCurve(
                                new Pose(71.500, 100.000),
                                new Pose(83.000, 100.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(325), Math.toRadians(180))
                .build();
    }

    @Override
    public void init() {
        super.init();
        follower.setStartingPose(new Pose(71.500, 176.000, Math.toRadians(216.5)));
        robot.setAlliance(Alliance.BLUE);
    }
}