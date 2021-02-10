package com.lmk.core.web.monitor.dto;

import com.lmk.core.web.monitor.annotation.BaseLog;

/**
 * 日志信息
 * @author LaoMake
 * @email laomake@hotmail.com
 */
public class LogParams {

    /** 日志类型：0.后台用户操作，1.前台用户操作，2.系统自动 */
    private int type;

    /** 日志级别：debug、info、warn、error */
    private String level;

    /** 日志行为 */
    private String action;

    /** 操作结果 */
    private int result;

    /** 目标资源 */
    private String resource;

    /** 详情描述 */
    private String content;

    /** 是否保存到数据库 */
    private boolean toDb;

    public LogParams() {
    }

    public LogParams(BaseLog baseLog) {
        this.type = baseLog.type().getValue();
        this.level = baseLog.level().toString();
        this.action = baseLog.action().toString();
        this.resource = baseLog.resource();
        this.content = baseLog.content();
        this.toDb = baseLog.toDb();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getToDb() {
        return toDb;
    }

    public void setToDb(boolean toDb) {
        this.toDb = toDb;
    }

    @Override
    public String toString() {
        return "LogParams{" +
                "type=" + type +
                ", level='" + level + '\'' +
                ", resource='" + resource + '\'' +
                ", action='" + action + '\'' +
                ", result='" + result + '\'' +
                ", content='" + content + '\'' +
                ", toDb=" + toDb + '}';
    }
}
