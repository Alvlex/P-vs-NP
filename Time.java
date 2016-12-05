package managementSolution;

public class Time {
	public long getStartTime(){
		long startTime;
		startTime = System.nanoTime();
		return startTime;
	}

	public void outputTotalTime(long startTime){
		long endTime;
		double totalTime;
		endTime   = System.nanoTime();
		totalTime = (endTime - startTime)/1e6;
		System.out.println(totalTime + " milliseconds");
	}
}
