package com.spring.project.exodia.utils;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

public class UuIdUserGenerator extends SequenceStyleGenerator {
	public static final String USER_VALUE_PREFIX_PARAMETER = "USR_";
	public static final String USER_VALUE_PREFIX_DEFAULT = "";
	public static final String USER_NUMBER_FORMAT_PARAMETER = "numberFormat";
	public static final String USER_NUMBER_FORMAT_DEFAULT = "%d";
	private String valuePrefix;
	private String numberFormat;

	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) {
		super.configure(LongType.INSTANCE, params, serviceRegistry);
		valuePrefix = ConfigurationHelper.getString(USER_VALUE_PREFIX_PARAMETER, params, USER_VALUE_PREFIX_DEFAULT);
		numberFormat =ConfigurationHelper.getString(USER_NUMBER_FORMAT_PARAMETER,params,USER_NUMBER_FORMAT_DEFAULT);
	}

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) {
		return USER_VALUE_PREFIX_PARAMETER + String.format(numberFormat, super.generate(session, object));
		
	}
}
