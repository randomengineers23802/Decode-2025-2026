package org.firstinspires.ftc.teamcode.opModes.auto.blue;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.control.Alliance;
import org.firstinspires.ftc.teamcode.opModes.auto.Far;

@Autonomous
@Configurable
public class farBlue extends Far {
    @Override
    protected void buildPaths() {
        Path1 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(56.875, 8.563),

                                new Pose(58.000, 15.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(287))

                .build();

        Path2 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(58.000, 15.000),
                                new Pose(66.000, 40.000),
                                new Pose(10.000, 37.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(287), Math.toRadians(180), 0.2)

                .build();

        Path3 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(10.000, 37.000),

                                new Pose(58.000, 15.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(290))

                .build();

        Path4 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(58.000, 15.000),

                                new Pose(10.000, 17.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(290), Math.toRadians(210), 0.4)

                .build();

        Path5 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(10.000, 17.000),

                                new Pose(21.000, 17.000)
                        )
                ).setConstantHeadingInterpolation(180)

                .build();

        Path6 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(21.000, 17.000),

                                new Pose(15.000, 15.000)
                        )
                ).setConstantHeadingInterpolation(210)

                .build();

        Path7 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(15.000, 15.000),

                                new Pose(8.000, 8.500)
                        )
                ).setConstantHeadingInterpolation(Math.toRadians(210))

                .build();

        Path8 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(8.000, 8.500),

                                new Pose(58.000, 15.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(210), Math.toRadians(290))

                .build();

        Path9 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(58.000, 15.000),

                                new Pose(9.000, 12.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(290), Math.toRadians(180), 0.4)

                .build();

        Path10 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(9.000, 12.000),

                                new Pose(58.000, 15.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(290))

                .build();

        Path11 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(58.000, 15.000),

                                new Pose(9.000, 12.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(290), Math.toRadians(180), 0.4)

                .build();

        Path12 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(9.000, 12.000),

                                new Pose(58.000, 15.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(290))

                .build();

        Path13 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(58.000, 15.000),

                                new Pose(36.000, 15.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(290), Math.toRadians(180))

                .build();
    }

    @Override
    public void init() {
        super.init();
        follower.setStartingPose(new Pose(56.875, 8.5625, Math.toRadians(0)));
        robot.setAlliance(Alliance.BLUE);
    }
}