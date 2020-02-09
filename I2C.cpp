#include <Adafruit_NeoPixel.h>

#include <Wire.h>

#include "I2C.h"

byte I2C::readData[1];
//byte I2C::writeData[2];

void I2C::initialize(byte address) {
  Wire.begin(address);
  Wire.onReceive(receiveEvent);
//  Wire.onRequest(requestEvent);
//  for (byte i = 0; i < sizeof(readData) / sizeof(byte); i ++)
//    readData[i] = 0;


  
  readData[0] = 0;
  
//  for (byte i = 0; i < sizeof(writeData) / sizeof(byte); i ++)
//    writeData[i] = 0;
}

void I2C::receiveEvent() {
  //get bytes of data
  for (byte i = 0; Wire.available() > 0 && i < sizeof(readData) / sizeof(byte); i ++)
    readData[i] = Wire.read();  
}

//void I2C::requestEvent() {
//  //send all data in writeData[]
//  Wire.write(writeData, sizeof(writeData) / sizeof(byte));
//}

//byte I2C::getAllianceColor() {
//  return readData[0];
//}

byte I2C::getSensorColor() {
  return readData[0];
}
