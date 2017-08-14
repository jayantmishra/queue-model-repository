package com.softcell.routing.service;

import com.softcell.routing.service.Constants.RuleConstants;

public class RulesInstanceData {
	
	public int mInstanceId;
	public int mBatchSize;
	
	public RulesInstanceData(int instanceId)
	{
		mInstanceId = instanceId;
		mBatchSize = RuleConstants.BATCH_SIZE;
	}

}
