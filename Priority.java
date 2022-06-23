import java.util.Arrays;
import java.util.Comparator;

public class Priority {

	Procces P = new Procces();
	int count = 0;

	public void addPrio(String name, int burs, int prio) {
		P.name[count] = name;
		P.ar[count] = count;
		P.bt[count] = burs;
		P.pr[count] = prio;
		P.done[count] = false;
		P.pid[count] = ++count;

	}
	
	public void findWaitingTime() {
		for (int i = 0; i < count; i++)
			P.wt[i] = P.ct[i] - P.bt[i] - P.ar[i];
	}
	
	public void SortProcces() {
		int in_cpu = 0, time = 0, priority;
		
		for (int i = 0; i < count; i++) {
			
			//Select highest priority 
			priority = 5;
			for(int j=0; j < count; j++) {
				if(P.ar[j] <= time && P.pr[j] < priority && P.done[j] == false) {
					in_cpu = j;
					priority = P.pr[j];
				}	
			}
			
			//Gant Chart 
			System.out.print("|--" + P.name[in_cpu] );
			for (int x = 0; x < P.bt[in_cpu]; x++)
				System.out.print("-");
			System.out.print((P.bt[in_cpu]+time));
			
			time += P.bt[in_cpu];
			P.done[in_cpu] = true;		
			P.ct[in_cpu] = time;
					
	            
		}
	
		
	}


	public void findavgTime() {
		int total_wt = 0, total_ct = 0;

		// Gant Chart starts
		System.out.println("\n__________________________________Gantt Chart_____________________________________");
		System.out.print("|| 0");

		SortProcces();
		
		// Gantt Chart ending
		System.out.println(" ||");
		System.out.println("__________________________________________________________________________________\n");
		
		findWaitingTime();

		// Display processes along with all details
		System.out.print("-----------------------------------------------------------------------------------------");
		System.out.print("\nProcess\t      Priority\t  Burst Time\t         Waiting Time\t         Completion Time\n");
		System.out.print("-----------------------------------------------------------------------------------------\n");

		// Calculate total waiting time and total Completion Time
		for (int i = 0; i < count; i++) {
			total_wt = total_wt + P.wt[i];
			total_ct = total_ct + P.ct[i];
			System.out.printf(" %s ", P.name[i]);
			System.out.printf("\t%d ", P.pr[i]);
			System.out.printf("\t\t%d ", P.bt[i]);
			System.out.printf("\t\t\t%d", P.wt[i]);
			System.out.printf("\t\t\t%d\n", P.ct[i]);
		}

		System.out.print("\nAverage waiting time = " + (float) total_wt / (float) count);
		System.out.print("\nAverage Completion time = " + (float) total_ct / (float) count);
	}

}
