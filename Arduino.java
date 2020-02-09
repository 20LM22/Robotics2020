package frc.robot;

//import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.Notifier;

public class Arduino {
    //separate thread for data collection and calculations
    private final static Notifier thread;
    
    //I2C communication protocol
    private final static I2C wire;

    //colors for diagnostic LED signals
    public enum Colors {
        Red, Orange, Yellow, Green, Blue, Purple, None
    }

    //I2C port to use with Arduino
    private static final int address;
    //data to be written to Arduino
    public static byte[] writeData;
    
    static {
        thread = new Notifier(() -> {
            write();
        });
        address = 0x1;
        wire = new I2C(Port.kOnboard, address);
        writeData = new byte[1];
    }

    // public static void setPattern(int pattern) {
    //     writeData[1] = (byte) pattern;
    // }

    public static void startThread() {
        thread.startPeriodic(0.02);
    }

    public static void setColorSeen(int colorSeen) {
        writeData[0] = (byte) colorSeen;
        //0 is red
        //1 is blue
        //2 is green
        //3 is yellow
        //4 is none
    }
    
    public static void write() {
        //write data to Arduino as byte array
        wire.writeBulk(writeData);
    }
}