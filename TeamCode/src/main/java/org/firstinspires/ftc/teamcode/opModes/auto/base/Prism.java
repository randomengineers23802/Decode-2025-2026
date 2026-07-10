package org.firstinspires.ftc.teamcode.opModes.auto.base;

import static com.pedropathing.ivy.groups.Groups.sequential;
import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.ivy.Command;
import com.pedropathing.paths.PathChain;
import org.firstinspires.ftc.teamcode.base.AutoOpMode;

@Configurable
public abstract class Prism extends AutoOpMode {
    protected PathChain Path1, Path2;

    @Override
    public void loop() {
        super.loop();
        robot.shooter.setVelocity(shotParameters.flywheelTicks + 10);
    }

    @Override
    protected Command autoRoutine() {
        return sequential(
                follow(Path1),
                robot.shoot()//,
                //follow(Path2)
        );
    }
}