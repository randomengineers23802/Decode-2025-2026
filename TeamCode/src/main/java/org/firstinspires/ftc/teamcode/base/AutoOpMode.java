package org.firstinspires.ftc.teamcode.base;

import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.deadline;
import static com.pedropathing.ivy.groups.Groups.sequential;

import com.pedropathing.geometry.Pose;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.CommandBuilder;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.ivy.pedro.PedroCommands;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class AutoOpMode extends RobotOpMode {
    protected ElapsedTime stuckTimer = new ElapsedTime();
    protected double currentStuckThreshold = 0;

    protected abstract Command autoRoutine();
    protected abstract void buildPaths();

    @Override
    public void init() {
        super.init();
        buildPaths();
        Scheduler.reset();
    }

    @Override
    public void start() {
        robot.intake.on();
        robot.belt.onShoot();
        robot.blueBoi.close();
        schedule(autoRoutine());
    }

    @Override
    public void loop() {
        super.loop();
        telemetry.addData("Stuck Timer", "%.2f / %.2f", stuckTimer.seconds(), currentStuckThreshold);
        telemetry.addData("T Value", "%.3f", follower.getCurrentTValue());
        telemetry.addData("Velocity", "%.3f", follower.getVelocity().getMagnitude());
    }

    protected CommandBuilder follow(PathChain path) {
        return PedroCommands.follow(follower, path);
    }

    protected Command fastFollow(PathChain path) {
        return follow(path).setDone(() -> follower.getCurrentTValue() > 0.95 && follower.getCurrentTValue() != 1.0);
    }

    protected Command timeFollow(PathChain path, double seconds) {
        return deadline(
                waitMs(seconds * 1000),
                follow(path)
        );
    }

    protected Command stuckFollow(PathChain path, double seconds) {
        return sequential(
                instant(() -> {
                    stuckTimer.reset();
                    currentStuckThreshold = seconds;
                }),
                follow(path).setDone(() -> {
                    if (follower.getVelocity().getMagnitude() > 1) {
                        stuckTimer.reset();
                    }
                    return stuckTimer.seconds() > seconds || !follower.isBusy();
                })
        ).setEnd(interrupted -> currentStuckThreshold = 0);
    }
}
