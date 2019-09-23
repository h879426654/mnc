package com.basics.support;

import com.google.gson.Gson;

public class GsonUtil {

	private static Gson sGson;

	public static Gson getGson() {
		if (sGson == null) {
			sGson = new Gson();
		}
		return sGson;
	}

	public static String getJsonString(Object o) {
		return GsonUtil.getGson().toJson(o);
	}

	public static <T> T parseJsonWithGson(String jsonData, Class<T> type) {
		Gson gson = new Gson();
		T result = gson.fromJson(jsonData, type);
		return result;
	}
}
