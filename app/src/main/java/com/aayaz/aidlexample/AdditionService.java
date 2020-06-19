package com.aayaz.aidlexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class AdditionService extends Service {
    public AdditionService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    IAdditionService.Stub mBinder = new IAdditionService.Stub() {
        @Override
        public int add(int number1, int number2) throws RemoteException {
            return number1 + number2;
        }
    };

}
