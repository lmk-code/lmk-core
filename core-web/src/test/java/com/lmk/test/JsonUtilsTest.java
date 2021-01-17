package com.lmk.test;

import java.util.List;
import org.junit.jupiter.api.Test;
import com.lmk.core.web.support.bean.db.Search;
import com.lmk.core.commons.utils.JsonUtils;

/**
 * JSON序列化测试
 */
public class JsonUtilsTest {
    @Test
    public void testToJson(){
        Search search = new Search();
        search.setName("username");
        search.setValue("san");
        search.setOperator("LIKE");

        String json = JsonUtils.toJSON(search);
        System.out.println(json);
    }

    @Test
    public void testParseObject(){
        String json = "{\"name\":\"username\",\"value\":\"san\",\"operator\":\"LIKE\"}";
        Search search = JsonUtils.parseObject(json, Search.class);
        System.out.println(search);
    }

    @Test
    public void parseList(){
        String json = "[{\"name\":\"username1\",\"value\":\"san\",\"operator\":\"LIKE\"}, {\"name\":\"username2\",\"value\":\"san\",\"operator\":\"LIKE\"}]";
        List<Search> searchList = JsonUtils.parseList(json, Search.class);
        System.out.println(searchList);
    }

}
