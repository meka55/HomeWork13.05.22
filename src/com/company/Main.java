package com.company;

import java.util.Random;

public class Main {
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic"};
    public static int[] heroesHealth = {270, 280, 260, 300};
    public static int[] heroesDamage = {20, 15, 25, 0};
    public static int indexMedic;
    public static int index = 0;
    public static int bossHealth = 1000;
    public static int bossDamage = 50;
    public static String bossDefenceType;
    public static int roundNumber = 0;

    public static void medic() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] < 100 && heroesHealth[i] > 0 && heroesHealth[i] != heroesHealth[indexMedic] &&
                    heroesHealth[indexMedic] > 0) {

                int medicHelp = 150;
                heroesHealth[i] = heroesHealth[i] + medicHelp;
                    System.out.println("MEDIC Help " + medicHelp + " " + heroesAttackType[i]);
                    break;

            }
        }
    }

    public static void indexMed(){
        for (String v: heroesAttackType ) {
            if (v == "Medic") {
                indexMedic = index;
                System.out.println("indexMedic = " + index);
                break;
            }
            index++;

        }
    }

    public static void main(String[] args) {
        printDStatistics();
        indexMed();
        isGameFinished();
        while (!isGameFinished()) {
            medic();
            round();
        }
    }

    private static void round() {
        roundNumber++;
        chooseBossDefenceType();
        bossHits();
        heroesHit();
        printDStatistics();
    }

    public static void chooseBossDefenceType() {
        Random random = new Random();
        int randNum = random.nextInt(heroesAttackType.length - 1);
        bossDefenceType = heroesAttackType[randNum];
        System.out.println("Boss choose: " + bossDefenceType);
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] < bossDamage) {
                heroesHealth[i] = 0;
            } else {
                heroesHealth[i] = heroesHealth[i] - bossDamage;
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesAttackType[i] == bossDefenceType) {
                    Random r = new Random();
                    int coefficient = r.nextInt(8) + 2;

                    if (bossHealth < heroesDamage[i] * coefficient) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coefficient;
                    }
                    System.out.println("Critical Damage: " + heroesDamage[i] * coefficient);
                } else {
                    if (bossHealth < heroesDamage[i]) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static Boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int j : heroesHealth) {
            if (j > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!");
        }
        return allHeroesDead;
    }

    public static void printDStatistics() {
        System.out.println("----" + roundNumber + " ROUND----");
        System.out.println("Boss health: " + bossHealth + " [" + bossDamage + "]");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + " [" + heroesDamage[i] + "]");
        }
    }
}
