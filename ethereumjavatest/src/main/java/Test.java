import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import utils.AccountInterface;
import utils.WalletInterface;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;

public class Test {

    static ObjectMapper mapper = new ObjectMapper();

    private static String walletPwd = "12345";
    private static String walletName = "";

    public static void main (String [] args ) throws Exception{
        String[] walletInfo = WalletInterface.getInstance().createNewWallet(walletPwd);
        //log
        log("wallet info:");
        Arrays.asList(walletInfo).stream().forEach(s->log(getPrettyJson(s)));
        walletName = walletInfo[0];
        Credentials credentials = AccountInterface.getInstance().openWallet(walletPwd, walletName);
        log("account address:");
        log(credentials.getAddress());
        BigInteger wei = AccountInterface.getInstance().getAddressBalance(credentials.getAddress());
        log(wei + " wei");
    }

    private static String getPrettyJson(String uglyJson){
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(uglyJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void log(String s){
        System.out.println(s);
    }



}
