import RPi.GPIO as GPIO
import time
import requests

GPIO.setmode(GPIO.BCM)
GPIO.setup(18, GPIO.IN, pull_up_down=GPIO.PUD_UP)
inputcount = 0

while True:
    state = GPIO.input(18)
    if state == False:
        inputcount += 1
        if inputcount == 1:
            requests.post('http://127.0.0.1:8080/status', json={"name":"softener", "status": "running"})       
    else:
        inputcount = 0
    time.sleep(5)
