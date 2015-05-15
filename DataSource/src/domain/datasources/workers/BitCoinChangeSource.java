package domain.datasources.workers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.sun.net.ssl.internal.www.protocol.https.Handler;

import jdk.internal.org.objectweb.asm.Handle;
import domain.api.ApiHandler;
import domain.api.BitCoinChangeApi;
import domain.api.models.bitcoin.Change;
import domain.api.models.bitcoin.ChangeList;
import domain.api.serialization.JsonParser;
import domain.api.url.UrlFetcher;
import domain.datasources.DataSource;
import domain.datasources.model.MetaData;
import domain.datasources.workers.general.GenericBitCoinSource;

public class BitCoinChangeSource extends GenericBitCoinSource {
	private boolean isFilterd = false;

	public BitCoinChangeSource(ApiHandler handler) {
		super(handler);
		setTitle("Change In Canadian Bitcoin Index");
	}

	public BitCoinChangeSource() {
		super();
		setTitle("Change In Canadian Bitcoin Index");
	}

	@Override
	protected void loadData() {
		ChangeList list = handler.get(url, ChangeList.class);
		for(Change c : list.getChanges()){
			data.put(LocalDate.parse(c.getDate()),c.getChange());
		}
	}

}
