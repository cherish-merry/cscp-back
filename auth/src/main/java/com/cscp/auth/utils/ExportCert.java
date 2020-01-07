package com.cscp.auth.utils;

import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.security.*;
import java.security.cert.Certificate;

/**
 * @author zhen
 * @Date 2018/4/16 10:58
 */
public class ExportCert {

    //导出证书 base64格式
    public static void exportCert(KeyStore keyStore, String alias, String exportFile) throws Exception {
        Certificate certificate = keyStore.getCertificate(alias);
        BASE64Encoder encoder = new BASE64Encoder();
        String encoded = encoder.encode(certificate.getEncoded());
        FileWriter fw = new FileWriter(exportFile);
        fw.write("------Begin Certificate----- \r\n ");//非必须
        fw.write(encoded);
        fw.write("\r\n-----End Certificate-----");//非必须
        fw.close();
    }

    //得到KeyPair
    public static KeyPair getKeyPair(KeyStore keyStore, String alias, char[] password){
        try{
            Key key = keyStore.getKey(alias, password);
            if (key instanceof PrivateKey){
                Certificate certificate = keyStore.getCertificate(alias);
                PublicKey publicKey = certificate.getPublicKey();
                return new KeyPair(publicKey, (PrivateKey) key);
            }
        }catch (UnrecoverableKeyException | NoSuchAlgorithmException | KeyStoreException e){
            e.printStackTrace();
        }
        return null;
    }

    //导出私钥
    public static void exportPrivateKey(PrivateKey privateKey, String exportFile) throws Exception {
        BASE64Encoder encoder = new BASE64Encoder();
        String encoded = encoder.encode(privateKey.getEncoded());
        FileWriter fileWriter = new FileWriter(exportFile);
        fileWriter.write("-----Begin Private Key-----\r\n");//非必须
        fileWriter.write(encoded);
        fileWriter.write("\r\n-----End Private Key-----");//非必须
        fileWriter.close();
    }

    //导出公钥
    public static void exportPublicKey(PublicKey publicKey, String exportFile) throws Exception {
        BASE64Encoder encoder = new BASE64Encoder();
        String encoded = encoder.encode(publicKey.getEncoded());
        FileWriter fileWriter = new FileWriter(exportFile);
        fileWriter.write("-----Begin Public Key-----\r\n");//非必须
        fileWriter.write(encoded);
        fileWriter.write("\r\n-----End Public Key-----");//非必须
        fileWriter.close();
    }

    public static void main(String[] args) throws Exception{
        String keyStoreType = "jks";
        String keystoreFile = "C:\\Users\\陈可卓\\Desktop\\jwt\\colstu.keystore";
        String password = "ckz123123"; //keystore的解析密码
        String friendPassword = "19990424";//条目的解析密码

        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        keyStore.load(new FileInputStream(keystoreFile), password.toCharArray());

        String alias = "colstu-keypair";//条目别名
        String exportCertFile = "C:\\Users\\陈可卓\\Desktop\\jwt\\cert.txt";
        String exportPrivateFile = "C:\\Users\\陈可卓\\Desktop\\jwt\\private-key.txt";
        String exportPublicFile = "C:\\Users\\陈可卓\\Desktop\\jwt\\public-key.txt";

        ExportCert.exportCert(keyStore, alias, exportCertFile);
        KeyPair keyPair = ExportCert.getKeyPair(keyStore, alias, friendPassword.toCharArray()); //注意这里的密码是你的别名对应的密码，不指定的话就是你的keystore的解析密码
        ExportCert.exportPrivateKey(keyPair.getPrivate(), exportPrivateFile);
        ExportCert.exportPublicKey(keyPair.getPublic(), exportPublicFile);

        System.out.println("OK");

    }


}