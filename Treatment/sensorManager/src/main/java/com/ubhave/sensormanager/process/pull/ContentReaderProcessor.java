package com.ubhave.sensormanager.process.pull;

import android.content.Context;

import com.ubhave.sensormanager.config.SensorConfig;
import com.ubhave.sensormanager.data.pullsensor.AbstractContentReaderEntry;
import com.ubhave.sensormanager.data.pullsensor.AbstractContentReaderListData;
import com.ubhave.sensormanager.process.CommunicationProcessor;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class ContentReaderProcessor extends CommunicationProcessor
{
	public ContentReaderProcessor(final Context c, boolean rw, boolean sp)
	{
		super(c, rw, sp);
	}

	public AbstractContentReaderListData process(long pullSenseStartTimestamp, int sensorType, ArrayList<HashMap<String, String>> contentList, SensorConfig sensorConfig)
	{
		AbstractContentReaderListData contentData = getData(pullSenseStartTimestamp, sensorConfig);
		if (setRawData)
		{
			for (HashMap<String, String> map : contentList)
			{
				AbstractContentReaderEntry entry = getEntry(map);
				if (entry != null)
				{
					contentData.addContent(entry);
				}
			}
		}
		return contentData;
	}
	
	protected abstract AbstractContentReaderListData getData(long senseStartTime, SensorConfig config);
	
	protected abstract AbstractContentReaderEntry getEntry(final HashMap<String, String> map);
}
