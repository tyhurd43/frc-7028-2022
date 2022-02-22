package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * Command to spin up the shooter and run the indexer to shoot a ball when the shooter is up to speed.
 */
public class JustShootCommand extends CommandBase {
  
  private final ShooterSubsystem shooterSubsystem;
  private final IndexerSubsystem indexerSubsystem;
  private final DoubleSupplier speedSupplier;
  
  private Timer endTimer = new Timer();

  public JustShootCommand(ShooterSubsystem shooterSubsystem, IndexerSubsystem indexerSubsystem, DoubleSupplier speedSupplier) {
    this.shooterSubsystem = shooterSubsystem;
    this.indexerSubsystem = indexerSubsystem;
    this.speedSupplier = speedSupplier;

    addRequirements(shooterSubsystem, indexerSubsystem);
  }

  @Override
  public void initialize() {
    endTimer.reset();
  }

  @Override
  public void execute() {
    shooterSubsystem.runShooter(speedSupplier.getAsDouble());
    if (shooterSubsystem.isReadyToShoot()) {
      indexerSubsystem.shoot();
      endTimer.start();
    }  
  }

  @Override
  public boolean isFinished() {
    return endTimer.hasElapsed(0.25);
  }

  @Override
  public void end(boolean interrupted) {
    shooterSubsystem.stop();
  }

}
