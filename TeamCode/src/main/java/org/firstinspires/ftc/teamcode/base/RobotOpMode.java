package org.firstinspires.ftc.teamcode.base;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Scheduler;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.control.Robot;
import org.firstinspires.ftc.teamcode.control.ShotParameters;
import org.firstinspires.ftc.teamcode.control.passthrough;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public abstract class RobotOpMode extends OpMode {
    protected Robot robot;
    protected Follower follower;
    protected TelemetryManager panelsTelemetry;
    protected ShotParameters shotParameters;

    @Override
    public void init() {
        follower = Constants.createFollower(hardwareMap);
        robot = new Robot(hardwareMap, follower);
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();
        panelsTelemetry.update(telemetry);
        robot.kickstand.raise();
    }

    @Override
    public void loop() {
        follower.update();
        shotParameters = robot.updateShotParameters();
        Scheduler.execute();
    }

    @Override
    public void stop() {
        passthrough.pose = follower.getPose();
    }
}
