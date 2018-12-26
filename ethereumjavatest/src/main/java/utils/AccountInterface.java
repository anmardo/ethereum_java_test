package utils;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

public class AccountInterface {

    private static AccountInterface instance;

    private Web3j web3j;

    public static AccountInterface getInstance() {
        if (instance == null) {
            instance = new AccountInterface();
            instance.web3j = Web3j.build(new HttpService());
        }
        return instance;
    }


    public Credentials openWallet(String password, String walletName) {

        Credentials credentials = null;
        try {
            credentials = WalletUtils.loadCredentials(password, WalletInterface.getInstance().WALLET_PATH + File.separator + walletName);
        } catch (IOException | CipherException e) {
            e.printStackTrace();
        }
        return credentials;

    }

    public BigInteger getAddressBalance(String address) {
        try {
            EthGetBalance ethGetBalance = this.web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).sendAsync().get();
            return ethGetBalance.getBalance();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }


}
