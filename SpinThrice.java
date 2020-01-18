package frc.robot;

import edu.wpi.first.wpilibj.util.Color;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.ColorMatchResult;

public class SpinThrice {

    public static void go() {
        if(Robot.firstTime==true) {
            Robot.colorString = SpinThrice.getColorColor(); // set color string to current color
            Robot.firstTime = false;
        }
    
        if(SpinThrice.getColorColor()!= Robot.colorString) { // if current color doesn't equal the color seen before
            Robot.numberTurns++;
            Robot.colorString = SpinThrice.getColorColor();
        }
    
        if(Robot.numberTurns>=24) {
            Robot.motor.set(ControlMode.PercentOutput, 0); // these need to be changed for the spark maxs
        }else {
            Robot.motor.set(ControlMode.PercentOutput, 0.1); // these need to be changed for the spark maxs
        }
    }


    public static String getColorColor() {

        Color detectedColor = Robot.m_colorSensor.getColor(); // get the color seen by sensor
        String colorString; // variable for what color is seen
        ColorMatchResult match = Robot.m_colorMatcher.matchClosestColor(detectedColor); // match the detected color to a known color and call it "match"
        if (match.color == Robot.kBlueTarget) { // if the detected color is close to blue
            colorString = "B"; // it's blue
            return colorString;
        } else if (match.color == Robot.kRedTarget) { // if the detected color is close to red
            colorString = "R"; // it's red
            return colorString;
        } else if (match.color == Robot.kGreenTarget) { // if the detected color is close to green
            colorString = "G"; // it's green
            return colorString;
        } else if (match.color == Robot.kYellowTarget) { // if the detected color is close to yellow
            colorString = "Y"; // it's yellow
            return colorString;
        } else {
            colorString = "Unknown"; // it's just unknown
            return colorString;
        }
    }
}