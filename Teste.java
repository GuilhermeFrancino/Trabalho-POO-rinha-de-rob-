package GuilhermeF_robo2;

import robocode.*;
import robocode.util.Utils;
import java.awt.Color;

public class GuilhermeF_robo2 extends AdvancedRobot {

    public void run() {
        setBodyColor(Color.blue);
        setGunColor(Color.black);
        setRadarColor(Color.cyan);
        setBulletColor(Color.orange);
        setScanColor(Color.white);

        setAdjustGunForRobotTurn(true);
        setAdjustRadarForGunTurn(true);

        while (true) {
            turnRadarRight(360);
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        double distancia = e.getDistance();
        double energiaInimigo = e.getEnergy();

        double anguloParaInimigo = getHeading() + e.getBearing();
        double ajusteGun = Utils.normalRelativeAngleDegrees(anguloParaInimigo - getGunHeading());
        setTurnGunRight(ajusteGun);

        double ajusteRadar = Utils.normalRelativeAngleDegrees(anguloParaInimigo - getRadarHeading());
        setTurnRadarRight(2 * ajusteRadar);

        double poderTiro = Math.min(3, Math.max(1, 400 / distancia));
        fire(poderTiro);

        double mover = distancia - 150;
        setAhead(mover);
        setTurnRight(e.getBearing() + 90 - 30 * Math.random());

        if (energiaInimigo > getEnergy() + 10 || distancia < 150) {
            fugirDoInimigo(e);
        }

        execute();
    }

    private void fugirDoInimigo(ScannedRobotEvent e) {
        double anguloFuga = e.getBearing() + 180 - 45 + (Math.random() * 90 - 45);
        setTurnRight(anguloFuga);
        setAhead(200 + Math.random() * 100);
    }

    public void onHitByBullet(HitByBulletEvent e) {
        double anguloEvasao = 90 - e.getBearing();
        setTurnRight(anguloEvasao);
        setAhead(120 + Math.random() * 80);
    }

    public void onHitWall(HitWallEvent e) {
        back(80);
        turnRight(90);
    }
}
