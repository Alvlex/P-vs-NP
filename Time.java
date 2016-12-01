package managementSolution;

public class Time {
	public long getStartTime(){
		long startTime;
		startTime = System.currentTimeMillis();
		return startTime;
	}

	public void outputTotalTime(long startTime){
		long endTime, totalTime;
		endTime   = System.currentTimeMillis();
		totalTime = endTime - startTime;
		System.out.println(totalTime + " milliseconds");
	}
}
