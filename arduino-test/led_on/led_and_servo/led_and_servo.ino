#include "variant.h"
#include <stdio.h>
#include <adk.h>
#include <Servo.h>
#define  LED_PIN  13
#define  PIN_SERVO 9

Servo myservo;

// Accessory descriptor. It's how Arduino identifies itself to Android.
char descriptionName[] = "ArduinoADK_2"; 
char modelName[] = "UDOO_ADK";           // your Arduino Accessory name (Need to be the same defined in the Android App)
char manufacturerName[] = "Aidilab";     // manufacturer (Need to be the same defined in the Android App)

// Make up anything you want for these
char versionNumber[] = "1.0";            // version (Need to be the same defined in the Android App)
char serialNumber[] = "1";
char url[] = "http://www.udoo.org";      // If there isn't any compatible app installed, Android suggest to visit this url

USBHost Usb;
ADK adk(&Usb, manufacturerName, modelName, descriptionName, versionNumber, url, serialNumber);

#define RCVSIZE 128
uint8_t buf[RCVSIZE];
uint32_t bytesRead = 0;

void setup()
{
    Serial.begin(115200);   
    pinMode(LED_PIN, OUTPUT);
    
    //setup and reset servo
    myservo.attach(PIN_SERVO);
    
    // blink for fun
    digitalWrite(LED_PIN, HIGH);
    delay(100);
    digitalWrite(LED_PIN, LOW);
}

void loop()
{
    Usb.Task();
     
    if (adk.isReady()) {
      adk.read(&bytesRead, RCVSIZE, buf);// read data into buf variable
      if (bytesRead > 0) {
     
        // trasmission blink    
        digitalWrite(LED_PIN, HIGH);
        delay(100);
        digitalWrite(LED_PIN, LOW);
        
        for(int j=0; j<5; j++) {
          
           for(int i=80; i>50; i--) {
              myservo.write(i);
              delay(60);
            }
            
            for(int i=50; i<80; i++) {
              myservo.write(i);
              delay(60);
            }
              
        }
    }
  }
    
  delay(10);
}

// the characters sent to Arduino are interpreted as ASCII, we decrease 48 to return to ASCII range.
uint8_t parseCommand(uint8_t received) {
  return received - 48;
}
