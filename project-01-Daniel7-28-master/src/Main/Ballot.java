package Main;

import java.util.Iterator;
import DataStructures.*;

/**
 * The Ballot
 * 
 * Stores all the votes casted by one person who votes in the Poor Harbor Election.
 * 
 * @author: Juan D. Pérez Sepúlveda
 * @version: 2.0
 * @since: 2022-02-22
 */
public class Ballot implements BaseBallot {

	private int id;	
	private ArrayList<Vote> votes = new ArrayList<>();

	public Ballot(int id, ArrayList<Vote> votes) {
		this.id = id;
		this.votes = votes;
	}

	@Override
	public int getBallotNum() {
		return this.id;
	}

	@Override
	public int getRankByCandidate(int candidateID) {
		for(int i=0; i<votes.size(); i++ ) {
			if(votes.get(i).getCandidate().getId() == candidateID) {
				return votes.get(i).getRank();
			}
		}
		return -1;
	}

	@Override
	public int getCandidateByRank(int rank) {
		for(int i=0; i<votes.size(); i++ ) {
			if(votes.get(i).getRank() == rank) {
				return votes.get(i).getCandidate().getId();
			}
		}
		return -1;
	}

	@Override
	public boolean eliminate(int candidateId) {
		for(int i=0; i<votes.size(); i++ ) {
			if(votes.get(i).getCandidate().getId() == candidateId) {
				votes.remove(i);
				return true;
			}
		}
		return false;
	}

	public ArrayList<Vote> getVotes()	{	
		return votes;
	}

	public boolean isBlank() {
		return votes.size() == 0;
	}

		//Checks is the ballots are invalid
	public boolean isInvalid(int maxCandidates) {

		return isBlank() || invalidRanks(maxCandidates) || repeatedRanks() || repeatedCandidates();

	}
	//Checks if a rank is invalid
	private boolean invalidRanks(int max) {
		for(int i=0; i<votes.size(); ++i)
			if(votes.get(i).getRank() > max || votes.get(i).getRank() != i+1) return true;
		return false;
	}

		//Checks if a candidate is repeated
	private boolean repeatedCandidates() {
		int id;
		for(int i=0; i<votes.size()-1; ++i) {
			id = votes.get(i).getCandidate().getId();
			for(int j=i+1; j<votes.size(); ++j) {
				if(votes.get(j).getCandidate().getId() == id)
					return true;
			}
		}
		return false;
	}
	//Checks if a rank is repeated
	private boolean repeatedRanks() {
		int rank;
		for(int i=0; i<votes.size()-1; ++i) {
			rank = votes.get(i).getRank();
			for(int j=i+1; j<votes.size(); ++j) {
				if(votes.get(j).getRank() == rank)
					return true;
			}
		}
		return false;
	}
	
}
