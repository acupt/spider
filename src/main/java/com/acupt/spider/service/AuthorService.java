package com.acupt.spider.service;

import com.acupt.spider.dao.impl.AuthorDAO;
import com.acupt.spider.dao.impl.AuthorUpdateDAO;
import com.acupt.spider.model.Author;
import com.acupt.spider.util.BeanFactory;

/**
 * Created by liujie on 2017/5/11.
 */
public class AuthorService {

    private AuthorDAO authorDAO = BeanFactory.getBean(AuthorDAO.class);

    private AuthorUpdateDAO authorUpdateDAO = BeanFactory.getBean(AuthorUpdateDAO.class);

    public void updateAuthor(Author record) {
        authorUpdateDAO.insert(record);
        Author author = authorDAO.exist(record);
        if (author != null) {
            record.setId(author.getId());
            authorDAO.update(record);
        } else {
            authorDAO.insert(record);
        }
    }
}
