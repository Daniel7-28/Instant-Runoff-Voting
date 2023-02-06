package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import DataStructures.ArrayList;

/**
 * The Election class
 * 
 * Where we decide who wins and is our new president base on the information provided.
 * 
 * @author: Juan D. Pérez Sepúlveda
 * @version: 2.0
 * @since 2022-02-22
 * 
 */
public class Election {
	
	private final String samplesPath = "./sampleFiles/";
	private final String imputPath = "./inputFiles/";
	
	private ArrayList<Ballot> ballots;			// List of Ballots
	private ArrayList<Candidate> candidates;	// List of Candidates
	private CSVReader reader;					// CSV File Reader
	
	private int[] votesPerRank;					// Array of integer each slot representing votes of a rank
	ArrayList<int[]> rankVotesPerCandidate;		// List of Rank Votes per Candidate

	public Election() {
		
		this.ballots = new ArrayList<>();
		this.candidates = new ArrayList<>();
		
		ArrayList<ArrayList<String>> temp;
		reader = new CSVReader(samplesPath);
		
		/**
		 * READ CANDIDATES
		 */
		
		temp = reader.read("candidates.csv");
		
		// Mapping data to Objects
		
		for (int i = 0; i < temp.size(); i++)
			candidates.add(new Candidate(temp.get(i).get(0), Integer.parseInt(temp.get(i).get(1))));
		
		votesPerRank = new int[candidates.size()];
		
		/**
		 * READ BALLOTS
		 */
		temp = reader.read("ballots2.csv");
		
		// Mapping data to objects

		ArrayList<Vote> votes;
		for(int i=0; i<temp.size(); i++) {
			votes = new ArrayList<>(temp.get(i).size());
			for(int j=1; j<temp.get(i).size();j++) {

				votes.add(new Vote(candidates.get(Integer.valueOf(temp.get(i).get(j).substring(0, temp.get(i).get(j).indexOf(':')))-1), j));
			}
			ballots.add(new Ballot(Integer.valueOf(temp.get(i).get(0)),votes));
		}
		
		
	}
	
	public int amountOfBlankBallots() {
		int count = 0;
		for (int i = 0; i < ballots.size(); i++) {
			if(ballots.get(i).isBlank())
				count++;
		}
		return count;
	}

	public int getAmountOfInvalidBallots(){
		return removeInvalidBallots();
	} 
	
	public ArrayList<Ballot> getBallots(){
		return ballots;
	}
	
	public ArrayList<Candidate> getCandidates() {
		return candidates;
	}
	
	public ArrayList<int[]> getRankVotesPerCandidate(){
		return rankVotesPerCandidate;
	}
	
	
	/**
	 * 
	 * Method that populates votesPerRank
	 * @return rankVotesPerCandidate
	 */
	public ArrayList<int[]> count() {
		
		votesPerRank = new int[candidates.size()];
				
		ArrayList<int[]> countPerCandidate = new ArrayList<>(candidates.size()); // getActiveCandidates();
		
		for(int i=0; i<candidates.size(); i++)
			countPerCandidate.add(new int[candidates.size()]);// getActiveCandidates();
		
		for(int i=0; i<ballots.size(); ++i) {
			
			int[] subArr = null;
			int rankOffset = 0;
			
			for(int j=0; j < ballots.get(i).getVotes().size(); ++j) {
								
				if(!ballots.get(i).getVotes().get(j).getCandidate().isActive()) {
					rankOffset++;
					continue;
				}
				subArr = countPerCandidate.get(ballots.get(i).getVotes().get(j).getCandidate().getId()-1);
				subArr[ballots.get(i).getVotes().get(j).getRank()-1-rankOffset]++;
				
				votesPerRank[ballots.get(i).getVotes().get(j).getRank()-1-rankOffset]++;
				
			}
		}
		
		return countPerCandidate;
		
	}
	/**
	 * 
	 * @param PrintStream object used to write on a file.
	 * @return Winning Candidate
	 */
	public Candidate winner(PrintStream ps) {
		
		int round = 1;
		
		while(true) {
		
			rankVotesPerCandidate = count();
						
			//FIND CANDIDATE WITH MAX VOTES IN RANK 0
			
			int i=0; // INDEX OF MAX VOTES CANDIDATE IN RANK 0
			
			for(int k=1; k<rankVotesPerCandidate.size(); ++k) {
				if(!candidates.get(k).isActive()) continue;
				if(rankVotesPerCandidate.get(k)[0] > rankVotesPerCandidate.get(i)[0])
					i=k;
			}

			if((1.0*rankVotesPerCandidate.get(i)[0])/votesPerRank[0] > 0.5) {
				return candidates.get(i);
			}
			
			Candidate c = removeLowestCandidate();
			
			ps.println("Round " + round++ + ": " + c.getName() + " was eliminated with "
					+ rankVotesPerCandidate.get(c.getId()-1)[0] + " #1's");
			
			
		
		}
		
		
	}
	
	/**
	 * 
	 * @return New non-active Candidate used to write some of its info in a file.
	 */
	
	private Candidate removeLowestCandidate() {
		
		return removeLowestCandidateHelper(0, -1, -1);
	}
	
	/**
	 * Recursive method to set a Candidate with lowest votes of rankIndex non-active.
	 * @param rankIndex
	 * @param firstDupIndex
	 * @param secondDupIndex
	 * @return Candidate object being set non-active
	 */
	
	private Candidate removeLowestCandidateHelper(int rankIndex, int firstDupIndex, int secondDupIndex) {
		
		int minIndex = -1;
		if(rankIndex > 0) {
			if(rankIndex+1 >= candidates.size()) {
				
				//Candidate being selected is the one with higher ID
				minIndex = Math.max(firstDupIndex, secondDupIndex);
			}
			else if(rankVotesPerCandidate.get(firstDupIndex)[rankIndex] == rankVotesPerCandidate.get(secondDupIndex)[rankIndex])
				return removeLowestCandidateHelper(rankIndex + 1, firstDupIndex, secondDupIndex);
			else if(rankVotesPerCandidate.get(firstDupIndex)[rankIndex] < rankVotesPerCandidate.get(secondDupIndex)[rankIndex])
				minIndex = firstDupIndex;
			else
				minIndex = secondDupIndex;
			
		}
		else {
			
			//FIND CANDIDATE WITH MIN VOTES IN RANK rankIndex
			int minIndexOne = 0;
			int minIndexSec = -1;
			boolean duplicate = false;
			
			for(int k=1; k<candidates.size(); ++k) {
				if(!candidates.get(k).isActive()) continue;
							
				if(rankVotesPerCandidate.get(k)[rankIndex] == rankVotesPerCandidate.get(minIndexOne)[rankIndex]) {
					duplicate = true;
					minIndexSec = k;
				}
				if(rankVotesPerCandidate.get(k)[rankIndex] < rankVotesPerCandidate.get(minIndexOne)[rankIndex]) {
					minIndexOne=k;
					duplicate = false;
					minIndexSec = -1;
				}
				
			}
			// No decision can be made. Proceed with the next rank.
			if(duplicate) {
				return removeLowestCandidateHelper(rankIndex + 1, minIndexOne, minIndexSec);
			}
			minIndex = minIndexOne;
		}
		// No duplicates found. Sets the candidate to non-active.
		candidates.get(minIndex).setActive(false);
		return candidates.get(minIndex);
	}
	//Get how many ballots are in blank
	public int getBlankBallots() {
		int blanks=0;
		for(int i=0; i<ballots.size(); ++i) {
			if(ballots.get(i).isBlank())
				blanks++;
			
		}
		return blanks;
	}

	
	public int removeInvalidBallots() {
		int counter = 0;
		for (int i = 0; i < ballots.size(); i++) {
			if(ballots.get(i).isInvalid(candidates.size())) {
				ballots.remove(i--);
				counter++;
			} 
		}
		return counter;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Election cee = new Election();
		
		File file = new File("./outputFiles/results.txt");
		file.setWritable(true);
		
		PrintStream ps = new PrintStream(file);
		cee.count();
		ps.println("Number of ballots: " + cee.getBallots().size());
		ps.println("Number of blank ballots: " + cee.getBlankBallots());
		ps.println("Number of invalid ballots: " + cee.removeInvalidBallots());

		Candidate winner = cee.winner(ps);
		ps.println("Winner: " + winner.getName() + " wins with " 
		+ cee.getRankVotesPerCandidate().get(winner.getId()-1)[0] + " #1's");
		
		ps.close();
	}

}