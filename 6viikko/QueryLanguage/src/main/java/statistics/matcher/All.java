package statistics.matcher;

import java.lang.reflect.Method;
import statistics.Player;

public class All implements Matcher {
    
    int value;
    String fieldName;
    
    public All() {
        this.value = 0;
        this.fieldName = "getGoals";
    }

    public boolean matches(Player p) {
        try {                                    
            Method method = p.getClass().getMethod(fieldName);
            int playersValue = (Integer)method.invoke(p);
            return playersValue >= value;
            
        } catch (Exception ex) {
            System.out.println(ex);
            throw new IllegalStateException("Player does not have field "+fieldName.substring(3, fieldName.length()).toLowerCase());
        }     
    }
}
