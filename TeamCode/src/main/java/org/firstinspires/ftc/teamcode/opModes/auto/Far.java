package org.firstinspires.ftc.teamcode.opModes.auto;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.ivy.Command;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.base.AutoOpMode;
import static com.pedropathing.ivy.groups.Groups.sequential;

@Autonomous
@Configurable
public abstract class Far extends AutoOpMode {
    protected PathChain Path1, Path2, Path3, Path4, Path5, Path6, Path7, Path8, Path9, Path10, Path11, Path12, Path13;

    @Override
    public void loop() {
        super.loop();
        robot.shooter.setVelocity(shotParameters.flywheelTicks + 30);
    }

    @Override
    protected Command autoRoutine() {
        return sequential(
                follow(Path1),
                robot.shoot(),
                follow(Path2),
                follow(Path3),
                robot.shoot(),
                fastFollow(Path4),
                fastFollow(Path5),
                timeFollow(Path6, 1.3),
                timeFollow(Path7, 1.3),
                follow(Path8),
                robot.shoot(),
                timeFollow(Path9, 2.3),
                follow(Path10),
                robot.shoot(),
                timeFollow(Path11, 2.3),
                follow(Path12),
                robot.shoot(),
                follow(Path13)
        );
    }
}