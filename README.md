# Description
## Introduction
It’s election year in the country of Poor Harbor and changes are being considered for the voting
system. The current system allows for the candidate with the highest amount of votes to be
declared as the winner. However, in the last two elections the winners received less than 50% of
the votes, causing citizens to question whether it was legitimate to have a president that was
rejected by more than half of the voters. The lawmakers of Poor Harbor have since decided to
implement an alternate voting method called Instant Runoff Voting.[CIIC4020 Project 1 - Instant Runoff Voting.pdf](https://github.com/Daniel7-28/Instant-Runoff-Voting/files/10636873/CIIC4020.Project.1.-.Instant.Runoff.Voting.pdf)

Using this alternate voting method, if there are n candidates running for a particular position,
voters can vote for all n candidates, giving each candidate a rank of preference, from 1 to n. In
general, an election may include several elected positions, with several candidates running for
each position, and several cast ballots. However, we’ll focus on an election for a single position.
You will write a program that is able to receive data about the candidates for the position and
votes cast in the election, then process the votes to determine the final results based on the
counting rules that we are about to discuss.
Some people might claim that the new voting system exhibits higher fairness than the current
one, while others might disagree. No voting system is perfect, but it is outside the scope of this
project to discuss all of the pros and cons of these systems in particular

## Valid ballots
In each ballot voters can:
• vote for any number of candidates from 0 (blank ballot) up to n, where n is the number of
candidates running for that particular position.
• rank each candidate according to his/her preference, assigning the value 1 to the highest
preference, 2 to the second-highest preference, and so on, with a higher value indicating
a less-preferred candidate.
• no ranking value can be repeated in a ballot; otherwise, the ballot is invalid.
• no candidate can have a ranking vote greater than the number of candidates: k n, where
k is the rank of the candidate and n is the number of candidates. If a ranking value is
greater than n is given, the ballot is also considered invalid.

## Counting rules
The counting process may consist of several rounds or iterations. In each round, if one candidate
has more than 50% of 1’s, that candidate wins the election for that position and the counting
process concludes. Otherwise, the candidate having the lowest amount of 1’s is eliminated and
will not be considered in the subsequent counting rounds.
If there is a tie for lowest amount of 1’s, then the decision is based on the lowest amount of 2’s
(between those that tied for the lowest amount of 1’s). If again there is a tie, then the decision
is based on the lowest amount of 3’s (between those that tied for the lowest amount of 2’s),
and so on. If at the end no decision can be made, then, among all of those candidates that are
still tied, the onehaving the current largest ID# is removed.
When a candidate is eliminated, the counting for the next round must take into account the
voters’ preferences. If the #1 candidate in a ballot is eliminated, then the higher-ranked
candidates are “moved up” a position, so that the #2 candidate now becomes #1, the #3
candidate now becomes #2, and so on. In general, if the candidate being eliminated was given a
rank value of k in a ballot, then all candidates on that ballot with a rank greater than k will have
their rank subtracted by 1, hence giving them a lower rank (higher preference).

More details check de pdf.
