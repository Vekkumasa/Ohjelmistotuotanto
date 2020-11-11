
package ohtu;

public class Player implements Comparable<Player> {
    private String name;
    private int goals;
    private int assists;
    private String team;
    private int penalties;
    private String nationality;
    private String birthdate;
    private int points;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * @return the goals
     */
    public int getGoals() {
        return goals;
    }

    /**
     * @param goals the goals to set
     */
    public void setGoals(int goals) {
        this.goals = goals;
    }

    /**
     * @return the assists
     */
    public int getAssists() {
        return assists;
    }

    /**
     * @param assists the assists to set
     */
    public void setAssists(int assists) {
        this.assists = assists;
    }

    /**
     * @return the team
     */
    public String getTeam() {
        return team;
    }

    /**
     * @param team the team to set
     */
    public void setTeam(String team) {
        this.team = team;
    }

    /**
     * @return the penalties
     */
    public int getPenalties() {
        return penalties;
    }

    /**
     * @param penalties the penalties to set
     */
    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    /**
     * @return the nationality
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * @param nationality the nationality to set
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * @return the birthdate
     */
    public String getBirthdate() {
        return birthdate;
    }

    /**
     * @param birthdate the birthdate to set
     */
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
    
     /**
     * @return the points
     */
    public int getPoints() {
        return this.goals + this.assists;
    }
    
    @Override
    public String toString() {
        return this.name + " Team: " + this.team + " Nationality: " + this.nationality + " Goals: " + this.goals + " Assists: " + this.assists + " Points: " + (this.assists + this.goals);
    }
    
    @Override
    public int compareTo(Player player) {
        return player.getPoints() - this.getPoints();
    }

   
      
}
