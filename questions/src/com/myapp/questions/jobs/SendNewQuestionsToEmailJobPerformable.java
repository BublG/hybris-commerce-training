package com.myapp.questions.jobs;

import com.myapp.questions.dao.QuestionDao;
import com.myapp.questions.model.QuestionModel;
import com.myapp.questions.model.SendNewQuestionsToEmailCronJobModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.util.mail.MailUtils;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

public class SendNewQuestionsToEmailJobPerformable
        extends AbstractJobPerformable<SendNewQuestionsToEmailCronJobModel> {

    private static final Logger LOG = Logger.getLogger(SendNewQuestionsToEmailJobPerformable.class.getName());

    @Resource
    private QuestionDao questionDao;
    @Resource
    private ModelService modelService;

    @Override
    public PerformResult perform(SendNewQuestionsToEmailCronJobModel sendNewQuestionsToEmailCronJobModel) {
        LOG.info("Sending new questions to email");
        Date lastRunTime = new Date();
        if (sendNewQuestionsToEmailCronJobModel.getLastRunTime() == null) {
            sendNewQuestionsToEmailCronJobModel.setLastRunTime(new Date(0));
        }
        List<QuestionModel> newQuestions =
                questionDao.getLastAddedQuestions(sendNewQuestionsToEmailCronJobModel.getLastRunTime());
        try {
            sendEmail(getMessage(newQuestions), "goragora123456123456@mail.ru");
        }
        catch (final EmailException e) {
            LOG.error("Problem sending new email. Note that org.apache.commons.mail.send() can block if behind a firewall/proxy.)");
            LOG.error("Problem sending new questions email.", e);
            return new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
        }
        sendNewQuestionsToEmailCronJobModel.setLastRunTime(lastRunTime);
        modelService.save(sendNewQuestionsToEmailCronJobModel);
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    private void sendEmail(String message, String recipient) throws EmailException {
        final String subject = "New questions";
        final Email email = MailUtils.getPreConfiguredEmail();
        email.setFrom("vasyapupkin123456123456@mail.ru");
        email.addTo(recipient);
        email.setSubject(subject);
        email.setMsg(message);
        email.setTLS(true);
        email.send();
    }

    private String getMessage(List<QuestionModel> questions) {
//        StringBuilder message = new StringBuilder();
//        questions = questions.stream().sorted(Comparator.comparing(q -> q.getProduct().getCode())).collect(Collectors.toList());
//        if (!questions.isEmpty()) {
//            String currentProductName = questions.get(0).getProduct().getName();
//            message.append("Product: ").append(currentProductName).append("\n");
//            for (QuestionModel question : questions) {
//                if (!question.getProduct().getName().equals(currentProductName)) {
//                    currentProductName = question.getProduct().getName();
//                    message.append("---------------------").append("\n");
//                    message.append("Product: ").append(currentProductName).append("\n");
//                }
//                message.append("Product code: ").append(question.getProduct().getCode()).append("\n");
//                message.append("Question code: ").append(question.getCode()).append("\n");
//                message.append("Question: ").append(question.getQuestion()).append("\n");
//                message.append("Answer: ").append(question.getAnswer()).append("\n");
//            }
//        }
//        return message.toString();
        return String.valueOf(questions.size());
    }
}
