package me.manny.unity.utils.cooldown;

import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import me.manny.unity.utils.utilTime;
import me.manny.unity.utils.utilTime.TimeUnit;

public class Cooldown {
	 
    public static HashMap<UUID, CooldownType> cooldownPlayers = new HashMap<UUID, CooldownType>();
 
    public static void add(UUID UUID, String ability, long seconds, long systime) {
        if(!cooldownPlayers.containsKey(UUID)) cooldownPlayers.put(UUID, new CooldownType(UUID, systime, systime));
        if(isCooling(UUID, ability)) return;
        cooldownPlayers.get(UUID).cooldownMap.put(ability, new CooldownType(UUID, seconds * 1000, System.currentTimeMillis()));
    }

    public static boolean isCooling(UUID UUID, String ability) {
        if(!cooldownPlayers.containsKey(UUID)) return false;
        if(!cooldownPlayers.get(UUID).cooldownMap.containsKey(ability)) return false;
        return true;
        }
 
    public static double getRemaining(UUID key, String ability) {
        if(!cooldownPlayers.containsKey(key)) return 0.0;
        if(!cooldownPlayers.get(key).cooldownMap.containsKey(ability)) return 0.0;
        return utilTime.convert((cooldownPlayers.get(key).cooldownMap.get(ability).second + cooldownPlayers.get(key).cooldownMap.get(ability).systime) - System.currentTimeMillis(), TimeUnit.SECONDS, 1);
    }
    public static double getRemainingSeconds(UUID key, String ability) {
        if(!cooldownPlayers.containsKey(key)) return 0.0;
        if(!cooldownPlayers.get(key).cooldownMap.containsKey(ability)) return 0.0;
        return utilTime.convert((cooldownPlayers.get(key).cooldownMap.get(ability).second + cooldownPlayers.get(key).cooldownMap.get(ability).systime) - System.currentTimeMillis(), TimeUnit.SECONDS, 1);
    }
    public static double getRemainingMinutes(UUID key, String ability) {
        if(!cooldownPlayers.containsKey(key)) return 0.0;
        if(!cooldownPlayers.get(key).cooldownMap.containsKey(ability)) return 0.0;
        return utilTime.convert((cooldownPlayers.get(key).cooldownMap.get(ability).second + cooldownPlayers.get(key).cooldownMap.get(ability).systime) - System.currentTimeMillis(), TimeUnit.MINUTES, 1);
    }
    public static double getRemainingHours(UUID key, String ability) {
        if(!cooldownPlayers.containsKey(key)) return 0.0;
        if(!cooldownPlayers.get(key).cooldownMap.containsKey(ability)) return 0.0;
        return utilTime.convert((cooldownPlayers.get(key).cooldownMap.get(ability).second + cooldownPlayers.get(key).cooldownMap.get(ability).systime) - System.currentTimeMillis(), TimeUnit.HOURS, 1);
    }
    public static void removeCooldown(UUID key, String ability)
    {
      if (!cooldownPlayers.containsKey(key)) {
        return;
      }
      if (!((CooldownType)cooldownPlayers.get(key)).cooldownMap.containsKey(ability)) {
        return;
      }
      ((CooldownType)cooldownPlayers.get(key)).cooldownMap.remove(ability);

      if (key != null) {
    
      }
    }
 
    public static void handleCooldowns() {
        if(cooldownPlayers.isEmpty()) {
            return;
        }
        for(Iterator<UUID> it = cooldownPlayers.keySet().iterator(); it.hasNext();) {
            UUID key = it.next();
            for(Iterator<String> iter = cooldownPlayers.get(key).cooldownMap.keySet().iterator(); iter.hasNext();) {
                String name = iter.next();
                if(getRemaining(key, name) <= 0.0) {
                	
                    removeCooldown(key, name);
            }
            }
        }
    }

}
