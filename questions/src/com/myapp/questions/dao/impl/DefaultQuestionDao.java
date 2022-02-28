package com.myapp.questions.dao.impl;

import com.myapp.questions.dao.QuestionDao;
import com.myapp.questions.model.QuestionModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository(value = "questionDao")
public class DefaultQuestionDao implements QuestionDao {
    private static final String GET_LAST_QUESTIONS_QUERY = "SELECT {p:" + QuestionModel.PK + "} "
            + "FROM {" + QuestionModel._TYPECODE + " AS p} WHERE {" + QuestionModel.CREATIONTIME + "} >= '";
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Resource
    private FlexibleSearchService flexibleSearchService;

    @Override
    public List<QuestionModel> getLastAddedQuestions(Date afterDate) {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        FlexibleSearchQuery query = new FlexibleSearchQuery(GET_LAST_QUESTIONS_QUERY + dateFormat.format(afterDate) + "'");
        return flexibleSearchService.<QuestionModel> search(query).getResult();
    }
}
