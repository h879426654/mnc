package com.basics.support.auth;

import com.alibaba.fastjson.JSONObject;

public class BankAuthUtil {
	// 接口调用地址
	private static String url = "https://kh_bd.253.com/openApi/";
	// 3des密钥
	private static String des_key = "8523@@abcd8523@@abcd##&&";
	// rsa客户私钥
	private static String rsa_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCJVydvlZdCNeJTHowjyulYSpFxZ+CpFfEt4FfJzfptzVc0wjwx/JlVV2uN0hM7qHBWiGonjhAVZX8mcebXXn4jxUJRe7+ieDc4OoCuCEePlIvFtNHAvVi9UmyzLzcfmXHfVkrIykjQvBUkOoeeeyfE8Gi+TDPv9Lg5B1sNA7hsndSDFAEGP5eK7lSnCQBU8Ccu59ySggQvz2ANdIaWA0QURqIYYuKharaHvKEqWE/WKqThabQDCOtQagBhdynjW+wPqoryTZswccEentGdmqhqFJaGiO30EHtIAyRxE6euW2u0t89iihLIxB8sdb8/gSEva/yzR0wKaIK9G6sGyotpAgMBAAECggEALLSvBJaIHyhVHMNj0L7bSD81qtmqer/Guy08xlcDPrzJt0XGvGrCKtKDEy4fzpqcvr4299s5hw173zLSdqaunsw2Mzn5/lPtfaMlggD18lnjSZ77bCb2fOIYuhcTdXjIZW+8djHKlA+1Pg7DWKY0Itoy7kb13RUm5oFrdQgR/2DAe/iKK4NbFdiGufKEbyyeCb0xZfoC8qHY9dLWdbPgxnRcfSAeb7QRrU/OLmifGVE2lM8Z9nc55nLm6UiVj82hW40mJwl/4ivRxfw5iyE+xJm6EaNEoQMy0Rc0zJECxps/yEhfeK90fNBQd+B7Oq+rlav+uwO8U6FIk+c3HZmaAQKBgQDkRMsinL2T36UCPA9LBk0+nFovuI4p94kqnwqw/4mM5togAf5b6KnB/ZWnJru8+iDVgOp/38wLNP7DB4qQav1eYNTIyuI8oR/Xp6zHKNxDKhwNIOzozYEBqDMRNxM8k3VkdohFHTIL8ykyMs3wXJBpwfuTgN3d0ioeYZU0IYSYKQKBgQCaBnizKa8/c5w2XiTwuUsc0SfXB64rEcGQQ8FTBO7hL6Rq7uqxkILTYOnwEDnm6+w+KZNqKu16UDvuO1d/v8e4nhCNBuQdwDNGsklS6K4ZuPmMdu8Fr/DPh7dVAJhTG/pvmic9K0Geyh+VmXyR+LW4aEvLJHAicjWU1gaQ80HBQQKBgQCyydsdIg0ufDXe+TG1PptT1dyhkfjvj+1Ej8ss9QlEbjAcb9NNI3+K7NbBVAopqvP6pf2F6MEFah28nfR+xv3qZQdkudvXRxAMtk0StMNIa/wKoGZOtV888AQHkM6lXI3PATQchhCD4ZG7uqUohSerXf9w+bdNHWZV43KcoUAceQKBgGCIQcl4DJ+l43enlVtRpiPPajq4U44muLuj21wesWBsrY1fY7QZsASurq+IW+HAZvWmtP9LHD8WXhk3E+W62n94gUMB2KJUvU5HmvDdZ5AzgCNqvu8/j5thoaMillUwKcscQA90NtJAN39ZDNunlqyWoToWAjl0fuRjJwZdjw6BAoGBAJazPEVB6ZVq6WKl9RbtbQ9eWpxZNE65ONHO5IrpUHofZVy//RR6wobQRH6TqVHC6/nWl64eKT/fEnbVxd4kXuu4soBGEgNvYYb6dpqvZ74u6Ar6RKgpnxzxbw83gam9hFjU3izDA8LqrJe1y6umDhfdRYvTIBvc0b2yhugNOwyK";
	// 客户帐号
	private static String apiName = "S3214209";
	// 客户密码
	private static String password = "pwd1839143";

	public static void main(String[] args) throws Exception {
		JSONObject param = new JSONObject();
		param.put("userName", "苏跃");
		param.put("idno", "152327197901081819");
		param.put("mobile", "17681240848");
		param.put("cardno", "6222621512412681626");
		String code = bankAuthTest(param);
		System.out.println("code:" + code);
		// mobileThreeAuthTest(param);
		JSONObject result = JSONObject.parseObject(code);
		System.out.println("resultCode" + result.getString("resultCode"));
		System.out.println("resultObj" + result.getString("resultObj"));
		System.out.println("result" + result.getJSONObject("resultObj").getString("result"));
	}

	public static String bankAuthTest(JSONObject paramString) throws Exception {
		// 接口调用地址
		String urlstr = url + "bankAuth";
		// 订单号
		String order_no = SerialNumberUtil.createBillNo();
		// 参数3DES加密生成密文
		byte[] requsest = ThreeDesUtil.encryptMode(des_key.getBytes(), paramString.toJSONString().getBytes());
		String paramStr = ThreeDesUtil.byte2Hex(requsest);
		JSONObject paramJson = new JSONObject();
		paramJson.put("apiName", apiName);
		paramJson.put("password", password);
		paramJson.put("order_no", order_no);
		paramJson.put("paramString", paramStr);
		// 生产参数串,参数串需要以参数名的首字母顺序排序,如首字母一样,则往后推一个字母顺序排序，以此类推
		StringBuilder builder = new StringBuilder();
		builder.append("apiName=".toUpperCase() + apiName);
		builder.append("&order_no=".toUpperCase() + order_no);
		builder.append("&paramString=".toUpperCase() + paramStr);
		builder.append("&password=".toUpperCase() + password);

		// rsa数据加密
		String sign = RsaSignature.rsaSign(builder.toString(), rsa_key, AuthConstants.CHARSET_UTF8);
		paramJson.put("sign", sign);

		System.out.println("参数信息:" + paramJson.toString());
		System.out.println("发送报文信息:" + builder.toString());
		// 调用接口
		return HttpClientUtils.postJSON(urlstr, paramJson.toJSONString());
	}

}
