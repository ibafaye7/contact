package com.softintel.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.function.BiFunction;

public final class IntraDateUtils {

	@FunctionalInterface
	public interface TextToDateParserInterface<S, F, E, D> {
		public D apply(S s, F f, E e);
	}

	public static final TextToDateParserInterface<String, DateFormat, String, Date> textToDateParser = (source, dateFormat,
			message) -> {

		try {
			return dateFormat.parse(source);
		} catch (ParseException e) {
			throw new RuntimeException(message);
		}
	};

	public static final BiFunction<Date, DateFormat, String> dateToTextFormater = (d, formater) -> 
		formater.format(d);
}
