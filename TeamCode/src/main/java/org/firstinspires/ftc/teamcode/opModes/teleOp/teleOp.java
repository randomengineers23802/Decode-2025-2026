package org.firstinspires.ftc.teamcode.opModes.teleOp;

import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.conditional;
import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;
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
public class teleOp extends RobotOpMode {
    private boolean shooting = false;
    private boolean endgame = false;
    private boolean prevRightTrigger = false;
    private boolean slowMode = false;
    private final double slowModeMultiplier = 0.25;
    private Target currentTarget = Target.GOAL;

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
        telemetry.update();
        Scheduler.execute();

        if (!endgame) 
            robot.shooter.setVelocity(shotParameters.flywheelTicks + Target.bonusPrismPower);

        double speedMultiplier = slowMode ? slowModeMultiplier : 1.0;
        double x = gamepad1.left_stick_x * speedMultiplier;
        double y = gamepad1.left_stick_y * speedMultiplier;

        double turn;
        if (gamepad1.left_trigger > 0.2)
            turn = shotParameters.heading;
        else if (gamepad1.left_bumper) {
            turn = robot.aimPower(robot.getGateHeading(), follower.getHeading());
            slowMode = true;
        }
        else
            turn = -gamepad1.right_stick_x * speedMultiplier;

        if (gamepad1.leftBumperWasReleased())
            slowMode = false;
        
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
            schedule(shoot(currentTarget));
        }

        prevRightTrigger = rightTriggerPressed;

        if (gamepad1.aWasPressed()) {
            currentTarget = currentTarget.toggle();
        }

        if (gamepad1.bWasPressed()) {
            shooting = false;
            schedule(robot.blueBoi.close);
            follower.startTeleopDrive();
        }

        if (gamepad1.x) {
            robot.belt.setPower(-1.0);
            robot.intake.setPower(-1.0);
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

        if (gamepad1.dpadDownWasPressed()) {
            schedule(robot.kickstand.lower, robot.light.setColorCommand(0.444));
        }
        else if (gamepad1.dpadUpWasPressed()) {
            schedule(robot.kickstand.raise, robot.light.setColorCommand(1.0));
        }

        if (gamepad1.dpadLeftWasPressed()) {
            slowMode = !slowMode;
        }
    }

    private Command shoot(Target target) {
        switch (target) {
            case PRISM:
                return sequential(
                        instant(() -> shooting = true),
                        robot.belt.onShoot,
                        robot.intake.on,
                        robot.blueBoi.open,
                        waitMs(300),
                        robot.blueBoi.close,
                        waitMs(300),
                        robot.blueBoi.open,
                        waitMs(300),
                        robot.blueBoi.close,
                        waitMs(300),
                        robot.blueBoi.open,
                        waitMs(300),
                        robot.blueBoi.close,
                        instant(() -> shooting = false)
                ).setEnd(interrupted -> robot.blueBoi.close());
            case GOAL:
            default:
                return sequential(
                        instant(() -> shooting = true),
                        conditional(() -> follower.getPose().getY() > 108, robot.belt.onFastShoot, robot.belt.onShoot),
                        robot.intake.on,
                        robot.shoot(),
                        instant(() -> shooting = false)
                ).setEnd(interrupted -> robot.blueBoi.close());
        }
    }
}
