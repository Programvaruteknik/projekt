package domain.datasources;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.datasources.workers.TotalFotballGoals;

public class DataSourceFactoryTest
{

    @Test
    public void testGetExistingDataSource() {
        assertEquals(TotalFotballGoals.class, DataSourceFactory.getDataSource("TotalFotballGoals").getClass());
    }
    
    @Test
    public void testGetNonExistingStrategy() {

        assertNull(DataSourceFactory.getDataSource("Totally uselees DataSource"));
    }

}
