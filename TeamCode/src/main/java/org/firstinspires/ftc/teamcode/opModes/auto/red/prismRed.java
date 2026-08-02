package org.firstinspires.ftc.teamcode.opModes.auto.red;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.control.Alliance;
import org.firstinspires.ftc.teamcode.opModes.auto.base.Prism;

@Autonomous
@Configurable
public class prismRed extends Prism {
    @Override
    protected void buildPaths() {
        Path1 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(120.500, 176.000),
                                new Pose(120.500, 128.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(323.5), Math.toRadians(218))
                .build();

        Path2 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(120.500, 128.000),
                                new Pose(120.500, 100.000)
                        )
                ).setConstantHeadingInterpolation(Math.toRadians(218))
                .addPath(
                        new BezierLine(
                                new Pose(120.500, 100.000),
                                new Pose(122.000, 80.000)
                        )
                ).setConstantHeadingInterpolation(Math.toRadians(218))
                .build();
    }

    @Override
    public void init() {
        super.init();
        follower.setStartingPose(new Pose(120.500, 176.000, Math.toRadians(323.5)));
        robot.setAlliance(Alliance.RED);
    }
}
