
import java.util.Scanner;

public class RoundRobin {
	Procces R = new Procces();
	int count = 0;
	int q = 0;

	public RoundRobin(int q) {
		this.q = q;
	}

	public void addRR(String name, int bursTime) {
		R.name[count] = name;
		R.ar[count] = count;
		R.bt[count] = bursTime;
		R.pid[count] = ++count;
	}

	public void findWaitingTime() {

		int rem_bt[] = new int[30];

		//Gant Chart starts
		System.out.println("\n__________________________________Gantt Chart_____________________________________");
		System.out.print("|| ");

		for (int i = 0; i < count; i++)
			rem_bt[i] = R.bt[i];

		int t = 0;
		while (true) {
			boolean done = true;
			
			
			for (int i = 0; i < count; i++) {
				
				
				
				if (rem_bt[i] > 0) {
					done = false;
					
					//Gantt Chart processes
					System.out.print(t +"|--" + R.name[i]);
					
					if (rem_bt[i] > q) {
						
						//Gant Chart
						for (int x = 0; x < q; x++)
							System.out.print("-");
						
						
						// Increase the value of t i.e. shows
						// how much time a process has been processed
						t += q;

						// Decrease the burst_time of current process
						// by quantum
						rem_bt[i] -= q;
						
						
						
					}

					// If burst time is smaller than or equal to
					// quantum. Last cycle for this process
					else {
						
						//Gant Chart
						for (int x = 0; x < rem_bt[i]; x++)
							System.out.print("-");
						
						
						// Increase the value of t i.e. shows
						// how much time a process has been processed
						t = t + rem_bt[i];

						// Waiting time is current time minus time
						// used by this process
						R.wt[i] = t - R.bt[i] - R.ar[i];
						

						// As the process gets fully executed
						// make its remaining burst time = 0
						rem_bt[i] = 0;
					}
				}
			}

			// If all processes are done
			if (done == true)
				break;
		}
		
		//Gantt Chart ending
				System.out.println(t + " ||");
				System.out.println("__________________________________________________________________________________\n");
	}

	public void findCompletionTime() {
		for (int i = 0; i < count; i++)
			R.ct[i] = R.bt[i] + R.wt[i] + R.ar[i];
	}

	public void findavgTime() {
		int total_wt = 0, total_ct = 0;

		findWaitingTime();
		findCompletionTime();
		
		System.out.print("--------------------------------------------------------------------------------");
		System.out.print("\nProcess\t      Burst Time\t       Waiting Time\t          Completion Time\n");
		System.out.print("--------------------------------------------------------------------------------\n");
		for (int i = 0; i < count; i++) {
			total_wt = total_wt + R.wt[i];
			total_ct += R.ct[i];

			System.out.printf(" %s ", R.name[i]);
			System.out.printf("\t%d ", R.bt[i]);
			System.out.printf("\t\t\t%d", R.wt[i]);
			System.out.printf("\t\t\t\t%d\n", R.ct[i]);
		}
		System.out.println("Average Waiting time = " + (float) total_wt / (float) count);
		System.out.println("Average Completion time = " + (float) total_ct / (float) count);

	}
}