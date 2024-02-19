/*
 * Copyright (c)  2018. houbinbin Inc.
 * rate-tryAcquire All rights reserved.
 */

package com.githhub.codeman.api.core;


public interface RadRateLimit {

    boolean tryAcquire(final RadRateLimitContext context);


}
