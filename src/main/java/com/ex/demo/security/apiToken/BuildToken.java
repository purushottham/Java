package com.ex.demo.security.apiToken;

import java.util.HashMap;
import java.util.Map;

public class BuildToken {
	static Map<String, String> content;

	public BuildToken() {
	}

	public static Map<String, String> getContnet() {
		if (content == null) {
			content = new HashMap<String, String>();
			content.put("any", "e79b1c21-8013-407b-8e34-98d47af49eb2");
			content.put("100001", "563b6bc4-f31a-40b2-bbd2-c475c7a375bd");
			content.put("100002", "a33dcad9-8d66-4b7d-890f-99d92c6491c2");
			content.put("100003", "c0455fe9-3106-4a7a-b347-eb4fd2c6d166");
			content.put("100004", "2d8bbc22-0106-40c3-819f-0cebacffbc19");

			content.put("100005", "6318f8ae-ff59-46a7-8e78-7599f91971f5");
			content.put("100006", "50a63c32-09bc-4202-b526-95d80c6bb1e2");
			content.put("100007", "06778cfd-db6f-49f2-af9b-13f0e14ac24f ");
			content.put("100008", "91697fc2-4c18-42b4-bf00-2289de213d13");
		}
		return content;
	}

	public static void setContnet(Map<String, String> contnet) {
		BuildToken.content = contnet;
	}

}
