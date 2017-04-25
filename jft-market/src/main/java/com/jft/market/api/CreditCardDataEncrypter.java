package com.jft.market.api;


import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Base64;

public class CreditCardDataEncrypter implements DataEncryptor {
	private static final Charset UTF8 = Charset.forName("UTF-8");
	private static SecretKeyFactory ourKeyFactory;
	private static final PBEParameterSpec ourPBEParameters = new PBEParameterSpec(new byte[]{(byte) 60, (byte) 21, (byte) 39, (byte) 127, (byte) 45, (byte) -38, (byte) -26, (byte) 100}, 15);
	protected boolean useHexForBinary = true;
	private final SecretKey mySecretKey;
	public static final String[] hexLookupTable = new String[]{"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "0a", "0b", "0c", "0d", "0e", "0f", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "1a", "1b", "1c", "1d", "1e", "1f", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "2a", "2b", "2c", "2d", "2e", "2f", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "3a", "3b", "3c", "3d", "3e", "3f", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "4a", "4b", "4c", "4d", "4e", "4f", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "5a", "5b", "5c", "5d", "5e", "5f", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "6a", "6b", "6c", "6d", "6e", "6f", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "7a", "7b", "7c", "7d", "7e", "7f", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "8a", "8b", "8c", "8d", "8e", "8f", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "9a", "9b", "9c", "9d", "9e", "9f", "a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9", "aa", "ab", "ac", "ad", "ae", "af", "b0", "b1", "b2", "b3", "b4", "b5", "b6", "b7", "b8", "b9", "ba", "bb", "bc", "bd", "be", "bf", "c0", "c1", "c2", "c3", "c4", "c5", "c6", "c7", "c8", "c9", "ca", "cb", "cc", "cd", "ce", "cf", "d0", "d1", "d2", "d3", "d4", "d5", "d6", "d7", "d8", "d9", "da", "db", "dc", "dd", "de", "df", "e0", "e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "ea", "eb", "ec", "ed", "ee", "ef", "f0", "f1", "f2", "f3", "f4", "f5", "f6", "f7", "f8", "f9", "fa", "fb", "fc", "fd", "fe", "ff"};


	public CreditCardDataEncrypter(String password) throws InvalidKeySpecException {
		PBEKeySpec spec = new PBEKeySpec(password.toCharArray());
		SecretKeyFactory keyFactory = getSecretKeyFactory();
		this.mySecretKey = keyFactory.generateSecret(spec);
	}

	public String decrypt(String encrypted) {
		Cipher cipher = getCipher();
		byte[] crypted = this.useHexForBinary ? stringToBinary(encrypted) : Base64.decodeBase64(encrypted.getBytes());

		byte[] result;
		try {
			cipher.init(2, this.mySecretKey, ourPBEParameters);
			result = cipher.doFinal(crypted);
		} catch (GeneralSecurityException var6) {
			throw new IllegalArgumentException("Can not decrypt:" + encrypted, var6);
		}

		return new String(result, UTF8);
	}

	public String encrypt(String plainText) {
		Cipher cipher = getCipher();

		byte[] crypted;
		try {
			cipher.init(1, this.mySecretKey, ourPBEParameters);
			byte[] cryptedText = plainText.getBytes(UTF8);
			crypted = cipher.doFinal(cryptedText);
		} catch (GeneralSecurityException var5) {
			throw new IllegalArgumentException("Can not encrypt :" + plainText, var5);
		}

		String cryptedText1 = this.useHexForBinary ? binaryToString(crypted) : new String(Base64.encodeBase64(crypted));
		return cryptedText1;
	}

	public static String binaryToString(byte[] string) {
		int readBytes = string.length;
		StringBuffer hexData = new StringBuffer();

		for (int i = 0; i < readBytes; ++i) {
			hexData.append(hexLookupTable[255 & string[i]]);
		}

		return hexData.toString();
	}

	private static Cipher getCipher() {
		try {
			Cipher ourCipher = Cipher.getInstance("PBEWithMD5AndDES");
			return ourCipher;
		} catch (NoSuchAlgorithmException var2) {
			throw new IllegalStateException("Strange. Algorithm was supported a few seconds ago : PBEWithMD5AndDES", var2);
		} catch (NoSuchPaddingException var3) {
			throw new IllegalStateException(var3);
		}
	}

	private static SecretKeyFactory getSecretKeyFactory() {
		if (ourKeyFactory == null) {
			try {
				ourKeyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
			} catch (NoSuchAlgorithmException var1) {
				throw new IllegalStateException("Algorithm is not supported: PBEWithMD5AndDES", var1);
			}
		}

		return ourKeyFactory;
	}

	public static byte[] stringToBinary(String string) {
		byte[] retValue = new byte[string.length() / 2];

		for (int i = 0; i < retValue.length; ++i) {
			String digit = string.substring(i * 2, i * 2 + 2);
			int hex = Integer.parseInt(digit, 16);
			byte by = (byte) hex;
			retValue[i] = by;
		}

		return retValue;
	}
}
