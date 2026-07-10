package org.firstinspires.ftc.teamcode.opModes.auto.base;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.ivy.Command;
import com.pedropathing.paths.PathChain;
import org.firstinspires.ftc.teamcode.base.AutoOpMode;
import static com.pedropathing.ivy.groups.Groups.sequential;
@Configurable
public abstract class Goal extends AutoOpMode {
    protected PathChain Path1, Path2, Path3, Path4, Path5, Path6, Path7, Path8, Path9, Path10;

    @Override
    public void loop() {
        super.loop();
        robot.shooter.setVelocity(shotParameters.flywheelTicks + 10);
    }

    @Override
    protected Command autoRoutine() {
        return sequential(
                follow(Path1),
                robot.shoot(),
                fastFollow(Path2),
                follow(Path3),
                robot.shoot(),
                timeFollow(Path4, 3.7),
                follow(Path5),
                robot.shoot(),
                timeFollow(Path6, 3.7),
                follow(Path7),
                robot.shoot(),
                timeFollow(Path8, 3.7),
                follow(Path9),
                robot.shoot(),
                follow(Path10)
        );
    }
}