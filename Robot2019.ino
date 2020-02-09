#include "I2C.h"
#include "LEDStrip.h"
//#include "PixyCam.h"
//#include "Ultrasonic.h"

void setup() {
  Serial.begin(9600);
  if (Serial)
    Serial.println("program started");
  I2C::initialize(0x1);
  LEDStrip::initialize(6, 20, 255);
  //PixyCam::initialize();
  //Ultrasonic::initialize();
}

void loop() {
  LEDStrip::updateDisplay(I2C::getSensorColor());
  //PixyCam::refresh(I2C::getPixyCamState());
  //I2C::setWriteData(PixyCam::getObjInView(), PixyCam::getXValue());
}
