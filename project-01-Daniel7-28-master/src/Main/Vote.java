package Main;

/**
 * 
 * Vote class
 * Contains voted Candidate and Rank.
 * 
 * @author: Juan D. Pérez Sepúlveda
 * @version: 2.0
 * @since 2022-02-22
 */

public class Vote {
	
	Candidate candidate;
	int rank;
	Vote(Candidate candidate, int rank){
		this.candidate = candidate;
		this.rank = rank;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public int getRank() {
		return rank;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

}
