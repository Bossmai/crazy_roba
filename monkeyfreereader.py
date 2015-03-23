from com.android.monkeyrunner import MonkeyRunner, MonkeyDevice
import time
import random
import sys

TEST_LENGTH = 600
MIN_X = 20
MAX_X = 220
MIN_Y = 20
MAX_Y = 400

package = 'com.news.newsshixun'
activity = 'com.news.newsshixun.FirstActivity'
runComponent = package + '/' + activity

print "Wait For connection..."
device = MonkeyRunner.waitForConnection(300, sys.argv[1])

print "Connected."
device.startActivity(component=runComponent)

start_time = time.time()
print "Current time:" + str(start_time)

MonkeyRunner.sleep(30)
end_time = start_time + 600

while time.time() < end_time:
    if random.randint(0, 1) % 2 == 0:
        x1 = random.randint(MIN_X, MAX_X)
        y1 = random.randint(MIN_Y, MAX_Y)
        x2 = random.randint(MIN_X, MAX_X)
        y2 = random.randint(MIN_Y, MAX_Y)
        device.drag((x1, y1), (x2, y2))
        print "Monkey drag from (" + str(x1) + ", " + str(y1) + ") to (" + str(x2) + ", " + str(y2) + ")"
    if random.randint(0, 1) % 2 == 0:

        x = random.randint(MIN_X, MAX_X)
        y = random.randint(MIN_Y, MAX_Y)
        device.touch(x, y, 'DOWN')
        MonkeyRunner.sleep(2)
        device.touch(x, y, 'UP')
        print "Monkey touch in (" + str(x) + ", " + str(y) + ")"
    print "Remain time: " + (str(end_time - time.time()))
    z_time = random.randint(0, 5)
    MonkeyRunner.sleep(z_time)
    print "Sleep: " + str(z_time)
print "Monkey success."
