package utils;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class WalletInterface {

    public final String WALLET_PATH = "/Users/angel/Dev/ethereum_java_test/ethereumjavatest/src/main/resources/wallet";

    private static WalletInterface instance;

    public static WalletInterface getInstance(){
        if(instance == null){
            instance = new WalletInterface();
        }
        return instance;
    }

    public String[] createNewWallet(String pwd) throws Exception{

        try{

            File file = new File(this.WALLET_PATH);
            file.createNewFile();
            if(file.exists()){
                String name = org.web3j.crypto.WalletUtils.generateFullNewWalletFile(pwd,file);
                Path path = FileSystems.getDefault().getPath(this.WALLET_PATH,name);
                return new String[] {name, new String(Files.readAllBytes(path))};
            }

        }catch(Exception ex){
            throw ex;
        }

        return null;

    }//createNewWallet
}
