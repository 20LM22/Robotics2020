package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.ColorMatch;

public class Robot extends TimedRobot {

  static TalonSRX motor = new TalonSRX(2);

  public final static I2C.Port i2cPort = I2C.Port.kOnboard;

  public final static ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

  public final static ColorMatch m_colorMatcher = new ColorMatch();

  public final static Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  public final static Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  public final static Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  public final static Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  public static boolean firstTime = true;
  public static int numberTurns = 0;

  public static String colorString = "\0"; // variable for what color is seen

  @Override
  public void robotInit() {
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);

  }

  @Override
  public void robotPeriodic() {

  }

  @Override
  public void teleopInit() {
    firstTime = true;
    colorString = "\0";
    numberTurns = 0;
  }

  @Override
  public void teleopPeriodic() {
    //motor.set(ControlMode.PercentOutput,.1);
    SpinThrice.go();
    //SpecificColor.go();
  }
}
