package domain.datasources;

public class DataSourceFactory
{
    public DataSource getDataSource(String id) {
        try {
            return (DataSource) Class.forName("domain.datasources.workers." + id).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
        	return null;
        }
    }
}
