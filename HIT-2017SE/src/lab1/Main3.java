package lab1;
//now on C4

//Edit on B1

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import sun.font.CreatedFontTracker;

public class Main3 {

	public static String dataPath = "C:\\Users\\10297\\SoftwareEngineering\\HIT-2017SE\\data\\";
	public static String fileName = "Data.txt";
	public static EdgeWeightedDigraph EWD = null;

	public static void main(String[] args) throws IOException, InterruptedException {
		// String filePath =
		// "C:\\Users\\10297\\SoftwareEngineering\\HIT-2017SE\\data\\Data.txt";

		// String filePathTo =
		// "C:\\Users\\10297\\SoftwareEngineering\\HIT-2017SE\\data\\To.txt";

		while (true) {
			System.out.println("===============================================");
			System.out.println("====================功能选择=====================");
			System.out.println("              1、读入文本并生成有向图");
			System.out.println("              2、展示有向图");
			System.out.println("              3、查询桥接词");
			System.out.println("              4、根据BridgeWord生成文本");
			System.out.println("              5、计算最短路径 ");
			System.out.println("              6、随机游走");
			System.out.println("              7、退出");

			System.out.println("===============================================");
			System.out.println("===============================================");
			Scanner sc = new Scanner(System.in);
			int choice = 0;
			if (sc.hasNextInt()) {
				choice = sc.nextInt();
			}
			switch (choice) {
			case 1: {
				System.out.println("Input Source Directory");
				String directory = sc.next();
				dataPath = directory;
				EdgeWeightedDigraph.setPath(dataPath);
				System.out.println("Input Data File Name:");
				fileName = sc.next();
				EWD = createDirectedGraph(fileName);
				System.out.println("Press Enter to continue");
				System.in.read();
				break;
			}
			case 2: {
				showDirectedGraph(EWD);
				System.out.println("Press Enter to continue");
				System.in.read();
				break;
			}
			case 3: {
				System.out.println("请输入要查询桥接词的两个单词：");
				String word1 = sc.next().toLowerCase();
				String word2 = sc.next().toLowerCase();
				System.out.println(queryBridgeWords(EWD, word1, word2));
				System.out.println("Press Enter to continue");
				System.in.read();
				break;
			}
			case 4: {
				String Input = EdgeWeightedDigraph.getInput();
				System.out.println(EdgeWeightedDigraph.generateNewText(EWD, Input));
				System.out.println("Press Enter to continue");
				System.in.read();
				break;
			}
			case 5: {
				System.out.println("Select the function:");
				System.out.println("(1):Shortest Path from word to word.");
				System.out.println("(2):Shortest Path from single word.");
				int funcchoice = -1;
				if (sc.hasNextInt()) {
					funcchoice = sc.nextInt();
				}
				switch (funcchoice) {
				case 1: {
					System.out.println("Input word1:");
					String word1 = sc.next().toLowerCase();
					System.out.println("Input word2:");
					String word2 = sc.next().toLowerCase();
					calcShortestPath(EWD, word1, word2);
					break;
				}
				case 2: {
					System.out.println("Input Source Word:");
					String word3 = sc.next().toLowerCase();
					calcShortestPathss(EWD, word3);
					break;
				}
				}
				System.out.println("Press Enter to continue");
				System.in.read();
				break;

			}
			case 6: {
				System.out.println("Press Enter to begin random-walk");
				System.in.read();
				randomWalk(EWD);
				System.out.println("Press Enter to continue");
				System.in.read();
				System.in.read();
				break;
			}
			case 7:
				System.out.println("Program Ended with Exit Code:0");
				System.exit(0);

			default: {
				System.out.println("Invalid Input!");
				System.out.println("Press Enter to continue");
				System.in.read();
				break;
			}

			}
		}
	}

	public static void setdataPath(String dataPathIn) {
		dataPath = dataPathIn;
	}

	public static EdgeWeightedDigraph createDirectedGraph(String filename) throws FileNotFoundException {

		return EdgeWeightedDigraph.initGraphFromFile(dataPath + filename, dataPath + "To.txt");

	}

	public static void showDirectedGraph(EdgeWeightedDigraph G) throws IOException, InterruptedException {
		EdgeWeightedDigraph.getGUI4Graph(G);
	}

	public static String queryBridgeWords(EdgeWeightedDigraph G, String word1, String word2) {
		return EdgeWeightedDigraph.queryBridgeWords(G, word1, word2);
	}

	public static String generateNewText(EdgeWeightedDigraph G, String InputText) {

		return EdgeWeightedDigraph.generateNewText(G, InputText);

	}

	public static String calcShortestPath(EdgeWeightedDigraph G, String word1, String word2)
			throws IOException, InterruptedException {
		EdgeWeightedDigraph.getDijkstraBetweenTwoNodes(G, word1, word2);
		return null;
	}

	public static String calcShortestPathss(EdgeWeightedDigraph G, String word) throws IOException {
		if (G.getMap().containsKey(word)) {
			return EdgeWeightedDigraph.getDijkstraSP(G, word);
		} else
			return ("Invalid Input!");
	}

	public static String randomWalk(EdgeWeightedDigraph G) throws IOException, InterruptedException {
		return EdgeWeightedDigraph.randomWalk(G);

	}
}
