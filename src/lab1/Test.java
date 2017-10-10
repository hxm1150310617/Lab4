package lab1;

import java.io.*;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {
//
//        String[] command0 = {"cmd.exe", "/C",
//                "dot -Tpng C:\\Users\\lty96117\\code\\HIT\\HIT-2017SE\\data\\graph.dot -o C:\\Users\\lty96117\\code\\HIT\\HIT-2017SE\\data\\dijkstra.png"};
//        System.out.println(Arrays.toString(command0));
        //ProcessBuilder probuilder = new ProcessBuilder(command0);
        //You can set up your work directory
//        probuilder.directory(new File("C:\\Users\\lty96117\\code\\HIT\\HIT-2017SE\\data\\test.jpg"));

        //Process process = probuilder.start();

        Runtime run = Runtime.getRuntime();
        Process process0 = run.exec("cmd.exe /C dot -Tpng C:\\Users\\lty96117\\code\\HIT\\HIT-2017SE\\data\\graph.dot -o C:\\Users\\lty96117\\code\\HIT\\HIT-2017SE\\data\\dijkstra.png");
//        Process process0 = run.exec("cmd.exe /C C:\\Users\\lty96117\\code\\HIT\\HIT-2017SE\\data\\dijkstra.png");
//        String[] command1 = {"cmd.exe", "/C",
//                "C:\\Users\\lty96117\\code\\HIT\\HIT-2017SE\\data\\dijkstra_two_out.png"};
//
//        ProcessBuilder probuilder1 = new ProcessBuilder(command1);
//        Process process1 = probuilder1.start();

        String[] command = {"CMD", "/C", "dot -Tpng graph.dot -o dijkstra.png && dijkstra.png"};
        ProcessBuilder probuilder = new ProcessBuilder(command);
        //You can set up your work directory
        probuilder.directory(new File("C:\\Users\\lty96117\\code\\HIT\\HIT-2017SE\\data"));

        Process process = probuilder.start();

        //Read out dir output
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;
        System.out.printf("Output of running %s is:\n",
                Arrays.toString(command));
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        //Wait to get exit value
        try {
            int exitValue = process.waitFor();
            System.out.println("\n\nExit Value is " + exitValue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
