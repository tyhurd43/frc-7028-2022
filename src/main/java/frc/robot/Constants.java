// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.Map;

import edu.wpi.first.math.util.Units;
import frc.robot.subsystems.LimelightConfig;
import frc.robot.util.ShootingInterpolator;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final class DrivetrainConstants {
    public static final int DEVICE_ID_LEFT_LEADER = 0;
    public static final int DEVICE_ID_LEFT_FOLLOWER = 1;
    public static final int DEVICE_ID_RIGHT_LEADER = 2;
    public static final int DEVICE_ID_RIGHT_FOLLOWER = 3;
  }

  public static final class ShooterConstants {
    public static final int DEVICE_ID_SHOOTER_LEADER = 10;
    public static final int DEVICE_ID_SHOOTER_FOLLOWER = 11;

    public static final double kS = 0.57831;
    public static final double kV = 0.09430;
    public static final double kA = 0;

    public static final double kP = 0.13;

    public static final int COUNTS_PER_REVOLUTION = 2048;

    public static final double RAMP_RATE = 0.4;
    public static final int CLOSED_LOOP_ERROR_RANGE = 50;

    public static final ShootingInterpolator SHOOTING_INTERPOLATOR = new ShootingInterpolator(Map.ofEntries(
      Map.entry(1d, 2d)
    ));
  }

  public static final class IndexerConstants {
    public static final int DEVICE_ID_INDEXER = 6;
  }

  public static final class IntakeConstants {
    public static final int DEVICE_ID_INTAKE = 7;
  }

  public static final class TransferConstants {
    public static final int DEVICE_ID_TRANSFER_LEADER = 8;
    public static final int DEVICE_ID_TRANSFER_FOLLOWER = 5;
  }

  public static final class LimeLightConstants {

    public static final double PIPELINE_INDEX_NEAR = 0.0;
    public static final double PIPELINE_INDEX_FAR = 1.0;

    /** Height of the target in meters */
    public static final double TARGET_HEIGHT = Units.inchesToMeters(104);

    public static final LimelightConfig LIMELIGHT_CONFIG = LimelightConfig.Builder.create()
        .withMountDepth(Units.inchesToMeters(18))
        .withMountDistanceFromCenter(Units.inchesToMeters(0))
        .withMountingAngle(40)
        .withMountingHeight(Units.inchesToMeters(23))
        .withNetworkTableName("limelight")
        .build();
    
  }
 
}
