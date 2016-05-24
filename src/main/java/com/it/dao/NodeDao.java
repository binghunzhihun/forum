package com.it.dao;

import com.it.entity.Node;
import com.it.util.DBHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class NodeDao {

    public List<Node> findAll() {
        String sql = "select * from t_node";
        return DBHelp.query(sql,new BeanListHandler<>(Node.class));
    }

    public Node findById(Integer id) {
        String sql = "select * from t_node where id = ?";
        return DBHelp.query(sql,new BeanHandler<>(Node.class),id);
    }
}
