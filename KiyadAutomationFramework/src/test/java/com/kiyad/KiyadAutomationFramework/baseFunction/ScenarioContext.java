package com.kiyad.KiyadAutomationFramework.baseFunction;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
	
	private Map<String, String> scenarioData;

    public ScenarioContext() {
        scenarioData = new HashMap<>();
    }

    // Store data in the context
    public void setContext(String key, String value) {
        scenarioData.put(key, value);
    }

    // Retrieve data from the context
    public String getContext(String key) {
        return scenarioData.get(key);
    }

    // Check if data exists for the given key
    public boolean containsContext(String key) {
        return scenarioData.containsKey(key);
    }

}
