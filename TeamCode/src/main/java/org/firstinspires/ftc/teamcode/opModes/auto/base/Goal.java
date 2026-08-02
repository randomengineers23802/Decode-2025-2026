package org.firstinspires.ftc.teamcode.opModes.auto.base;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.ivy.Command;
import com.pedropathing.paths.PathChain;
import org.firstinspires.ftc.teamcode.base.AutoOpMode;

import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.groups.Groups.sequential;

@Configurable
public abstract class Goal extends AutoOpMode {
    protected PathChain Path1, Path2, Path3, Path4, Path5, Path6, Path7, Path8, Path9, Path10;
    private String currentStep = "None";

    @Override
    public void loop() {
        super.loop();
        telemetry.addData("Current Step", currentStep);
        robot.shooter.setVelocity(shotParameters.flywheelTicks);
    }

    public Command step(String name, Command action) {
        return action.with(instant(() -> currentStep = name));
    }

    @Override
    protected Command autoRoutine() {
        return sequential(
                step("Path 1", follow(Path1)),
                step("Shoot 1", robot.shoot()),
                step("Path 2", fastFollow(Path2)),
                step("Path 3", follow(Path3)),
                step("Shoot 2", robot.shoot()),
                step("Path 4", timeFollow(Path4, 3.7)),
                step("Path 5", follow(Path5)),
                step("Shoot 3", robot.shoot()),
                step("Path 6", timeFollow(Path6, 3.7)),
                step("Path 7", follow(Path7)),
                step("Shoot 4", robot.shoot()),
                step("Path 8", timeFollow(Path8, 3.7)),
                step("Path 9", follow(Path9)),
                step("Shoot 5", robot.shoot()),
                step("Path 10", follow(Path10))
        );
    }
}
