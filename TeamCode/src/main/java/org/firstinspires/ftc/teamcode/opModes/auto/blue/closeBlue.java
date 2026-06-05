package org.firstinspires.ftc.teamcode.opModes.auto.blue;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.control.Alliance;
import org.firstinspires.ftc.teamcode.opModes.auto.Close;

@Autonomous
@Configurable
public class closeBlue extends Close {
    @Override
    protected void buildPaths() {
        Path1 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(24.500, 128.000),

                                new Pose(58.000, 80.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(323.5), Math.toRadians(314))

                .build();

        Path2 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(58.000, 80.000),
                                new Pose(55.000, 59.000),
                                new Pose(10.000, 58.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(314), Math.toRadians(200), 0.15)

                .build();

        Path3 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(10.000, 58.000),
                                new Pose(39.000, 61.000),
                                new Pose(58.000, 80.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(200), Math.toRadians(314))

                .build();

        Path4 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(58.000, 80.000),
                                new Pose(60.000, 69.000),
                                new Pose(4.500, 56.500) //was 57
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(314), Math.toRadians(155), 0.15)

                .build();

        Path5 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(4.500, 56.500),
                                new Pose(40.000, 62.000),
                                new Pose(58.000, 80.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(120), Math.toRadians(314))

                .build();

        Path6 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(58.000, 80.000),
                                new Pose(60.000, 69.000),
                                new Pose(4.500, 56.500) //was 57
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(314), Math.toRadians(155), 0.15)

                .build();

        Path7 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(4.500, 56.500),
                                new Pose(40.000, 62.000),
                                new Pose(58.000, 80.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(155), Math.toRadians(314))

                .build();

        Path8 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(58.000, 80.000),

                                new Pose(18.000, 84.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(314), Math.toRadians(175), 0.15)

                .build();

        Path9 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(18.000, 84.000),

                                new Pose(58.000, 80.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(175), Math.toRadians(314))

                .build();

        Path10 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(58.000, 80.000),

                                new Pose(25.000, 70.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(314), Math.toRadians(270))

                .build();
    }

    @Override
    public void init() {
        super.init();
        follower.setStartingPose(new Pose(24.500, 128.000, Math.toRadians(323.5)));
        robot.setAlliance(Alliance.BLUE);
    }
}