package config;

import org.aeonbits.owner.ConfigFactory;

public class ProjectData {
    public static final TestDataConfig testDataConfig =
            ConfigFactory.create(TestDataConfig.class, System.getProperties());
    public static final DriverConfig driverConfig =
            ConfigFactory.create(DriverConfig.class, System.getProperties());
}
