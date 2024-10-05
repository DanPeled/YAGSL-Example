package swervelib.parser;

import edu.wpi.first.units.Measure;
import edu.wpi.first.units.Time;
import edu.wpi.first.wpilibj.RobotController;

import static edu.wpi.first.units.Units.Microseconds;

import java.util.function.Supplier;

/**
 * Cache for frequently requested data.
 */
public class Cache<T> {

  /**
   * Cached value.
   */
  private T value;
  /**
   * Supplier for cached value.
   */
  private Supplier<T> supplier;
  /**
   * Timestamp in microseconds.
   */
  private Measure<Time> timestamp;
  /**
   * Validity period in microseconds.
   */
  private Measure<Time> validityPeriod;

  /**
   * Cache for arbitrary values.
   *
   * @param val            Value to cache.
   * @param validityPeriod Validity period in milliseconds.
   */
  public Cache(Supplier<T> val, Measure<Time> validityPeriod) {
    supplier = val;
    value = supplier.get();
    timestamp = Microseconds.of(RobotController.getFPGATime());
    this.validityPeriod = validityPeriod;
  }

  /**
   * Return whether the cache is stale.
   *
   * @return The stale state of the cache.
   */
  public boolean isStale() {
    return (Microseconds.of(RobotController.getFPGATime()).minus(timestamp)).gt(validityPeriod);
  }

  /**
   * Update the cache value and timestamp.
   *
   * @return {@link Cache} used.
   */
  public Cache<T> update() {
    this.value = supplier.get();
    this.timestamp = Microseconds.of(RobotController.getFPGATime());
    return this;
  }

  /**
   * Update the supplier to a new source. Updates the value and timestamp as well.
   *
   * @param supplier new supplier source.
   * @return {@link Cache} for chaining.
   */
  public Cache<T> updateSupplier(Supplier<T> supplier) {
    this.supplier = supplier;
    update();
    return this;
  }

  /**
   * Update the validity period for the cached value, also updates the value.
   *
   * @param validityPeriod The new validity period in milliseconds.
   * @return {@link Cache} for chaining.
   */
  public Cache<T> updateValidityPeriod(Measure<Time> validityPeriod) {
    this.validityPeriod = validityPeriod;
    update();
    return this;
  }

  /**
   * Get the most up to date cached value.
   *
   * @return {@link T} updated to the latest cached version.
   */
  public T getValue() {
    if (isStale()) {
      update();
    }
    return value;
  }

}
