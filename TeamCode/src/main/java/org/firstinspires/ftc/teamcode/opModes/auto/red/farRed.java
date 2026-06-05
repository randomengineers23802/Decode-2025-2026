 package org.firstinspires.ftc.teamcode.opModes.auto.red;

 import com.bylazar.configurables.annotations.Configurable;
 import com.pedropathing.geometry.BezierCurve;
 import com.pedropathing.geometry.BezierLine;
 import com.pedropathing.geometry.Pose;
 import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
 import org.firstinspires.ftc.teamcode.control.Alliance;
 import org.firstinspires.ftc.teamcode.opModes.auto.Far;

 @Autonomous
 @Configurable
 public class farRed extends Far {
     @Override
     protected void buildPaths() {
         Path1 = follower.pathBuilder().addPath(
                         new BezierLine(
                                 new Pose(87.125, 8.563),
                                 new Pose(86.000, 15.000)
                         )
                 ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(253))
                 .build();

         Path2 = follower.pathBuilder().addPath(
                         new BezierCurve(
                                 new Pose(86.000, 15.000),
                                 new Pose(78.000, 40.000),
                                 new Pose(134.000, 37.000)
                         )
                 ).setLinearHeadingInterpolation(Math.toRadians(253), Math.toRadians(0), 0.2)
                 .build();

         Path3 = follower.pathBuilder().addPath(
                         new BezierLine(
                                 new Pose(134.000, 37.000),
                                 new Pose(86.000, 15.000)
                         )
                 ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(250))
                 .build();

         Path4 = follower.pathBuilder().addPath(
                         new BezierLine(
                                 new Pose(86.000, 15.000),
                                 new Pose(134.000, 17.000)
                         )
                 ).setLinearHeadingInterpolation(Math.toRadians(250), Math.toRadians(330), 0.4)
                 .build();

         Path5 = follower.pathBuilder().addPath(
                         new BezierLine(
                                 new Pose(134.000, 17.000),
                                 new Pose(123.000, 17.000)
                         )
                 ).setConstantHeadingInterpolation(Math.toRadians(0))
                 .build();

         Path6 = follower.pathBuilder().addPath(
                         new BezierLine(
                                 new Pose(123.000, 17.000),
                                 new Pose(129.000, 15.000)
                         )
                 ).setConstantHeadingInterpolation(Math.toRadians(330))
                 .build();

         Path7 = follower.pathBuilder().addPath(
                         new BezierLine(
                                 new Pose(129.000, 15.000),
                                 new Pose(136.000, 8.500)
                         )
                 ).setConstantHeadingInterpolation(Math.toRadians(330))
                 .build();

         Path8 = follower.pathBuilder().addPath(
                         new BezierLine(
                                 new Pose(136.000, 8.500),
                                 new Pose(86.000, 15.000)
                         )
                 ).setLinearHeadingInterpolation(Math.toRadians(330), Math.toRadians(250))
                 .build();

         Path9 = follower.pathBuilder().addPath(
                         new BezierLine(
                                 new Pose(86.000, 15.000),
                                 new Pose(135.000, 12.000)
                         )
                 ).setLinearHeadingInterpolation(Math.toRadians(250), Math.toRadians(0), 0.4)
                 .build();

         Path10 = follower.pathBuilder().addPath(
                         new BezierLine(
                                 new Pose(135.000, 12.000),
                                 new Pose(86.000, 15.000)
                         )
                 ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(250))
                 .build();

         Path11 = follower.pathBuilder().addPath(
                         new BezierLine(
                                 new Pose(86.000, 15.000),
                                 new Pose(135.000, 12.000)
                         )
                 ).setLinearHeadingInterpolation(Math.toRadians(250), Math.toRadians(0), 0.4)
                 .build();

         Path12 = follower.pathBuilder().addPath(
                         new BezierLine(
                                 new Pose(135.000, 12.000),
                                 new Pose(86.000, 15.000)
                         )
                 ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(250))
                 .build();

         Path13 = follower.pathBuilder().addPath(
                         new BezierLine(
                                 new Pose(86.000, 15.000),
                                 new Pose(108.000, 15.000)
                         )
                 ).setLinearHeadingInterpolation(Math.toRadians(250), Math.toRadians(0))
                 .build();
     }

     @Override
     public void init() {
         super.init();
         follower.setStartingPose(new Pose(87.125, 8.5625, Math.toRadians(180)));
         robot.setAlliance(Alliance.RED);
     }
 }