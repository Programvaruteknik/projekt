package domain.jersey;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import domain.datasources.DataSource;
import domain.datasources.DataSourceFactory;
import domain.datasources.model.SourceMetaData;

public class TestGetMetadata {

	@Test
	public void testGetMetaData() {
		String expectedUrl = "url";
		String expectedOwner = "owner";
		String expectedLicense = "license";

		DataSource sc = mock(DataSource.class);
		SourceMetaData metaMock = mock(SourceMetaData.class);

		when(metaMock.getUrl()).thenReturn("url");
		when(metaMock.getOwner()).thenReturn("owner");
		when(metaMock.getLicense()).thenReturn("license");
		
		when(sc.getMetaData()).thenReturn(metaMock);

		DataSourceFactory factory = mock(DataSourceFactory.class);
		when(factory.getDataSource("metaSource")).thenReturn(sc);

		DataSourceAPI api = new DataSourceAPI();
		api.setFactory(factory);
		
		SourceMetaData meta = api.getMetaData("metaSource");
		assertNotNull(meta);
		assertEquals(expectedUrl, meta.getUrl());
		assertEquals(expectedOwner, meta.getOwner());
		assertEquals(expectedLicense, meta.getLicense());

	}

}
