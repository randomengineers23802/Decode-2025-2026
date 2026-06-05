package org.firstinspires.ftc.teamcode.opModes.auto;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.ivy.Command;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.base.AutoOpMode;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.commands.Commands.waitMs;

@Autonomous
@Configurable
public abstract class Close extends AutoOpMode {
    protected PathChain Path1, Path2, Path3, Path4, Path5, Path6, Path7, Path8, Path9, Path10;

    @Override
    public void loop() {
        super.loop();
        robot.shooter.setVelocity(shotParameters.flywheelTicks + 10);
    }

    private Command shoot() {
        return sequential(
                robot.blueBoi.open,
                waitMs(1000),
                robot.blueBoi.close
        );
    }

//    @Override
//    protected Command autoRoutine() {
//        return sequential(
//                follow(Path1),
//                shoot(),
//                fastFollow(Path2),
//                follow(Path3),
//                shoot(),
//                timeFollow(Path4, 4),
//                follow(Path5),
//                shoot(),
//                timeFollow(Path6, 4),
//                follow(Path7),
//                shoot(),
//                fastFollow(Path8),
//                follow(Path9),
//                shoot(),
//                follow(Path10)
//        );
//    }

    @Override
    protected Command autoRoutine() {
        return sequential(
                follow(Path1),
                shoot(),
                fastFollow(Path2),
                follow(Path3),
                shoot(),
                stuckFollow(Path4, 0.3),
                waitMs(1000),
                follow(Path5),
                shoot(),
                stuckFollow(Path6, 0.3),
                waitMs(1000),
                follow(Path7),
                shoot(),
                fastFollow(Path8),
                follow(Path9),
                shoot(),
                follow(Path10)
        );
    }
}