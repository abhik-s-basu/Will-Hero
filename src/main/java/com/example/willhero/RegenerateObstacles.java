package com.example.willhero;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class RegenerateObstacles {
        private ArrayList<GameObject> myList;
        private GameObject deserializedObj = null;
        private Hero gameHero;
        private Game game = new Game();

        private void deserialize(String fileName) throws IOException, ClassNotFoundException {

            ObjectInputStream in = null;
            myList = new ArrayList<>();
            try {
                in = new ObjectInputStream(new FileInputStream(fileName));
                deserializedObj = (GameObject)in.readObject();
                gameHero = new Hero(deserializedObj.getX(),deserializedObj.getY(),game,
                        "file:src/main/resources/Assets/Knight.png");
                while(true) {
                    try{
                        GameObject tmp = (GameObject) in.readObject();
                        myList.add(tmp);
                    }catch (EOFException e) {
                        break;
                    }catch (ClassCastException e) {
                        System.out.println("Invalid Class Cast Exception");
                    }
                }
            }
            finally {
                in.close();
            }
        }

        public ArrayList<GameObject> regenerateGameObjects(String fileName) throws IOException, ClassNotFoundException {
            deserialize(fileName);
            System.out.println(fileName);
            ArrayList<GameObject> finalList = new ArrayList<>();
            for(GameObject obj : myList) {
                System.out.println(obj.getX()+"   "+obj.getY()+"   "+
                        obj.getLength()+"   "+obj.getBreadth()+"   "+obj.getClass().getSimpleName());
                switch (obj.getClass().getSimpleName()){
                    case "Island":
                        finalList.add(new Island((int) obj.getX(),(int) obj.getY(),(int) obj.getLength(),(int) obj.getBreadth()
                                , obj.isMoving(), obj.getImageURL()));
                        break;
                    case "Hero":
                        finalList.add(new Hero((int) obj.getX(),(int) obj.getY()
                                , game, obj.getImageURL()));
                        break;
                    case "SmallOrc":
                        finalList.add(new SmallOrc((int) obj.getX(),(int) obj.getY(),(int) obj.getLength(),(int) obj.getBreadth(),
                                obj.getXSpeed(), obj.getYSpeed(),null ,((SmallOrc) obj).getCoinsOnKill(),
                                ((SmallOrc) obj).getHealth(), obj.getImageURL()));
                        break;

                    case "MediumOrc":
                        finalList.add(new MediumOrc((int) obj.getX(),(int) obj.getY(),(int) obj.getLength(),(int) obj.getBreadth(),
                                obj.getXSpeed(), obj.getYSpeed(),null ,((MediumOrc) obj).getCoinsOnKill(),
                                ((MediumOrc) obj).getHealth(), obj.getImageURL()));
                        break;
                    case "BossOrc":
                        finalList.add(new BossOrc((int) obj.getX(),(int) obj.getY(),(int) obj.getLength(),(int) obj.getBreadth(),
                                obj.getXSpeed(), obj.getYSpeed(),"RED or GREEN" ,((BossOrc) obj).getCoinsOnKill(),
                                ((BossOrc) obj).getHealth(), obj.getImageURL()));
                        break;
                    case "TNT":
                        finalList.add(new TNT((int) obj.getX(), (int) obj.getY(), obj.getImageURL()));
                        break;
                    case "TNTBlinker":
                        finalList.add(new TNTBlinker((int) obj.getX(), (int) obj.getY(), obj.getImageURL()));
                        break;
                    case "TNTSmoke":
                        finalList.add(new TNTSmoke((int) obj.getX(), (int) obj.getY(), obj.getImageURL()));
                        break;
                    case "CoinChest":
                        finalList.add(new CoinChest(((CoinChest) (obj)).openChest(), (int) obj.getX(),
                                (int) obj.getY(), obj.getImageURL()));
                        break;
                    case "WeaponChest":
                        finalList.add(new WeaponChest(new Helmet(new ThrowingAxe(), new ThrowingKnife()), (int) obj.getX(),
                                (int) obj.getY(), obj.getImageURL()));
                        break;
                    }
                }
            return finalList;
        }

        public Game getGame(){
            return this.game;
        }
}

