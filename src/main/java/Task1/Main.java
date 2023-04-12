package Task1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        File dirSrc = new File("//Users/Games", "src");
        File dirRes = new File("//Users/Games", "res");
        File dirSavegames = new File("//Users/Games", "savegames");
        File dirTemp = new File("//Users/Games", "temp");
        dirSrc.mkdir();
        dirRes.mkdir();
        dirSavegames.mkdir();
        dirTemp.mkdir();

        File dirSrcMain = new File(dirSrc, "main");
        File dirSrcTest = new File(dirSrc, "test");
        if (dirSrcMain.mkdir()) {
            sb.append("Directory name : " + dirSrcMain.getName()
                    + " Path : " + dirSrcMain.getPath()
                    + "\n");
        }

        if (dirSrcTest.mkdir()) {
            sb.append("Directory name : " + dirSrcTest.getName()
                    + " Path : " + dirSrcTest.getPath()
                    + "\n");

        }

        File fileMain = new File(dirSrcMain, "Main.java");
        File fileUtils = new File(dirSrcMain, "Utils.java");

        try {
            if (fileMain.createNewFile()) {
                sb.append("File name : " + fileMain.getName()
                        + " Path : " + fileMain.getPath()
                        + "\n");
            }

            if (fileUtils.createNewFile()) {
                sb.append("File name : " + fileUtils.getName()
                        + " Path : " + fileUtils.getPath()
                        + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        File dirDrawables = new File(dirRes, "drawables");
        File dirVectors = new File(dirRes, "vectors");
        File dirIcons = new File(dirRes, "icons");

        if (dirDrawables.mkdir()) {
            sb.append("Directory name : " + dirDrawables.getName()
                    + " Path : " + dirDrawables.getPath()
                    + "\n");
        }
        if (dirVectors.mkdir()) {
            sb.append("Directory name : " + dirVectors.getName()
                    + " Path : " + dirVectors.getPath()
                    + "\n");
        }
        if (dirIcons.mkdir()) {
            sb.append("Directory name : " + dirIcons.getName()
                    + " Path : " + dirIcons.getPath()
                    + "\n");
        }

        File fileTemp = new File(dirTemp, "temp.txt");

        try {
            if (fileTemp.createNewFile()) {
                sb.append("File name : " + fileTemp.getName()
                        + " Path : " + fileTemp.getPath()
                        + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (FileOutputStream fos = new FileOutputStream(fileTemp)) {
            byte[] bytes = sb.toString().getBytes();
            fos.write(bytes, 0, bytes.length);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (FileInputStream fin = new FileInputStream(fileTemp)) {
            int i;
            while ((i = fin.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

