package org.firstinspires.ftc.teamcode.base;

import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.deadline;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.CommandBuilder;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.ivy.pedro.PedroCommands;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class AutoOpMode extends RobotOpMode {
    protected abstract Command autoRoutine();
    protected abstract void buildPaths();
    public String currentStep;

    @Override
    public void init() {
        super.init();
        buildPaths();
        Scheduler.reset();
        telemetry.addLine("Ready");
        telemetry.addData("Step", "Not started");
        telemetry.update();
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
        telemetry.addData("T Value", follower.getCurrentTValue());
        telemetry.addData("Velocity", follower.getVelocity().getMagnitude());
        telemetry.addData("Step", currentStep);
        telemetry.update();
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

    protected Command step(String name, Command action) {
        return action.with(instant(() -> currentStep = name));
    }
}
