package me.manny.unity.utils.cooldown;

import java.util.HashMap;
import java.util.UUID;
 
public class CooldownType {
 
    public String ability = "";
    public UUID player;
    public long second;
    public long systime;
 
    public HashMap<String, CooldownType> cooldownMap = new HashMap<String, CooldownType>();
 
    public CooldownType(UUID UUID, long seconds, long systime) {
        this.player = UUID;
        this.second = seconds;
        this.systime = systime;
    }




 


}