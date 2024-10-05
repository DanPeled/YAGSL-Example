package swervelib.parser.json;

import edu.wpi.first.units.Distance;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.Velocity;
import swervelib.parser.PIDFConfig;
import swervelib.parser.SwerveControllerConfiguration;
import swervelib.parser.SwerveDriveConfiguration;

/**
 * {@link swervelib.SwerveController} parsed class. Used to access the JSON
 * data.
 */
public class ControllerPropertiesJson {

  /**
   * The minimum radius of the angle control joystick to allow for heading
   * adjustment of the robot.
   */
  public double angleJoystickRadiusDeadband;
  /**
   * The PID used to control the robot heading.
   */
  public PIDFConfig heading;

  /**
   * Create the {@link SwerveControllerConfiguration} based on parsed and given
   * data.
   *
   * @param driveConfiguration {@link SwerveDriveConfiguration} parsed
   *                           configuration.
   * @param maxSpeed        Maximum speed in meters per second for the angular
   *                           acceleration of the robot.
   * @return {@link SwerveControllerConfiguration} object based on parsed data.
   */
  public SwerveControllerConfiguration createControllerConfiguration(
      SwerveDriveConfiguration driveConfiguration, Measure<Velocity<Distance>> maxSpeed) {
    return new SwerveControllerConfiguration(
        driveConfiguration, heading, angleJoystickRadiusDeadband, maxSpeed);
  }
}
