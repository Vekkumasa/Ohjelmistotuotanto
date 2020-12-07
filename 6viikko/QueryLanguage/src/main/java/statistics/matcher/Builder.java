package statistics.matcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Builder {
    Matcher matcher;
    
    public Builder() {
        this.matcher = new All();
    }
    
    public Matcher build() {
        Matcher m = matcher;
        reset();
        return m;
    }
    
    public void reset() {
        this.matcher = new All();
    }
    
    public Builder playsIn(String team) {
        this.matcher = new And(matcher, new PlaysIn(team));
        return this;
    }
    
    public Builder hasAtLeast(int luku, String fieldName) {
        this.matcher = new And(matcher, new HasAtLeast(luku, fieldName));
        return this;
    }
    
    public Builder hasFewerThan(int luku, String fieldName) {
        this.matcher = new And(matcher, new HasFewerThan(luku, fieldName));
        return this;
    }
    
    public Builder oneOf(Matcher... matchers) {
        this.matcher = new Or(matchers);
        return this;
    } 
}