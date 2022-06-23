import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class main {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		SRTF s = new SRTF();
		System.out.print("Enter the quantum time = ");
		RoundRobin rr = new RoundRobin(sc.nextInt());
		Priority P = new Priority();
		File file = new File(
				"C:\\Users\\ysyss\\OneDrive\\سطح المكتب\\K.S.U\\الترم 6\\عال227\\project\\project277\\project277\\project277\\test.txt");
		
		// Scan(Count) How many Processes in the file
		int Proces_count = 0;
		Scanner count_Scanner = new Scanner(file);
		while (count_Scanner.hasNextLine()) {

			count_Scanner.nextLine();
			count_Scanner.nextLine();
			Proces_count++;

		}
		System.out.println("There are #" + Proces_count + " Processes");
		
		// Scan the processes in the file
		Scanner in = new Scanner(file);
		while (in.hasNextLine()) {

			String name = in.nextLine();
			String num = in.nextLine();
			String nn[] = num.split(",");

			s.addSRTF(name, Integer.parseInt(nn[0]));

			rr.addRR(name, Integer.parseInt(nn[0]));
			P.addPrio(name, Integer.parseInt(nn[0]), Integer.parseInt(nn[1]));

		}
		
		System.out.println("==================================SRTF=========================================");
		s.findavgTime();
		System.out.println("\n===============================================================================");

		System.out.println("=============================Round Robin=========================================");
		rr.findavgTime();
		System.out.println("\n===============================================================================");

		System.out.println("============================Priority======================================");
		P.findavgTime();
		

		System.out.println("\n==============================================================================");
		
		in.close();
		count_Scanner.close();
		sc.close();
	}
	
}
