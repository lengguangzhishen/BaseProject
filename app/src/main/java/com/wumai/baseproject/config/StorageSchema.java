package com.wumai.baseproject.config;


import com.wumai.baseproject.app.CustomerAccount;
import com.wumai.baselibrary.Storage;

/**
 * Created by litengfei on 16-3-12.
 */
public interface StorageSchema {

    Storage<CustomerAccount> ACCOUNT_STORAGE = new Storage<CustomerAccount>(CustomerAccount.class) {
        @Override
        public synchronized CustomerAccount get() {
            final CustomerAccount result = super.get();
            return result == null ? new CustomerAccount() : result;
        }
    };

    //从业缓存;
//    Storage<MainCacheLevelEntry> MAIN_CHCHE_LEVEL_ENTRY_STORAGE = new Storage<MainCacheLevelEntry>(MainCacheLevelEntry.class) {
//
//        @Override
//        public synchronized MainCacheLevelEntry get() {
//            final MainCacheLevelEntry result = super.get();
//            return result == null ? new MainCacheLevelEntry() : result;
//        }
//    };


}
