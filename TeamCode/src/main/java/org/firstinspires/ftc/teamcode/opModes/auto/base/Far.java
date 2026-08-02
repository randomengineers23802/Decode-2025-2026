package org.firstinspires.ftc.teamcode.opModes.auto.base;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.ivy.Command;
import com.pedropathing.paths.PathChain;
import org.firstinspires.ftc.teamcode.base.AutoOpMode;
import static com.pedropathing.ivy.groups.Groups.sequential;

@Configurable
public abstract class Far extends AutoOpMode {
    protected PathChain Path1, Path2, Path3, Path4, Path5, Path6, Path7, Path8, Path9, Path10;

    @Override
    public void loop() {
        super.loop();
        robot.shooter.setVelocity(shotParameters.flywheelTicks - 10);
    }

    @Override
    protected Command autoRoutine() {
        return sequential(
                step("Path 1", follow(Path1)),
                step("Shoot 1", robot.shoot()),
                step("Path 2", fastFollow(Path2)),
                step("Path 3", follow(Path3)),
                step("Shoot 2", robot.shoot()),
                step("Path 4", fastFollow(Path4)),
                step("Path 5", follow(Path5)),
                step("Shoot 3", robot.shoot()),
                step("Path 6", fastFollow(Path6)),
                step("Path 7", follow(Path7)),
                step("Shoot 4", robot.shoot()),
                step("Path 8", fastFollow(Path8)),
                step("Path 9", follow(Path9)),
                step("Shoot 5", robot.shoot()),
                step("Path 10", follow(Path10))
        );
    }
}
