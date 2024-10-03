package swervelib.parser;

import static edu.wpi.first.units.Units.Meters;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.units.Angle;
import edu.wpi.first.units.Distance;
import edu.wpi.first.units.Measure;
import swervelib.encoders.SwerveAbsoluteEncoder;
import swervelib.motors.SwerveMotor;
import swervelib.parser.json.MotorConfigDouble;

/**
 * Swerve Module configuration class which is used to configure
 * {@link swervelib.SwerveModule}.
 */
public class SwerveModuleConfiguration {

  /**
   * Conversion factor for drive motor onboard PID's and angle PID's. Use
   * {@link swervelib.math.SwerveMath#calculateMetersPerRotation(double, double, double)}
   * and
   * {@link swervelib.math.SwerveMath#calculateDegreesPerSteeringRotation(double, double)}
   * respectively to calculate the
   * conversion factors.
   */
  public final MotorConfigDouble conversionFactors;
  /**
   * Angle offset in degrees for the Swerve Module.
   */
  public final Measure<Angle> angleOffset;
  /**
   * Whether the absolute encoder is inverted.
   */
  public final boolean absoluteEncoderInverted;
  /**
   * State of inversion of the drive motor.
   */
  public final boolean driveMotorInverted;
  /**
   * State of inversion of the angle motor.
   */
  public final boolean angleMotorInverted;
  /**
   * PIDF configuration options for the angle motor closed-loop PID controller.
   */
  public PIDFConfig anglePIDF;
  /**
   * PIDF configuration options for the drive motor closed-loop PID controller.
   */
  public PIDFConfig velocityPIDF;
  /**
   * Swerve module location relative to the robot.
   */
  public Translation2d moduleLocation;
  /**
   * Physical characteristics of the swerve module.
   */
  public SwerveModulePhysicalCharacteristics physicalCharacteristics;
  /**
   * The drive motor and angle motor of this swerve module.
   */
  public SwerveMotor driveMotor, angleMotor;
  /**
   * The Absolute Encoder for the swerve module.
   */
  public SwerveAbsoluteEncoder absoluteEncoder;
  /**
   * Name for the swerve module for telemetry.
   */
  public String name;
  /**
   * Should do cosine compensation when not pointing correct direction;.
   */
  public boolean useCosineCompensator;

  /**
   * Construct a configuration object for swerve modules.
   *
   * @param driveMotor              Drive {@link SwerveMotor}.
   * @param angleMotor              Angle {@link SwerveMotor}
   * @param absoluteEncoder         Absolute encoder
   *                                {@link SwerveAbsoluteEncoder}.
   * @param angleOffset             Absolute angle offset to 0.
   * @param absoluteEncoderInverted Absolute encoder inverted.
   * @param angleMotorInverted      State of inversion of the angle motor.
   * @param driveMotorInverted      Drive motor inverted.
   * @param xMeters                 Module location in meters from the center
   *                                horizontally.
   * @param yMeters                 Module location in meters from center
   *                                vertically.
   * @param anglePIDF               Angle PIDF configuration.
   * @param velocityPIDF            Velocity PIDF configuration.
   * @param physicalCharacteristics Physical characteristics of the swerve module.
   * @param name                    The name for the swerve module.
   * @param conversionFactors       Conversion factors to be applied to the drive
   *                                and angle motors.
   * @param useCosineCompensator    Should use cosineCompensation.
   */
  public SwerveModuleConfiguration(
      SwerveMotor driveMotor,
      SwerveMotor angleMotor,
      MotorConfigDouble conversionFactors,
      SwerveAbsoluteEncoder absoluteEncoder,
      Measure<Angle> angleOffset,
      Measure<Distance> xMeters,
      Measure<Distance> yMeters,
      PIDFConfig anglePIDF,
      PIDFConfig velocityPIDF,
      SwerveModulePhysicalCharacteristics physicalCharacteristics,
      boolean absoluteEncoderInverted,
      boolean driveMotorInverted,
      boolean angleMotorInverted,
      String name,
      boolean useCosineCompensator) {
    this.driveMotor = driveMotor;
    this.angleMotor = angleMotor;
    this.conversionFactors = conversionFactors;
    this.absoluteEncoder = absoluteEncoder;
    this.angleOffset = angleOffset;
    this.absoluteEncoderInverted = absoluteEncoderInverted;
    this.driveMotorInverted = driveMotorInverted;
    this.angleMotorInverted = angleMotorInverted;
    this.moduleLocation = new Translation2d(xMeters.in(Meters), yMeters.in(Meters));
    this.anglePIDF = anglePIDF;
    this.velocityPIDF = velocityPIDF;
    this.physicalCharacteristics = physicalCharacteristics;
    this.name = name;
    this.useCosineCompensator = useCosineCompensator;
  }

  /**
   * Construct a configuration object for swerve modules. Assumes the absolute
   * encoder and drive motor are not
   * inverted.
   *
   * @param driveMotor              Drive {@link SwerveMotor}.
   * @param angleMotor              Angle {@link SwerveMotor}
   * @param conversionFactors       Conversion factors for angle/azimuth motors
   *                                drive factors.
   * @param absoluteEncoder         Absolute encoder
   *                                {@link SwerveAbsoluteEncoder}.
   * @param angleOffset             Absolute angle offset to 0.
   * @param xMeters                 Module location in meters from the center
   *                                horizontally.
   * @param yMeters                 Module location in meters from center
   *                                vertically.
   * @param anglePIDF               Angle PIDF configuration.
   * @param velocityPIDF            Velocity PIDF configuration.
   * @param physicalCharacteristics Physical characteristics of the swerve module.
   * @param name                    Name for the module.
   * @param useCosineCompensator    Should use cosineCompensation.
   */
  public SwerveModuleConfiguration(
      SwerveMotor driveMotor,
      SwerveMotor angleMotor,
      MotorConfigDouble conversionFactors,
      SwerveAbsoluteEncoder absoluteEncoder,
      Measure<Angle> angleOffset,
      Measure<Distance> xMeters,
      Measure<Distance> yMeters,
      PIDFConfig anglePIDF,
      PIDFConfig velocityPIDF,
      SwerveModulePhysicalCharacteristics physicalCharacteristics,
      String name,
      boolean useCosineCompensator) {
    this(
        driveMotor,
        angleMotor,
        conversionFactors,
        absoluteEncoder,
        angleOffset,
        xMeters,
        yMeters,
        anglePIDF,
        velocityPIDF,
        physicalCharacteristics,
        false,
        false,
        false,
        name,
        useCosineCompensator);
  }

}
