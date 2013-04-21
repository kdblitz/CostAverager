package me.costAvereger.math;

public class Statistics {
	/**
	 * The number of days stock gained, and lost
	 */
	int gainDays, lossDays;
	/**
	 * The maximum recorded gain and loss
	 */
	double maxGain, maxLoss;
	
	void recordGain(double gain) {
		maxGain = Math.max(maxGain, gain);
		gainDays++;
	}
	
	void recordLoss(double loss) {
		maxLoss = Math.min(maxLoss, loss);
		lossDays++;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("gain days: ").append(gainDays).append('\n');
		sb.append("max gain : ").append(maxGain).append('\n');
		sb.append("loss days: ").append(lossDays).append('\n');
		sb.append("max loss : ").append(maxLoss).append('\n');
		return sb.toString();
	}
}
