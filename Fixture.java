public class Fixture
{
	private int fixtureNumber; 
	private int homeTeamNumber;
	private int awayTeamNumber;
	
	Fixture(int fixtureNum, int homeNum, int awayNum)
	{
		fixtureNumber = fixtureNum;
		homeTeamNumber = homeNum;
		awayTeamNumber = awayNum;
	}
	
	public int getFixtureNumber()
	{
		return fixtureNumber;
	}
		
	public int getHomeTeamNumber()
	{
		return homeTeamNumber;
	}
		
	public int getAwayTeamNumber()
	{
		return awayTeamNumber;
	}
}
