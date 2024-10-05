package swervelib.encoders;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.DegreesPerSecond;

import com.reduxrobotics.sensors.canandmag.Canandmag;

import edu.wpi.first.units.Angle;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.Velocity;

/**
 * HELIUM {@link Canandmag} from ReduxRobotics absolute encoder, attached
 * through the CAN bus.
 */
public class CanAndMagSwerve extends SwerveAbsoluteEncoder {

  /**
   * The {@link Canandmag} representing the CANandMag on the CAN bus.
   */
  public Canandmag encoder;

  /**
   * Create the {@link Canandmag}
   *
   * @param canid The CAN ID whenever the CANandMag is operating on the CANBus.
   */
  public CanAndMagSwerve(int canid) {
    encoder = new Canandmag(canid);
  }

  /**
   * Reset the encoder to factory defaults.
   * <p>
   * This will not clear the stored zero offset.
   */
  @Override
  public void factoryDefault() {
    encoder.resetFactoryDefaults(false);
  }

  /**
   * Clear sticky faults on the encoder.
   */
  @Override
  public void clearStickyFaults() {
    encoder.clearStickyFaults();
  }

  /**
   * Configure the CANandMag to read from [0, 360) per second.
   *
   * @param inverted Whether the encoder is inverted.
   */
  @Override
  public void configure(boolean inverted) {
    encoder.setSettings(new Canandmag.Settings().setInvertDirection(inverted));
  }

  /**
   * Get the absolute position of the encoder.
   *
   * @return Absolute position in degrees from [0, 360).
   */
  @Override
  public Measure<Angle> getAbsolutePosition() {
    return Degrees.of(encoder.getAbsPosition() * 360);
  }

  /**
   * Get the instantiated absolute encoder Object.
   *
   * @return Absolute encoder object.
   */
  @Override
  public Object getAbsoluteEncoder() {
    return encoder;
  }

  /**
   * Cannot set the offset of the CANandMag.
   *
   * @param offset the offset the Absolute Encoder uses as the zero point.
   * @return true if setting the zero point succeeded, false otherwise
   */
  @Override
  public boolean setAbsoluteEncoderOffset(Measure<Angle> offset) {
    return encoder.setSettings(new Canandmag.Settings().setZeroOffset(offset.in(Degrees)));
  }

  /**
   * Get the velocity in degrees/sec.
   *
   * @return velocity in degrees/sec.
   */
  @Override
  public Measure<Velocity<Angle>> getVelocity() {
    return DegreesPerSecond.of(encoder.getVelocity() * 360);
  }
}
