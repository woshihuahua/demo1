package com.example.community.util;

import com.example.community.entity.User;
import org.springframework.stereotype.Component;
//用于代替session对象 持有用户信息，线程隔离
//以线程为key来取值
@Component
public class HostHolder {
    private ThreadLocal<User> users = new ThreadLocal<>();

    public void setUser(User user) {
        users.set(user);
    }

    public User getUser() {
        return users.get();
    }

    public void clear() {
        users.remove();
    }
}
