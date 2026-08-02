package org.firstinspires.ftc.teamcode.opModes.auto.base;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.ivy.Command;
import com.pedropathing.paths.PathChain;
import org.firstinspires.ftc.teamcode.base.AutoOpMode;

import static com.pedropathing.ivy.groups.Groups.sequential;

@Configurable
public abstract class Prism extends AutoOpMode {
    protected PathChain Path1, Path2;

    @Override
    public void loop() {
        super.loop();
        robot.shooter.setVelocity(shotParameters.flywheelTicks);
    }

    @Override
    protected Command autoRoutine() {
        return sequential(
                step("Path 1", follow(Path1)),
                step("Shoot", robot.shoot()),
                step("Path 2", follow(Path2))
        );
    }
}
