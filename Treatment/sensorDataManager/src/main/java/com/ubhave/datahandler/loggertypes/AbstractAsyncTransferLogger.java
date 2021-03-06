package com.ubhave.datahandler.loggertypes;

import android.content.Context;

import com.ubhave.datahandler.config.DataStorageConfig;
import com.ubhave.datahandler.config.DataTransferConfig;
import com.ubhave.datahandler.except.DataHandlerException;

public abstract class AbstractAsyncTransferLogger extends AbstractTransferLogger
{

	protected AbstractAsyncTransferLogger(Context context)
	{
		super(context);
	}

	@Override
	protected void configureDataStorage()
	{
		super.configureDataStorage();
		try
		{
			dataManager.setConfig(DataTransferConfig.DATA_TRANSER_POLICY, DataTransferConfig.TRANSFER_PERIODICALLY);
			dataManager.setConfig(DataStorageConfig.FILE_LIFE_MILLIS, getFileLifeMillis());
			dataManager.setConfig(DataTransferConfig.TRANSFER_ALARM_INTERVAL, getTransferAlarmLengthMillis());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	protected abstract long getFileLifeMillis();

	protected abstract long getTransferAlarmLengthMillis();
	
	public void flush() throws DataHandlerException
	{
		dataManager.postAllStoredData();
	}
}
