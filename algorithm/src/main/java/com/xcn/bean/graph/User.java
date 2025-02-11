package com.xcn.bean.graph;

import java.lang.ref.WeakReference;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class User extends WeakReference<String> {
    public User(String referent) {
        super(referent);
    }
}
