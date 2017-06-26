package com.lefu.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class BaseService {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

}
