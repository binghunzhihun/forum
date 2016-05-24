package com.it.web.topic;

import com.it.entity.Fav;
import com.it.entity.Topic;
import com.it.entity.User;
import com.it.exception.ServiceException;
import com.it.service.TopicService;
import com.it.web.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/topic/view.do")
public class ViewTopicServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if(StringUtils.isNumeric(id)) {
            TopicService topicService = new TopicService();

            try {
                Topic topic = topicService.viewTopic(Integer.valueOf(id));
                req.setAttribute("topic",topic);

                //判断当前登录用户是否收藏过该主题
                User user = getLoginUser(req);
                if(user != null) {
                    Fav fav = topicService.findFavByUserIdAndTopicId(user.getId(),topic.getId());
                    if(fav != null) {
                        req.setAttribute("action","fav");
                    }
                }

                forward(req,resp,"topic/view");
            } catch (ServiceException ex) {
                resp.sendError(404,ex.getMessage());
            }
        } else {
            resp.sendError(404,"查看的内容不存在或已被删除");
        }

    }
}
