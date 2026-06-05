package org.firstinspires.ftc.teamcode.base;

import static com.pedropathing.ivy.Scheduler.schedule;
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

    protected CommandBuilder follow(PathChain path) { //no error when its CommandBuilder
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
        ElapsedTime stuckTimer = new ElapsedTime();
        return follow(path).setDone(() -> {
            if (!follower.isRobotStuck()) {
                stuckTimer.reset();
            }
            return stuckTimer.seconds() > seconds || !follower.isBusy();
        });
    }
}
