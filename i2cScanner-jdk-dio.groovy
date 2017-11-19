// this code not work on my raspberry pi 2, work only in eclipse kura bundle
// work only with builded dio library:
// https://dzone.com/refcardz/iot-applications-with-java-and-raspberry-pi
// groovy -cp "/home/pi/git/dio/build/jar" i2cScanner-jdk-dio.groovy
//@GrabResolver(name='Eclipe Kura', root='https://repo.eclipse.org/content/repositories/kura-releases/')
//@Grapes([
//  @Grab(group='org.eclipse.kura', module='jdk.dio', version='1.0.200')
//])
import java.nio.ByteBuffer;
import java.util.logging.LogManager
import jdk.dio.DeviceManager;
import jdk.dio.i2cbus.I2CDevice;
import jdk.dio.i2cbus.I2CDeviceConfig;

LogManager.getLogManager().reset()
println 'Start Scanning i2c devices on bus 1'

int devicesCount
for (byte address = 0; address < 127; address++) {
  try {
    ByteBuffer chipIdBuffer = ByteBuffer.allocate(7)
    I2CDeviceConfig config = new I2CDeviceConfig(1, address, 7, -1)
    I2CDevice device = DeviceManager.open(I2CDevice.class, config)
    if (device != null) {
      device.read(0xD0, 1, chipIdBuffer)
      chipIdBuffer.flip()
      println "Device found on 0x${Integer.toHexString(address)} Response: 0x${Integer.toHexString(chipIdBuffer.get())}"
      devicesCount++
    }
  } catch(Exception e) {
  }
}
println "Found $devicesCount device's on i2c Bus 1\nScanning Done"
