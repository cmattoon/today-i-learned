#include <compat/deprecated.h>
#include <FlexiTimer2.h>

#define NUM_CHANNELS 6
#define HEADER_LENGTH 4
#define PACKET_LENGTH (NUM_CHANNELS * 2 + HEADER_LENGTH + 1)
#define SAMPLE_FREQ 256 // Hz
#define TIMER2VAL (1024 / (SAMPLE_FREQ))
#define SYNC0 0xa5
#define SYNC1 0x5a
#define PROTO_VERSION 2
#define SERIAL_BAUD 57600
#define LED1 13
#define CAL_SIG 9

// transmission packet
volatile unsigned char TxBuf[PACKET_LENGTH];
// next byte in transmission
volatile unsigned char TxIndex;
// current channel being sampled
volatile unsigned char CurrentCh;
// Additional divisor for CAL_SIG generation
volatile unsigned char counter = 0;
// ADC current value
volatile unsigned char ADCValue = 0;

void toggle_led(int LED) {
  digitalWrite(LED, (digitalRead(LED) == LOW));
}

void setup() {
  noInterrupts();
  pinMode(LED1, OUTPUT);
  digitalWrite(LED1, LOW);
  pinMode(CAL_SIG, OUTPUT);

  TxBuf[0] = SYNC0;
  TxBuf[1] = SYNC1;
  TxBuf[2] = PROTO_VERSION;
  for (int i=3; i < (3+NUM_CHANNELS); ++i)
    {
      TxBuf[i] = 0x02; // HIGH
      TxBuf[i+1] = 0x00; // LOW
      i++;
    }
  TxBuf[2 * NUM_CHANNELS * HEADER_LENGTH] = 0x01;

  FlexiTimer2::set(TIMER2VAL, Timer2_Overflow_ISR);
  FlexiTimer2::start();
  Serial.begin(SERIAL_BAUD);

  interrupts();
}
void Timer2_Overflow_ISR()
{
  // Toggle LED1 with ADC sampling frequency /2

  toggle_led(LED1);
  //Read the 6 ADC inputs and store current values in Packet
  for (CurrentCh=0; CurrentCh<6; CurrentCh++) {
    ADCValue = analogRead(CurrentCh);
    // Write High Byte
    TxBuf[((2*CurrentCh) + HEADERLEN)] = ((unsigned char)((ADC_Value & 0xFF00) >> 8));
    // Write Low Byte
    TxBuf[((2*CurrentCh) + HEADERLEN + 1)] = ((unsigned char)(ADC_Value & 0x00FF));

  }

  // Send Packet
  for (TxIndex=0; TxIndex < 17; TxIndex++) {
    Serial.write(TxBuf[TxIndex]);
  }

  // Increment the packet counter
  TxBuf[3]++;

  // Generate the CAL_SIGnal
  counter++;            // increment the counter
  if (counter == 12) {    // 250/12/2 = 10.4Hz ->Toggle frequency
    counter = 0;
    toggle_led(CAL_SIG); // Generate CAL signal with frequ ~10Hz
  }
}

void loop() {
  __asm__ __volatile__ ("sleep");
}
