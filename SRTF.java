
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class SRTF {
	Procces s = new Procces();
	int count = 0;

	public void addSRTF(String name, int burs) {
		s.name[count] = name;
		s.ar[count] = count;
		s.bt[count] = burs;
		s.pid[count] = ++count;

	}
	
	public void findCompletionTime() {
		for (int i = 0; i < count; i++)
			s.ct[i] = s.bt[i] + s.wt[i] + s.ar[i];
	}

	public void findWaitingTime() {
		int p_inChart = -1;
		int rt[] = new int[30];

		// calculating waiting time
		for (int i = 0; i < count; i++) {
			rt[i] = s.bt[i];
		}
		int comp = 0, t = 0, min = Integer.MAX_VALUE;
		int shortest = 0, ft;
		boolean check = false;
		while (comp != count) {

			for (int j = 0; j < count; j++) {
				if ((s.ar[j] <= t) && (rt[j] < min) && rt[j] > 0) {
					min = rt[j];
					shortest = j;
					check = true;
				}
			}

			if (check == false) {
				t++;
				continue;
			}

			//Gantt Chart Display
			if (shortest != p_inChart) {
				if(p_inChart == -1)	{
					System.out.println("\n__________________________________Gantt Chart_____________________________________");
					System.out.print("|| ");
				}
				p_inChart = shortest;
				System.out.print(t + "|--" + s.name[shortest] + "-");
			}
			else {
				System.out.print("-");
			}
			
			
			rt[shortest]--;

			min = rt[shortest];
			if (min == 0)
				min = Integer.MAX_VALUE;

			// If a process gets completely
			// executed
			if (rt[shortest] == 0) {

				// Increment comp
				comp++;
				check = false;

				// Find finish time of current
				// process
				ft = t +1;

				// Calculate waiting time
				s.wt[shortest] = ft - s.bt[shortest] - s.ar[shortest];
				
				// Calculate Completion time
				//s.ct[shortest] = ft;

				if (s.wt[shortest] < 0)
					s.wt[shortest] = 0;
			}
			// Increment time
			t++;
		}
		
		//Gantt Chart ending
		System.out.println(t + " ||");
		System.out.println("__________________________________________________________________________________\n");
	}

	public void findavgTime() {
		int total_wt = 0, total_ct = 0;

		findWaitingTime();
		
		findCompletionTime();
		
		System.out.print("--------------------------------------------------------------------------------");
		System.out.print("\nProcess\t      Burst Time\t       Waiting Time\t         Completion Time \n");
		System.out.print("--------------------------------------------------------------------------------\n");
		for (int i = 0; i < count; i++) {
			total_wt = total_wt + s.wt[i];
			total_ct = total_ct + s.ct[i];

			System.out.printf(" %s ", s.name[i]);
			System.out.printf("\t%d ", s.bt[i]);
			System.out.printf("\t\t\t%d", s.wt[i]);
			System.out.printf("\t\t\t\t%d\n", s.ct[i]);
		}
		System.out.println("Average waiting time = " + (float) total_wt / (float) count);
		System.out.println("Average Completion time = " + (float) total_ct / (float) count);
		
	}
	
}