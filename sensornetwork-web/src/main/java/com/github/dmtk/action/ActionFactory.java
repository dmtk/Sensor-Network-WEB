package com.github.dmtk.action;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {

    /**
     * Map of actions
     */
    private static Map<String, Class> map = defaultMap();

    /**
     * Creates instance of Action
     *
     * @return instance of Action
     */
    public static Action create(String actionName) {

        Class klass = (Class) map.get(actionName);
        if (klass == null) {
            throw new RuntimeException("Was unable to find an action named '" + actionName + "'.");
        }

        Action actionInstance = null;
        try {
            actionInstance = (Action) klass.newInstance();
        } catch (Exception e) {
            RuntimeException e1 = new RuntimeException(e);
            throw e1;
        }

        return actionInstance;
    }

    private static Map<String, Class> defaultMap() {

        Map<String, Class> map = new HashMap<String, Class>();

        map.put("index.htm", BootstrapAction.class);
        map.put("authenticate", LoginAction.class);
        map.put("about", AboutAction.class);
        map.put("overview", OverviewAction.class);
        map.put("reports", ReportsAction.class);
        map.put("graphics", GraphicsAction.class);
        map.put("map", MapAction.class);
        
        map.put("settings", SettingsAction.class);
        map.put("export", ExportAction.class);
        map.put("rawdata", RealtimeDataAction.class);
        
        return map;
    }
}
