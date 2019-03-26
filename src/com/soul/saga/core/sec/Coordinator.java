package com.soul.saga.core.sec;

import java.util.Map;
import java.util.UUID;

public class Coordinator {

	Map<UUID,DistibutedTransactionTracker> tracker;
}
