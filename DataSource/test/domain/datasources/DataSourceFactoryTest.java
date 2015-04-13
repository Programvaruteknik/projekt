package domain.datasources;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.datasources.workers.TotalFotballGoals;

public class DataSourceFactoryTest
{

	DataSourceFactory factory = new DataSourceFactory();
    @Test
    public void testGetExistingDataSource() {
        assertEquals(TotalFotballGoals.class, factory.getDataSource("TotalFotballGoals").getClass());
    }
    
    @Test
    public void testGetNonExistingStrategy() {

        assertNull(factory.getDataSource("Totally uselees DataSource"));
    }

}
