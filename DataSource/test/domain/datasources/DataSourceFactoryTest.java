package domain.datasources;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.datasources.workers.SunAltitudeAtNoon;

public class DataSourceFactoryTest
{

	DataSourceFactory factory = new DataSourceFactory();
    @Test
    public void testGetExistingDataSource() {
        assertEquals(SunAltitudeAtNoon.class, factory.getDataSource(new SunAltitudeAtNoon().getMetaData().getName()).getClass());
    }
    
    @Test
    public void testGetNonExistingStrategy() {

        assertNull(factory.getDataSource("Totally uselees DataSource"));
    }

}
