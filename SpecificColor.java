package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.ColorMatchResult;

public class SpecificColor {

    public static void go() {

        String data = DriverStation.getInstance().getGameSpecificMessage(); // get the color from the driver station
        char gameData = '\0'; // create another variable to store that data, set to null
        if (data.length() > 0) { // if there's a color
            gameData = data.charAt(0); // take in the color as gameData
            if (gameData == 'R') {
                gameData = 'B';
            } else if (gameData == 'Y') {
                gameData = 'G';
            } else if (gameData == 'G') {
                gameData = 'Y';
            } else if (gameData == 'B') {
                gameData = 'R';
                //System.out.println("game data: " + gameData);
            }
        }

        Color detectedColor = Robot.m_colorSensor.getColor(); // get the color seen by sensor
        String colorString; // variable for what color is seen
        ColorMatchResult match = Robot.m_colorMatcher.matchClosestColor(detectedColor);
        // match the detected color to a known color and call it "match"

        if (match.color == Robot.kBlueTarget) { // if the detected color is close to blue
            colorString = "B"; // it's blue
        } else if (match.color == Robot.kRedTarget) { // if the detected color is close to red
            colorString = "R"; // it's red
        } else if (match.color == Robot.kGreenTarget) { // if the detected color is close to green
            colorString = "G"; // it's green
        } else if (match.color == Robot.kYellowTarget) { // if the detected color is close to yellow
            colorString = "Y"; // it's yellow
        } else {
            colorString = "Unknown"; // it's just unknown
        }

        // if the there's an assigned color and the color that the sensor sees isn't
        // that color, spin the wheel
        if (gameData != '\0' && gameData != colorString.charAt(0)) {
            System.out.println("color seen: " + colorString.charAt(0));
            System.out.println("game data: " + gameData);
            Robot.motor.set(ControlMode.PercentOutput, (.05)); // these need to be changed for the spark maxs
        } else { // if it's on the right color, stop the wheel
            Robot.motor.set(ControlMode.PercentOutput, 0); // these need to be changed for the spark maxs
        }

        /**
         * Open Smart Dashboard or Shuffleboard to see the color detected by the sensor.
         */
        SmartDashboard.putNumber("Red", detectedColor.red);
        SmartDashboard.putNumber("Green", detectedColor.green);
        SmartDashboard.putNumber("Blue", detectedColor.blue);
        SmartDashboard.putNumber("Confidence", match.confidence);
        SmartDashboard.putString("Detected Color", colorString);
    }
}