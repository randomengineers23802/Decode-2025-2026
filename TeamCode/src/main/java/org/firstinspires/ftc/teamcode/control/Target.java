package org.firstinspires.ftc.teamcode.control;

public enum Target {
    GOAL,
    PRISM;
public static int bonusPrismPower;
    public Target toggle() {
        Target next = (this == GOAL) ? PRISM : GOAL;
        bonusPrismPower = (next == PRISM) ? 30 : 0;
        return next;
    }
}
