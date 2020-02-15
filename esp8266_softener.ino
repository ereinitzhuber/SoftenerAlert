#include <ESP8266WiFi.h>
#include <WiFiClient.h>
#include <ESP8266HTTPClient.h>

const char *ssid = "";
const char *password = "";
const char *postaddr = "";
int inputcount = 0;

void setup() {
  pinMode(13, INPUT_PULLUP);
  delay(2000);
  Serial.begin(115200);
  Serial.println();
  Serial.print("Connecting...");
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
  }
}
void loop() {
  int status = analogRead(13);
  if (status == false) {
    inputcount = inputcount + 1;
    if (inputcount == 1) {
      Serial.println("running");
      send();
    }
    delay(1000);
  }
  else {
    Serial.println("not running");
    inputcount = 0;
    delay(1000);
  }
}
void send() {
    HTTPClient http;
    http.begin(postaddr);
    http.addHeader("Content-Type", "application/json");
    http.POST("{\"name\":\"softener\", \"status\": \"running\"}");
    http.end();
}
