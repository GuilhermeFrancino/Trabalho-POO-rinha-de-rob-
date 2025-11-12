package Nascimento;

import robocode.*;
import java.awt.Color;

public class Nascimento extends Robot {

    public void run() {
        setBodyColor(Color.blue);
        setGunColor(Color.black);
        setRadarColor(Color.cyan);
        setBulletColor(Color.orange);

        setAdjustGunForRobotTurn(true);
        setAdjustRadarForGunTurn(true);

        while (true) {
            turnRadarRight(360);
            ahead(120);
            turnRight(30);
            back(60);
            turnLeft(20);
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        double angulo = getHeading() - getGunHeading() + e.getBearing();
        turnGunRight(angulo);

        double power = Math.min(3, Math.max(1, 400 / e.getDistance()));
        fire(power);

        if (e.getDistance() < 150) {
            back(100);
            turnRight(45);
        }
    }

    public void onHitByBullet(HitByBulletEvent e) {
        double direcao = 90 - e.getBearing() + (Math.random() * 60 - 30);
        turnRight(direcao);
        ahead(80 + Math.random() * 50);
    }

    public void onHitWall(HitWallEvent e) {
        back(80);
        turnRight(90);
    }
}
