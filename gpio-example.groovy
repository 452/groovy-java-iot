@GrabResolver(name='Sonatype OSS Maven Repository', root='https://oss.sonatype.org/content/groups/public')
@Grapes([
  @Grab(group='com.pi4j', module='pi4j-core', version='1.2-SNAPSHOT'),
  @Grab(group='com.pi4j', module='pi4j-device', version='1.2-SNAPSHOT'),
])
import com.pi4j.platform.Platform
import com.pi4j.platform.PlatformManager
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.platform.PlatformAlreadyAssignedException;
import com.pi4j.util.CommandArgumentParser;
import com.pi4j.util.Console;
import com.pi4j.util.ConsoleColor;

PlatformManager.setPlatform(Platform.RASPBERRYPI)
println 'Start gpio example'
GpioController gpio = GpioFactory.getInstance()
GpioPinDigitalOutput led = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00)

while(1) {
  led.high()
  sleep(60000)
  led.low()
  sleep(1000)
}
