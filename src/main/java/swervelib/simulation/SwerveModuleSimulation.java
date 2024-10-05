package swervelib.simulation;

import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.Seconds;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.units.Distance;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.Time;
import edu.wpi.first.units.Velocity;
import edu.wpi.first.wpilibj.Timer;

/**
 * Class to hold simulation data for {@link swervelib.SwerveModule}
 */
public class SwerveModuleSimulation {

  /**
   * Main timer to simulate the passage of time.
   */
  private final Timer timer;
  /**
   * Time delta since last update
   */
  private Measure<Time> dt;
  /**
   * Fake motor position.
   */
  private Measure<Distance> fakePos;
  /**
   * The fake speed of the previous state, used to calculate
   * {@link SwerveModuleSimulation#fakePos}.
   */
  private Measure<Velocity<Distance>> fakeSpeed;
  /**
   * Last time queried.
   */
  private Measure<Time> lastTime;
  /**
   * Current simulated swerve module state.
   */
  private SwerveModuleState state;

  /**
   * Create simulation class and initialize module at 0.
   */
  public SwerveModuleSimulation() {
    timer = new Timer();
    timer.start();
    lastTime = Seconds.of(timer.get());
    state = new SwerveModuleState(0, Rotation2d.fromDegrees(0));
    fakeSpeed = MetersPerSecond.of(0);
    fakePos = Meters.of(0);
    dt = Seconds.of(0);
  }

  /**
   * Update the position and state of the module. Called from
   * {@link swervelib.SwerveModule#setDesiredState} function
   * when simulated.
   *
   * @param desiredState State the swerve module is set to.
   */
  public void updateStateAndPosition(SwerveModuleState desiredState) {
    dt = Seconds.of(timer.get()).minus(lastTime);
    lastTime = Seconds.of(timer.get());

    state = desiredState;
    fakeSpeed = MetersPerSecond.of(desiredState.speedMetersPerSecond);
    fakePos = fakePos.plus(Meters.of(fakeSpeed.in(MetersPerSecond) * dt.in(Seconds)));
  }

  /**
   * Get the simulated swerve module position.
   *
   * @return {@link SwerveModulePosition} of the simulated module.
   */
  public SwerveModulePosition getPosition() {

    return new SwerveModulePosition(fakePos, state.angle);
  }

  /**
   * Get the {@link SwerveModuleState} of the simulated module.
   *
   * @return {@link SwerveModuleState} of the simulated module.
   */
  public SwerveModuleState getState() {
    return state;
  }
}
