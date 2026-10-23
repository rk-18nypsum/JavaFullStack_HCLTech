package com.hcl.library.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SearchCache {
    private static final int MAX_SIZE = 5;

    private final LinkedHashMap<String, String> cache = new LinkedHashMap<String, String>(16, 0.75f, true) {
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
            return size() > MAX_SIZE;
        }
    };

    public synchronized void recordSearch(String query) {
        if (query != null && !query.trim().isEmpty()) {
            cache.put(query.trim().toLowerCase(), query.trim());
        }
    }

    public synchronized List<String> getRecentSearches() {
        return Collections.unmodifiableList(new ArrayList<>(cache.values()));
    }
}
