package lab1;

//now on C4

import java.io.*;
import java.util.*;

//Edit on B1

public class MyIOUtils {


    static HashMap<String, Integer> readDataAndWriteData(String filenameFrom, String filenameTo) {
        if (filenameFrom == null) throw new IllegalArgumentException("argument is null");
        Scanner scanner = null;
        PrintStream printStream = null;
        HashMap<String, Integer> hashMap = null;
        try {
            File fileFrom = new File(filenameFrom);
            File fileTo = new File(filenameTo);

            if (fileFrom.exists() && fileTo.exists()) {
                FileInputStream fis = new FileInputStream(fileFrom);
                scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");

                FileOutputStream fos = new FileOutputStream(fileTo);
                printStream = new PrintStream(fos, true, "UTF-8");

                scanner.useDelimiter(" |\r\n");
                ArrayList<String> list = new ArrayList<>();


                while (scanner.hasNext()) {
                	// to lowercase
                    String tmp = scanner.next().toLowerCase().replaceAll("[^A-Za-z]", "");
                    if (!Objects.equals(tmp, "")) {
                        //System.out.print(tmp + " ");
                        list.add(tmp);
                    }
                }
                // close the Scanner
                scanner.close();
                System.out.println("================read data completed==============");
                Iterator<String> iter = list.iterator();
                //get Vertices
                Set<String> set = new HashSet<>();
                while (iter.hasNext()) {
                    set.add(iter.next());
                }
                int V = set.size();
                int E = list.size() - 1; //pay attention here
                printStream.println(V);
                printStream.println(E);


                // get HashMap<String, Integer>
                hashMap = new HashMap<>();
                int index = 0;
                for (int i = 0; i < list.size(); i++) {
                    if (!hashMap.containsKey(list.get(i))) {
                        hashMap.put(list.get(i), index++);
                        System.out.println(i + ", " + list.get(i));
                    }
                }
                System.out.println("===============init graph completed===============");
                Set<String> set2 = hashMap.keySet();
                //System.out.println(set2.size());
                Iterator<String> iterator = set2.iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                   // System.out.println(key + "-->" + hashMap.get(key));
                }

                // print to file
                for (int i = 0; i < list.size() - 1; i++) {
                    String str = list.get(i) + " " + list.get(i + 1);
                    if (i == list.size() - 2) printStream.print(str);
                    else printStream.println(str);
                }
            }
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Could not open specific file(s). ", ioe);
        }

        return hashMap;

    }

}
