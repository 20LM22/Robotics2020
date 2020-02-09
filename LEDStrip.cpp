#include "LEDStrip.h"

Adafruit_NeoPixel LEDStrip::strip;

//const byte LEDStrip::pixelSpacing = 5;
//order for RGB values is RBG
const uint32_t LEDStrip::off = strip.Color(0, 0, 0);
const uint32_t LEDStrip::red = strip.Color(255, 0, 0);
//const uint32_t LEDStrip::orange = strip.Color(255, 0, 31);
const uint32_t LEDStrip::yellow = strip.Color(200, 200, 0);
const uint32_t LEDStrip::green = strip.Color(0, 255, 0); 
const uint32_t LEDStrip::blue = strip.Color(0, 0, 255); 
//const uint32_t LEDStrip::purple = strip.Color(127, 255, 0);
//const uint32_t LEDStrip::white = strip.Color(255, 255, 255);

//int LEDStrip::counter;
//unsigned long LEDStrip::mainTimeStamp;
//unsigned long LEDStrip::diagnosticTimeStamp;
//uint32_t LEDStrip::allianceColor;
//bool LEDStrip::diagnosticOn;

uint32_t LEDStrip::color;

void LEDStrip::initialize(byte pin, byte numLEDs, byte brightness) {
  strip = Adafruit_NeoPixel(numLEDs, pin, NEO_GRB + NEO_KHZ800);
  strip.begin();
  strip.setBrightness(brightness);
  strip.show();
  //counter = 0;
  //mainTimeStamp = millis();
  //diagnosticTimeStamp = millis();
  //robotOn = false;
}

void LEDStrip::updateDisplay(byte sensedColor) {
  //sets sensed color
  switch (sensedColor) {
    case 0:
      color = red;
      break;
    case 1:
      color = blue;
      break;
    case 2:
      color = green;
      break;
    case 3:
      color = yellow;
      break;
    case 4:
      color = off;
      break;      
  }
  //send data to LED strip
  solid();
  strip.show();
}

void LEDStrip::allOff() {
  for (byte i = 0; i < strip.numPixels(); i ++)
    strip.setPixelColor(i, off);
}

void LEDStrip::solid() {
  for (byte i = 0; i < strip.numPixels(); i ++)
    strip.setPixelColor(i, color);
}
