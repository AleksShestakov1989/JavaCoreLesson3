package Task2;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) throws IOException {
        GameProgress game1 = new GameProgress(100, 5, 1, 0.1);
        GameProgress game2 = new GameProgress(90, 4, 2, 10.1);
        GameProgress game3 = new GameProgress(53, 5, 5, 100.1);

        saveProgress("/Users/Games/savegames/save1.dat", game1);
        saveProgress("/Users/Games/savegames/save2.dat", game2);
        saveProgress("/Users/Games/savegames/save3.dat", game3);
        ArrayList<String> nameSave = new ArrayList<>();
        Collections.addAll(nameSave, "save1.dat", "save2.dat", "save3.dat");
        zipFiles("/Users/Games/savegames/", nameSave);
        cleanFile(nameSave);
        openZip("/Users/Games/savegames/zip.zip", "/Users/Games/savegames/");
        System.out.println(openProgress("/Users/Games/savegames/save1.dat"));


    }

    public static void saveProgress(String path, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void zipFiles(String path, ArrayList<String> fileToZip) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path + "/zip.zip"))) {
            for (String name : fileToZip) {
                try (FileInputStream fis = new FileInputStream(path + "/" + name)) {
                    ZipEntry entry = new ZipEntry(name);
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void openZip(String pathFile, String pathDir) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(pathFile))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(pathDir + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static GameProgress openProgress(String pathSave) {
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(pathSave);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return gameProgress;

    }

    public static void cleanFile(ArrayList<String> nameSave) {
        File delete;
        for (String name : nameSave) {
            delete = new File("/Users/Games/savegames/" + name);
            delete.delete();
        }
    }
}
