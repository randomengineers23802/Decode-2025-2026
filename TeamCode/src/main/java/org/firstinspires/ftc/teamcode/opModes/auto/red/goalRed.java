 package org.firstinspires.ftc.teamcode.opModes.auto.red;

 import com.bylazar.configurables.annotations.Configurable;
 import com.pedropathing.geometry.BezierCurve;
 import com.pedropathing.geometry.BezierLine;
 import com.pedropathing.geometry.Pose;
 import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
 import org.firstinspires.ftc.teamcode.control.Alliance;
 import org.firstinspires.ftc.teamcode.opModes.auto.base.Goal;

 @Autonomous
 @Configurable
 public class goalRed extends Goal {
     @Override
     protected void buildPaths() {
         Path1 = follower.pathBuilder().addPath(
                         new BezierLine(
                                 new Pose(119.500, 128.000),
                                 new Pose(86.000, 80.000)
                         )
                 ).setLinearHeadingInterpolation(Math.toRadians(216.5), Math.toRadians(226))
                 .build();
         Path2 = follower.pathBuilder().addPath(
                         new BezierCurve(
                                 new Pose(86.000, 80.000),
                                 new Pose(89.000, 59.000),
                                 new Pose(134.000, 58.000)
                         )
                 ).setLinearHeadingInterpolation(Math.toRadians(226), Math.toRadians(340), 0.15)
                 .build();

         Path3 = follower.pathBuilder().addPath(
                         new BezierCurve(
                                 new Pose(134.000, 58.000),
                                 new Pose(105.000, 61.000),
                                 new Pose(86.000, 80.000)
                         )
                 ).setLinearHeadingInterpolation(Math.toRadians(340), Math.toRadians(226))
                 .build();

         Path4 = follower.pathBuilder().addPath(
                         new BezierCurve(
                                 new Pose(86.000, 80.000),
                                 new Pose(84.000, 69.000),
                                 new Pose(139.500, 56.500)
                         )
                 ).setLinearHeadingInterpolation(Math.toRadians(226), Math.toRadians(385), 0.15)
                 .build();

         Path5 = follower.pathBuilder().addPath(
                         new BezierCurve(
                                 new Pose(139.500, 56.500),
                                 new Pose(104.000, 62.000),
                                 new Pose(86.000, 80.000)
                         )
                 ).setLinearHeadingInterpolation(Math.toRadians(420), Math.toRadians(226))
                 .build();

         Path6 = follower.pathBuilder().addPath(
                         new BezierCurve(
                                 new Pose(86.000, 80.000),
                                 new Pose(84.000, 69.000),
                                 new Pose(139.500, 56.500)
                         )
                 ).setLinearHeadingInterpolation(Math.toRadians(226), Math.toRadians(385), 0.15)
                 .build();

         Path7 = follower.pathBuilder().addPath(
                         new BezierCurve(
                                 new Pose(139.500, 56.500),
                                 new Pose(104.000, 62.000),
                                 new Pose(86.000, 80.000)
                         )
                 ).setLinearHeadingInterpolation(Math.toRadians(385), Math.toRadians(226))
                 .build();

         Path8 = follower.pathBuilder().addPath(
                         new BezierLine(
                                 new Pose(86.000, 80.000),
                                 new Pose(126.000, 84.000)
                         )
                 ).setLinearHeadingInterpolation(Math.toRadians(226), Math.toRadians(365), 0.15)
                 .build();

         Path9 = follower.pathBuilder().addPath(
                         new BezierLine(
                                 new Pose(126.000, 84.000),
                                 new Pose(86.000, 80.000)
                         )
                 ).setLinearHeadingInterpolation(Math.toRadians(365), Math.toRadians(226))
                 .build();

         Path10 = follower.pathBuilder().addPath(
                         new BezierLine(
                                 new Pose(86.000, 80.000),
                                 new Pose(119.000, 70.000)
                         )
                 ).setLinearHeadingInterpolation(Math.toRadians(226), Math.toRadians(270))
                 .build();
     }

     @Override
     public void init() {
         super.init();
         follower.setStartingPose(new Pose(119.500, 128.000, Math.toRadians(216.5)));
         robot.setAlliance(Alliance.RED);
     }
 }