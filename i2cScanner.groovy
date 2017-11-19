@Grapes([
  @Grab(group='com.pi4j', module='pi4j-core', version='1.1'),
  @Grab(group='com.pi4j', module='pi4j-device', version='1.1'),
])
import com.pi4j.io.i2c.I2CBus
import com.pi4j.io.i2c.I2CDevice
import com.pi4j.io.i2c.I2CFactory
import com.pi4j.platform.Platform
import com.pi4j.platform.PlatformManager

PlatformManager.setPlatform(Platform.RASPBERRYPI)
println 'Start Scanning i2c devices on bus 1'
int devicesCount
for (byte address = 0; address < 127; address++) {
  try {
    I2CBus i2c = I2CFactory.getInstance(I2CBus.BUS_1)
    I2CDevice device = i2c.getDevice(address)
    int response = device.read(0xD0)
    println "Device found on 0x${Integer.toHexString(address)} Response: 0x${Integer.toHexString(response)}"
    devicesCount++
  } catch(Exception e) {
  }
}
println "Found $devicesCount device's on i2c Bus 1\nScanning Done"
