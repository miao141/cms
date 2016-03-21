package db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class ThreadLocalKeyRoutingDataSource extends AbstractRoutingDataSource{

    public static final ThreadLocal<String> _DATA_SOURCE_KEY = new ThreadLocal<String>();
    
    @Override
    public Object determineCurrentLookupKey() {
        return _DATA_SOURCE_KEY.get();
    }
    
}
