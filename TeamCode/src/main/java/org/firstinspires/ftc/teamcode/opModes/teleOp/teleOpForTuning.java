package org.firstinspires.ftc.teamcode.opModes.teleOp;

import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.groups.Groups.sequential;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.base.RobotOpMode;
import org.firstinspires.ftc.teamcode.control.Target;
import org.firstinspires.ftc.teamcode.control.passthrough;

@Configurable
@TeleOp
public class teleOpForTuning extends RobotOpMode {
    private boolean shooting = false;
    private boolean endgame = false;
    private boolean prevRightTrigger = false;
    private boolean slowMode = false;
    private final double slowModeMultiplier = 0.25;
    private Target currentTarget = Target.GOAL;
    private int flywheelTargetTicks = 1000;

    @Override
    public void init() {
        super.init();
        if (passthrough.pose == null || passthrough.alliance == null) {
            telemetry.addLine("Run an auto first to set the pose and alliance");
        }
        else {
            follower.setStartingPose(passthrough.pose);
            robot.setAlliance(passthrough.alliance);
            telemetry.addData("Alliance", passthrough.alliance);
        }
        telemetry.update();
    }

    @Override
    public void start() {
        follower.startTeleopDrive();
        robot.intake.off();
        robot.belt.off();
        robot.light.setColor(1.0);
    }

    @Override
    public void loop() {
        follower.update();
        shotParameters = robot.updateShotParameters(robot.getTargetPose(currentTarget));
        telemetry.addData("Target", currentTarget);
        telemetry.addData("calculated flywheel velocity in/sec", robot.flywheelInchesPerSec);
        telemetry.addData("current shooterL ticks", robot.shooter.shooterL.getVelocity());
        telemetry.addData("current shooterR ticks", robot.shooter.shooterR.getVelocity());
        telemetry.addData("flywheel target ticks determined by controller dpad", flywheelTargetTicks);
        telemetry.update();
        Scheduler.execute();

        if (gamepad1.dpadUpWasPressed())
            flywheelTargetTicks += 10;
        else if (gamepad1.dpadDownWasPressed())
            flywheelTargetTicks -= 10;
        robot.shooter.setVelocity(flywheelTargetTicks);

        double speedMultiplier = slowMode ? slowModeMultiplier : 1.0;
        double x = gamepad1.left_stick_x * speedMultiplier;
        double y = gamepad1.left_stick_y * speedMultiplier;
        double turn = (gamepad1.left_trigger > 0.2) ? shotParameters.heading : -gamepad1.right_stick_x * speedMultiplier;
        follower.setTeleOpDrive(y, x, turn, false, robot.teleOpHeadingOffset);

        if (!shooting) {
            if (gamepad1.right_bumper) {
                robot.intake.on();
                robot.belt.onIntake();
            }
            else {
                robot.intake.off();
                robot.belt.off();
            }
        }

        boolean rightTriggerPressed = gamepad1.right_trigger > 0.2;
        boolean rightTriggerWasPressed = rightTriggerPressed && !prevRightTrigger;

        if (rightTriggerWasPressed && !shooting) {
            schedule(Shoot());
        }

        prevRightTrigger = rightTriggerPressed;

        if (gamepad1.aWasPressed()) {
            currentTarget = (currentTarget == Target.GOAL) ? Target.PRISM : Target.GOAL;
        }

        if (gamepad1.bWasPressed()) {
            shooting = false;
            schedule(robot.blueBoi.close);
            follower.startTeleopDrive();
        }

        if (gamepad1.xWasPressed() && follower.getVelocity().getMagnitude() < 1.5) {
            schedule(instant(robot::relocalize));
        }

        if (gamepad1.yWasPressed()) {
            if (endgame) {
                follower.startTeleopDrive();
                slowMode = false;
            }
            else {
                follower.followPath(robot.endgamePark.get());
                robot.shooter.off();
                slowMode = true;
            }
            endgame = !endgame;
        }

//        if (gamepad1.dpadDownWasPressed()) {
//            schedule(robot.kickstand.lower, robot.light.setColorCommand(0.444));
//        }
//        else if (gamepad1.dpadUpWasPressed()) {
//            schedule(robot.kickstand.raise, robot.light.setColorCommand(1.0));
//        }

//        if (gamepad1.leftBumperWasPressed()) {
//            slowMode = !slowMode;
//        }
    }

    private Command Shoot() {
        return sequential(
                instant(() -> shooting = true),
                robot.belt.onShoot,
                robot.intake.on,
                robot.shoot(),
                instant(() -> shooting = false)
        ).setEnd(interrupted -> robot.blueBoi.close());
    }
}
