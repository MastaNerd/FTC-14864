package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

// Non-RR imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Config
@Autonomous(name = "redAutoVibStart", group = "Autonomous")
public class redAuto extends LinearOpMode {
    @Override
    public void runOpMode() {
        // Initialize the starting pose at the origin facing forward
        Pose2d initialPose = new Pose2d(0, -65, 270);
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        // Build the trajectory to move 100 inches along the x-axis
        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
                .strafeTo(new Vector2d(0, -34))
                .waitSeconds(3)
                .setTangent(0)
                .splineTo(new Vector2d(48, -8), Math.PI / 1.5)
                .waitSeconds(3)
                .strafeTo(new Vector2d(48, -65));
                //.setTangent(0)
                //.splineTo(new Vector2d(54, -24), Math.PI / 2);
        //Action traj = tab1.endTrajectory().strafeTo(new Vector2d(36, 49)).build();

        // Wait for the start signal
        waitForStart();

        if (isStopRequested()) return;

        Action trajectoryActionChosen = tab1.build();
        // Execute the trajectory
        //Actions.runBlocking(new SequentialAction(traj, trajectoryActionChosen));
        Actions.runBlocking(new SequentialAction(trajectoryActionChosen));

        // Indicate completion
        telemetry.addData("Status", "Movement complete");
        telemetry.update();
    }
}