package com.stone.EasyTeaching.events;

/**
 * 事件集中定义
 * By hangli2 :使用EventBus 的Event必须使用或继承这个类！！！
 */
public class BaseEvents {

    private Object data;

    private int type;

    public Object getData() {
        return data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public BaseEvents(int type) {
        super();
        this.type = type;
    }

    public BaseEvents(int type, Object data) {
        super();
        this.type = type;
        this.data = data;
    }

}
