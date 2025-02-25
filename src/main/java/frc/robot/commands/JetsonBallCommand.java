package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.JetsonSubsystem;
import frc.robot.subsystems.TransferSubsystem;

public class JetsonBallCommand extends CommandBase {

  private final JetsonSubsystem jetson;
  private final DriveTrainSubsystem driveTrainSubsystem;
  private final IntakeSubsystem intakeSubsystem;
  private final TransferSubsystem transferSubsystem;
  private final IndexerSubsystem indexerSubsystem;

  private final PIDController xPidController = new PIDController(.0007, 0, 0);
  private final PIDController yPidController = new PIDController(.0007, 0, 0);

  public JetsonBallCommand(
      DriveTrainSubsystem driveTrainSubsystem,
      JetsonSubsystem jetsonSubsystem,
      IntakeSubsystem intakeSubsystem,
      TransferSubsystem transferSubsystem,
        IndexerSubsystem indexerSubsystem) {
    this.driveTrainSubsystem = driveTrainSubsystem;
    this.jetson = jetsonSubsystem;
    this.intakeSubsystem = intakeSubsystem;
    this.transferSubsystem = transferSubsystem;
    this.indexerSubsystem = indexerSubsystem;

    addRequirements(driveTrainSubsystem, jetsonSubsystem, intakeSubsystem, indexerSubsystem, transferSubsystem);
    
    xPidController.setSetpoint(0);
    xPidController.setTolerance(3);

    yPidController.setSetpoint(0);
    yPidController.setTolerance(1);
  }

  @Override
  public void initialize() {
    jetson.enable();
    xPidController.reset();
    yPidController.reset();
  }

  @Override
  public void execute() {
    var detection = jetson.getClosestDetection();
    if (detection == null) {
      driveTrainSubsystem.stop();
    } else {
      var speed = -yPidController.calculate(detection.targetY);
      var rotation = -xPidController.calculate(detection.targetX);
      driveTrainSubsystem.arcadeDrive(speed, rotation, false);
      intakeSubsystem.intake();
      transferSubsystem.intake();
      indexerSubsystem.load();
    }
  }

  @Override
  public boolean isFinished() {
    return (xPidController.atSetpoint() && yPidController.atSetpoint());
  }

  @Override
  public void end(boolean interrupted) {
    super.end(interrupted);
    driveTrainSubsystem.stop();
    intakeSubsystem.stop();
    transferSubsystem.stop();
    indexerSubsystem.stop();
  }

}